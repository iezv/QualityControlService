package tel_ran.quality.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;
import tel_ran.quality.api.IdAddress;
import tel_ran.quality.entities.*;
import tel_ran.quality.model.dao.QualityOrm;
import static tel_ran.quality.api.QualityConstants.*;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
@RestController
public class QualityOrmService {

	private static final String PACKAGE_QUALITY = "tel_ran.quality.entites.";
	@Autowired	
	QualityOrm qualityOrm;
	
	@RequestMapping(value = ADD_EMPLOYEE, method=RequestMethod.POST)
	public String addEmployee (@RequestBody Map<String,Object> data){
		System.out.println(data);
		String type = (String) data.get( TYPE );
		if ( type == null )
			return "Type missing";
		
		try {
			Employee employee = (Employee) Class.forName(PACKAGE_QUALITY + type).newInstance();
			try {
				employee.setData(data);
			} catch (IllegalArgumentException e) {
				return e.getMessage();
			}
			boolean res = qualityOrm.addEmployee(employee, type);
			if (res==false)
				return "No added";
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			return "Wrong Type of Employee " + type;
		} 
		return "Success";
	}
	
	@RequestMapping (value = UPDATE_ADDRESS, method=RequestMethod.POST)
	public String updateAddress(@RequestBody IdAddress idAddress){
		boolean res = qualityOrm.updateAddress(idAddress.getId(), 
				new Address(idAddress.getCity(), idAddress.getStreet(), idAddress.getBld(),idAddress.getAppart()));
	    return res ? "update Success" : "client not found with id:" +idAddress.getId();
	}
	

	@RequestMapping (value = GET_CLIENT)
	public Map<String,Object> getClient( int id ){
		Map<String,Object> res = new LinkedHashMap<>();
		Client client = qualityOrm.getClient(id);
		if(client==null){
			res.put(STATUS, "error");
			res.put(DATA, "client not found " + id);
		}
		else {
		   res.put(STATUS, "success");
		   res.put(DATA, client);}
		return res;
	}
	
	@RequestMapping (value = GET_EMPLOYEE)
	public Map<String,Object> getEmployee( int id ){
		Map<String,Object> res = new LinkedHashMap<>();
		Employee employee = qualityOrm.getEmployee(id);
		if(employee==null){
			res.put(STATUS, "error");
			res.put(DATA, "employee not found " + id);
		}
		else {
		   res.put(STATUS, "success");
		   res.put(DATA, employee);}
		return res;
	}
	
	@RequestMapping (value = GET_COMPANY)
	public Map<String,Object> getCompany( String name ){
		Map<String,Object> res = new LinkedHashMap<>();
		Company company = qualityOrm.getCompany(name);
		if(company==null){
			res.put(STATUS, "error");
			res.put(DATA, "company not found " + name);
		}
		else {
		   res.put(STATUS, "success");
		   res.put(DATA, company);}
		return res;
	}
	
	@RequestMapping (value = GET_QUESTIONS_SERVICE)
	public Map<String,Object> getQuestionsByService( String nameService ){
		Map<String,Object> res = new LinkedHashMap<>();
		Service service = qualityOrm.getService(nameService);
		
		if(service==null){
			res.put(STATUS, "error");
			res.put(DATA, "service not found " + nameService);
		}
		else {
		   res.put(STATUS, "success");
		   res.put(DATA, nameService);}
		return res;
	}
		
	public static void main(String[] args) {
		SpringApplication.run(QualityOrmService.class, args);
	}

}
