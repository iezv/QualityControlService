package tel_ran.quality.tests.client;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static tel_ran.quality.api.QualityConstants.*;
import java.util.Date;

public class QualityOrmClients {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		final String URL = "http://localhost:8080/";
		
		// 1 - Add EMPLOYEE
		EmployeeData empl = 
				new EmployeeData(100064500,"NewEmpoyee",1999, "111-11-11", "email1", "Kiev","rehov", 25, 37, "repair");
		String enpSt = restTemplate.postForObject(URL+ADD_EMPLOYEE,empl,String.class);
		System.out.println(enpSt);
		
		// 2 - Add CLIENT
		ClientData client = 
				new ClientData(100006500,"NewClient",1999, "111-11-11", "email1", "Kiev","rehov", 25, 37, 1, "repair");
		String clientSt = restTemplate.postForObject(URL+ADD_CLIENT,client,String.class);
		System.out.println(clientSt);
		
		// 3 - Add NEW FEEDBACK
		FeedbackData feedback = 
				new FeedbackData(new Date(), "comment", 5, 5, 5, 5, 5, 100000110, "repair");
		String feedSt = restTemplate.postForObject(URL+ADD_FEEDBACK,feedback,String.class);
		System.out.println(feedSt);
		
		// 4 - UPDATE ADDRESS (Client or Employee)
		AddressData address = new AddressData(100000005, "newcity", "newstreet", 10, 10);
		try {
			restTemplate.put(URL+UPDATE_ADDRESS, address);
			System.out.println("Update Success");
		} catch (RestClientException e) {
			System.out.println("Client or Employee not found");
		}
		
		// 5- UPDATE STATUS TICKET
		TicketData ticket = new TicketData(65, "close");
		try {
			restTemplate.put(URL+UPDATE_STATUS_TICKET, ticket);
			System.out.println("Update Success");
		} catch (RestClientException e) {
			System.out.println("Client or Employee not found");
		}
			
	}

}
