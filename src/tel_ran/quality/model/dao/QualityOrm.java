package tel_ran.quality.model.dao;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import javax.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import tel_ran.quality.entities.*;

public class QualityOrm {

	@PersistenceContext(unitName = "springHibernate", type = PersistenceContextType.EXTENDED)
	EntityManager em;

	@Transactional
	public boolean addCeo(Employee employee) {
		if (em.find(Person.class, employee.getId()) != null)
			return false;
		employee.setManager(null);
		em.persist(employee);
		return true;
	}

	@Transactional
	public boolean addCompany(Company company, int id) {
		if (em.find(Company.class, company.getName()) != null)
			return false;
		Employee ceo = getEmployee(id);
		company.setCeo(ceo);
		em.persist(company);
		return true;
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

	private Service getService(String servicename) {
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

	private Manager getManager(int managerId) {
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
        return true;
    }

	private Client getClient(int clientId) {
		return em.find(Client.class, clientId);
	}
	
	
}
