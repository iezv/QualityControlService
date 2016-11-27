package tel_ran.quality.entities;

import javax.persistence.*;

@Entity
public class Employee extends Person {
	
    @ManyToOne
	Manager manager;
    
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
				", email=" + email + ", address=" + address + "manager=" + manager.id + ", service=" + service.name + "]";
	}
   
  	
			
}
