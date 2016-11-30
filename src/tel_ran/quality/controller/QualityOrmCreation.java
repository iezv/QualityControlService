package tel_ran.quality.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tel_ran.quality.entities.*;
import tel_ran.quality.model.dao.QualityOrm;

@Component
public class QualityOrmCreation implements CommandLineRunner {
	@Autowired
	QualityOrm qualityOrm;

	@Override
	public void run(String... arg0) throws Exception {
		
		// ---------------------------------------
		Ceo ceo = new Ceo(100000001, "Ivan", 1975, "555-55-55", "ceo@mail.ru", genRandomAddress());
		Company company = new Company("TOYOTA", "Tel Aviv");
		createCompany(company, ceo, qualityOrm);
		// ----------------------------------------
		createQuestions(qualityOrm);
		// ----------------------------------------
		Manager [] managers ={
		new Manager(100000002, "Ilya", 1960, "444-44-44", "man1@mail.ru",  genRandomAddress()),
		new Manager(100000003, "Vova", 1974, "444-33-22", "man2@mail.ru",  genRandomAddress()),
		new Manager(100000004, "Vasya", 1966, "444-13-58", "man3@mail.ru",  genRandomAddress())};
		createManagers(managers, qualityOrm);
		// ----------------------------------------
	    Set<Service> services = createSetServices();
	    createServices(services, qualityOrm, company.getName());
		// ----------------------------------------
		List<Employee> employees = createListEmployees();
		createEmployee(employees, qualityOrm);
		// ----------------------------------------
		 List<Client> clients = createListClients();
		 createClients(clients, qualityOrm);
		// ----------------------------------------
	    createFeedbacks(qualityOrm);
		// ----------------------------------------
		// Tickets created AUTO!
		
	}

	// Add COMPANY
	private void createCompany(Company company, Ceo ceo, QualityOrm qualityOrm) {
		qualityOrm.addCeo(ceo);
		qualityOrm.addCompany(company, ceo.getId());
	}

	private static Address genRandomAddress() {
		final int N_RANDOM = 100;
		Random gen = new Random();
		Address address = new Address("city" + gen.nextInt(N_RANDOM), "street" + gen.nextInt(N_RANDOM),
				gen.nextInt(N_RANDOM), gen.nextInt(N_RANDOM));
		return address;
	}
	
	// Add List QUESTIONS
	private void createQuestions(QualityOrm qualityOrm) {
		qualityOrm.addQuestion(new Question(1, "Are you satisfied with the quality of services?"));
		qualityOrm.addQuestion(new Question(2, "Are you satisfied with timelines?"));
		qualityOrm.addQuestion(new Question(3, "Our employee was polite to you?"));
		qualityOrm.addQuestion(new Question(4, "Overall rating of our service"));
		qualityOrm.addQuestion(new Question(5, "Do you like our advertising?"));
	}
	// Add MANAGERS
	private void createManagers(Manager[] managers, QualityOrm qualityOrm) {
		for(int i=0; i<managers.length; i++){
			qualityOrm.addManager(managers[i]);
		}
	}
	 // Add SERVICES
    private Set<Service> createSetServices() {
        Set<Service> services = new LinkedHashSet<>();
        services.add(new Service("repair"));
        services.add(new Service("tire_mounting"));
        services.add(new Service("washing"));
        return services;
    }
 
    private void createServices(Set<Service> services, QualityOrm qualityOrm, String nameComp) {
    	List<Integer> questions = new ArrayList<>();
    	questions.add(1); questions.add(2); questions.add(3); questions.add(4); questions.add(5); 
        int i = 1;
        for (Service service : services) {
        	qualityOrm.addService(service, 100000001 + i, nameComp, questions);
            i++;
        }
    }
	// Add EMPLOYEES
	private List<Employee> createListEmployees() {
		List<Employee> employees = new ArrayList<>();
		Random gen = new Random();
		for (int i = 5; i < 11; i++) {
			Employee employee = new Employee(100000000 + i, "employee" + i, 1950 + gen.nextInt(60),
					"phone" + gen.nextInt(100), "email" + gen.nextInt(100), genRandomAddress());
			employees.add(employee);
		}
		return employees;
	}
	
	private void createEmployee(List<Employee> emp, QualityOrm qualityOrm) {
        for (int i=0;i<3;i++) {
            qualityOrm.addEmployee(emp.get(i), "repair"); }
        for (int i=3;i<5;i++) {
            qualityOrm.addEmployee(emp.get(i), "tire_mounting"); }
        for (int i=5;i<emp.size();i++) {
            qualityOrm.addEmployee(emp.get(i), "washing"); }
    }
	
	// Add CLIENTS
    private List<Client> createListClients() {
        List<Client> clients = new ArrayList<>();
        final int N_RANDOM = 100;
        Random gen = new Random();
        for (int i = 0; i < N_RANDOM; i++) {
            clients.add(new Client(100000011 + i, "name" + gen.nextInt(N_RANDOM), 1950 + gen.nextInt(60),
            		"phone" + gen.nextInt(N_RANDOM), "email" + gen.nextInt(N_RANDOM), genRandomAddress(),true));
        }
        return clients;
    }
 
    private void createClients(List<Client> clien, QualityOrm qualityOrm) {
    	Set <String> services = new HashSet<>();
    	services.add("repair"); services.add("tire_mounting"); services.add("washing");
        for (Client client : clien) {
            qualityOrm.addClient(client, services);
        }
    }
    
    // Add RECEIVEDFEEDBACKS
    private static void createFeedbacks(QualityOrm qualityOrm) {
        for (int i = 0; i < 100; i++) {
            Feedback repairRec = genFeedback();
            qualityOrm.addFeedback(repairRec, 100000011 + i, "repair");
            Feedback tire_mounting = genFeedback();
            qualityOrm.addFeedback(tire_mounting, 100000011 + i, "tire_mounting");
            Feedback washing = genFeedback();
            qualityOrm.addFeedback(washing, 100000011 + i, "washing");
          }
    }
 
    private static Feedback genFeedback() {
        final int N_RANDOM = 100;
        Random gen = new Random();
        return new Feedback(genNewDate(), "comment" + gen.nextInt(N_RANDOM), genRandomResult());
    }
 
    private static Result genRandomResult() {
        Random gen = new Random();
        return new Result(1 + gen.nextInt(5), 1 + gen.nextInt(5), 1 + gen.nextInt(5), 1 + gen.nextInt(5),
                1 + gen.nextInt(5));
    }
 
    private static LocalDate genNewDate() {
        Random gen = new Random();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(2016 + "-" + gen.nextInt(11) + "-" + gen.nextInt(28));
            LocalDate dat = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return dat;
        } catch (ParseException e) {
            Date date = new Date();
            LocalDate dat = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return dat;
        }
    }
    
    

}
