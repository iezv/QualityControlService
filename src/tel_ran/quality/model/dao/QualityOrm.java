package tel_ran.quality.model.dao;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import tel_ran.quality.entities.*;

public class QualityOrm {

	@PersistenceContext(unitName = "springHibernate", type = PersistenceContextType.EXTENDED)
	EntityManager em;

	@Transactional
	public boolean addCeo(Ceo ceo) {
		if (em.find(Person.class, ceo.getId()) != null)
			return false;
		em.persist(ceo);
		return true;
	}

	@Transactional
	public boolean addCompany(Company company, int id) {
		if (em.find(Company.class, company.getName()) != null)
			return false;
		Ceo ceo = getCeo(id);
		company.setCeo(ceo);
		em.persist(company);
		return true;
	}

	private Ceo getCeo(int id) {
		return em.find(Ceo.class, id);
	}

	@Transactional
	public boolean addManager(Manager manager) {
		if (em.find(Person.class, manager.getId()) != null)
			return false;
		em.persist(manager);
		return true;
	}

	@Transactional
	public boolean addEmployee(Employee employee, String servicename) {
		if (em.find(Person.class, employee.getId()) != null)
			return false;
		Service service = getService(servicename);
		employee.setService(service);
		employee.setManager(service.getManager());
		em.persist(employee);
		return true;
	}
	
	@Transactional
	public boolean updateAddress(int id, Address address){
		Person person = em.find(Person.class, id);
		if (person==null)
			return false;
		person.setAddress(address);
		return true;
	}

	public Service getService(String servicename) {
		return em.find(Service.class, servicename);
	}

	public Employee getEmployee(int id) {
		return em.find(Employee.class, id);
	}

	public Company getCompany(String name) {
		return em.find(Company.class, name);
	}

	@Transactional
	public boolean addQuestion(Question question) {
		if (em.find(Question.class, question.getId()) != null)
			return false;
		em.persist(question);
		return true;
	}
	
	@Transactional
    public boolean addService(Service service, int managerId, String namecompany, List<Integer>questId) {
        if (em.find(Service.class, service.getName()) != null)
            return false;
        Manager manager = getManager(managerId);
        Company company = getCompany(namecompany);
        List<Question>questions = getQuestions(questId);
        service.setManager(manager);
        service.setCompany(company);
        service.setQuestions(questions);
        service.setQues1(service.getName()+questions.get(0).getId());
        service.setQues2(service.getName()+questions.get(1).getId());
        service.setQues3(service.getName()+questions.get(2).getId());
        service.setQues4(service.getName()+questions.get(3).getId());
        service.setQues5(service.getName()+questions.get(4).getId());
        em.persist(service);
        return true;
    }

	private List<Question> getQuestions(List<Integer> questId) {
		List<Question>questions = new ArrayList<>();
		for(int i=0; i<questId.size(); i++){
			questions.add(em.find(Question.class, questId.get(i)));
		}
		return questions;
	}

	public Manager getManager(int managerId) {
		return em.find(Manager.class, managerId);
	}
	
	@Transactional
    public boolean addClient(Client client, Set<String> serviceNames) {
        if (em.find(Person.class, client.getId()) != null)
            return false;
        Set<Service> services = getServices(serviceNames);
        client.setServices(services);
        em.persist(client);
        return true;
    }

	private Set<Service> getServices(Set<String> serviceNames) {
		Set<Service>res = new HashSet<>();
		for(String ser: serviceNames){
			res.add(em.find(Service.class, ser));
		}
		return res;
	}
	
	@Transactional
    public boolean addFeedback(Feedback feedback, int ClientId, String serviceName) {
        if (em.find(Feedback.class, feedback.getId()) != null)
            return false;
        Client client = getClient(ClientId);
        Service service = getService(serviceName);
        feedback.setClient(client);
        feedback.setService(service);
        em.persist(feedback);
        isCreateTicket(feedback, service);
        return true;
    }

	private void isCreateTicket(Feedback feedback, Service service) {
		if (feedback.getResult().getQues1()<3||feedback.getResult().getQues2()<3||
			feedback.getResult().getQues3()<3||feedback.getResult().getQues4()<3||
			feedback.getResult().getQues5()<3 ) inspectTicket(feedback, service);
	}

	private void inspectTicket(Feedback feedback, Service service) {
		if (feedback.getResult().getQues1()<3){
			if(!isOpenTicket(service.getQues1())) {
				long counter = countBadFeedback(service.getQues1(), feedback.getDate());
				if (counter%10==0&&counter!=0) createTicket(feedback, service, service.getQues1());
			}
		}
		if (feedback.getResult().getQues2()<3){
			if(!isOpenTicket(service.getQues2())) {
				long counter = countBadFeedback(service.getQues2(), feedback.getDate());
				if (counter%10==0&&counter!=0) createTicket(feedback, service, service.getQues2());
			}
		}
		if (feedback.getResult().getQues3()<3){
			if(!isOpenTicket(service.getQues3())) {
				long counter = countBadFeedback(service.getQues3(), feedback.getDate());
				if (counter%10==0&&counter!=0) createTicket(feedback, service, service.getQues3());
			}
		}
		if (feedback.getResult().getQues4()<3){
			if(!isOpenTicket(service.getQues4())) {
				long counter = countBadFeedback(service.getQues4(), feedback.getDate());
				if (counter%10==0&&counter!=0) createTicket(feedback, service, service.getQues4());
			}
		}
		if (feedback.getResult().getQues5()<3){
			if(!isOpenTicket(service.getQues5())) {
				long counter = countBadFeedback(service.getQues5(), feedback.getDate());
				if (counter%10==0&&counter!=0) createTicket(feedback, service, service.getQues5());
			}
		}
	}
	
	@Transactional
	private void createTicket(Feedback feedback, Service service, String quesCod) {
		 Ticket ticket = new Ticket(feedback.getDate());
		 if (em.find(Ticket.class, feedback.getId()) == null){
            ticket.setQuestCod(quesCod);
            ticket.setService(service);
            em.persist(ticket);
      }
	}

	private long countBadFeedback(String ques1, LocalDate date) {
		int month = date.getMonthValue();
		long query=(long)em.createQuery(String.format ("select COUNT (f) from Feedback f where month (f.date)=%d", month)).getSingleResult();
		return query;
	}

	private boolean isOpenTicket(String ques) {
		long query=(long)em.createQuery(String.format ("select COUNT (t) from Ticket t where t.closeDate is NULL and "
				+ "t.questCod='%s'", ques)).getSingleResult();
		if(query>0) return true;
		return false;
	}

	public Client getClient(int clientId) {
		return em.find(Client.class, clientId);
	}
	
	@Transactional
	public Person removePerson (int id){
		Person res = em.find(Person.class, id);
		if (em!=null) em.remove(res);
		return res;
	}
		
	
}
