package tel_ran.quality.entities;

import static tel_ran.quality.api.QualityConstants.*;
import java.util.Map;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee extends Person {
	@JsonIgnore
    @ManyToOne
	Manager manager;
    
    @JsonIgnore
    @ManyToOne
    Service service;

	public Employee(int id, String name, int birthyear, String phone, String email, Address address) {
		super(id, name, birthyear, phone, email, address);
	}

	public Employee() {
		super();
	}
    
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone + 
				", email=" + email + ", address=" + address + 
				//"manager=" + (manager==null?"NULL":manager.id) + ", service=" +(service==null?"NULL": service.name) + 
				"]";
	}
	
	     	
			
}
