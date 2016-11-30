package tel_ran.quality.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;
import tel_ran.quality.api.IdAddress;
import tel_ran.quality.api.IdTicket;
import tel_ran.quality.entities.*;
import tel_ran.quality.model.dao.QualityOrm;
import static tel_ran.quality.api.QualityConstants.*;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
@RestController
public class QualityOrmService {

	private static final String PACKAGE_QUALITY = "tel_ran.quality.entities.";
	@Autowired
	QualityOrm qualityOrm;

	@RequestMapping(value = ADD_FEEDBACK, method = RequestMethod.POST)
	public String addFeedback(@RequestBody Map<String, Object> data) {
		try {
			Feedback feed = (Feedback) Class.forName(PACKAGE_QUALITY + "Feedback").newInstance();
			try {
				feed.setData(data);
				System.out.println(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			int client = (Integer) data.get(CLIENTID);
			String service = (String) data.get(SERVICENAME);
			boolean res = qualityOrm.addFeedback(feed, client, service);
			if (res == false)
				return "No added";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			return "Wrong Type of Feedback";
		}
		return "Success";
	}

	@RequestMapping(value = ADD_PERSON, method = RequestMethod.POST)
	public String addPerson(@RequestBody Map<String, Object> data) {
		String type = (String) data.get(TYPE);
		if (type == null)
			return "Type missing";

		try {
			Person person = (Person) Class.forName(PACKAGE_QUALITY + type).newInstance();
			try {
				person.setData(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			boolean res = qualityOrm.addPerson(person);
			if (res == false)
				return "No added";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return "Wrong Type of Person " + type;
		}
		return "Success";
	}

	@RequestMapping(value = ADD_EMPLOYEE, method = RequestMethod.POST)
	public String addEmployee(@RequestBody Map<String, Object> data) {
		String type = (String) data.get(TYPE);
		String service = (String) data.get(SERVICENAME);
		if (type == null)
			return "Type missing";
		System.out.println(data);

		try {
			Employee empl = (Employee) Class.forName(PACKAGE_QUALITY + type).newInstance();
			try {
				empl.setData(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			boolean res = qualityOrm.addEmployee((Employee) empl, service);
			if (res == false)
				return "No added";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			return "Wrong Type of Person " + type;
		}
		return "Success";
	}
	
	@RequestMapping(value = ADD_CLIENT, method = RequestMethod.POST)
	public String addClient(@RequestBody Map<String, Object> data) {
		String type = (String) data.get(TYPE);
		String servicename = (String) data.get(SERVICENAME);
		System.out.println(servicename);
		if (type == null||servicename==null)
			return "Type missing";
		System.out.println(data);

		try {
			Client cl = (Client) Class.forName(PACKAGE_QUALITY + type).newInstance();
			try {
				cl.setData(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			Set<String>sers = new LinkedHashSet<>();
			sers.add(servicename);
			boolean res = qualityOrm.addClient(cl, sers);
			if (res == false)
				return "No added";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			return "Wrong Type of Person " + type;
		}
		return "Success";
	}

	@RequestMapping(value = UPDATE_ADDRESS, method = RequestMethod.PUT)
	public String updateAddress(@RequestBody IdAddress idAddress) {
		boolean res = qualityOrm.updateAddress(idAddress.getId(),
				new Address(idAddress.getCity(), idAddress.getStreet(), idAddress.getBld(), idAddress.getAppart()));
		return res?"Update Success":"Client or Employee not found with id:" + idAddress.getId();
	}
	
	@RequestMapping(value = UPDATE_STATUS_TICKET, method = RequestMethod.PUT)
	public String updateTicket(@RequestBody IdTicket idTicket) {
		boolean res = qualityOrm.updateTicket(idTicket.getId(),	new Ticket(idTicket.getStatus()));
		return res?"Update Success":"Ticket not found with id";
	}

	@RequestMapping(value = GET_CLIENT)
	public Map<String, Object> getClient(int id) {
		Map<String, Object> res = new LinkedHashMap<>();
		Client client = qualityOrm.getClient(id);
		if (client == null) {
			res.put(STATUS, "error");
			res.put(DATA, "client not found " + id);
		} else {
			res.put(STATUS, "success");
			res.put(DATA, client);
		}
		return res;
	}

	@RequestMapping(value = GET_EMPLOYEE)
	public Map<String, Object> getEmployee(int id) {
		Map<String, Object> res = new LinkedHashMap<>();
		Employee employee = qualityOrm.getEmployee(id);
		if (employee == null) {
			res.put(STATUS, "error");
			res.put(DATA, "employee not found " + id);
		} else {
			res.put(STATUS, "success");
			res.put(DATA, employee);
		}
		return res;
	}

	@RequestMapping(value = GET_COMPANY)
	public Map<String, Object> getCompany(String name) {
		Map<String, Object> res = new LinkedHashMap<>();
		Company company = qualityOrm.getCompany(name);
		if (company == null) {
			res.put(STATUS, "error");
			res.put(DATA, "company not found " + name);
		} else {
			res.put(STATUS, "success");
			res.put(DATA, company);
		}
		return res;
	}

	@RequestMapping(value = GET_SERVICE)
	public Map<String, Object> getService(String name) {
		Map<String, Object> res = new LinkedHashMap<>();
		Service service = qualityOrm.getService(name);
		if (service == null) {
			res.put(STATUS, "error");
			res.put(DATA, "service not found " + name);
		} else {
			res.put(STATUS, "success");
			res.put(DATA, service);
		}
		return res;
	}

	@RequestMapping(value = GET_SERVICES)
	public Set<Service> getServices() {
		List<Service> serv = qualityOrm.getServices();
		Set<Service> services = new LinkedHashSet<>();
		for (Service service : serv) {
			services.add(service);
		}
		return services;
	}

	@RequestMapping(value = GET_CLIENTS)
	public Set<Client> getClients() {
		List<Client> clien = qualityOrm.getClients();
		return createSet(clien);
	}

	@RequestMapping(value = GET_FEEDBACKS)
	public Set<Feedback> getFeedbacks() {
		List<Feedback> feedback = qualityOrm.getFeedbacks();
		return createSet(feedback);
	}

	@RequestMapping(value = GET_FEEDBACKS_MONTH_AND_YEAR)
	public Set<Feedback> getFeedbacks(int month, int year) {
		List<Feedback> feedback = qualityOrm.getFeedbacks(month, year);
		return createSet(feedback);
	}

	@RequestMapping(value = GET_TICKETS)
	public Set<Ticket> getTickets(String status) {
		List<Ticket> res = qualityOrm.getTickets(status);
		return createSet(res);
	}

	private Set createSet(List iter) {
		LinkedHashSet<Object> set = new LinkedHashSet<>();
		for (int i = 0; i < iter.size(); i++) {
			set.add(iter.get(i));
		}
		return set;
	}

	public static void main(String[] args) {
		SpringApplication.run(QualityOrmService.class, args);
	}

}
