package tel_ran.quality.entities;

import javax.persistence.Entity;

@Entity
public class Manager extends Employee {
	
    public Manager(int id, String name, int birthyear, String phone, String email, Address address) {
		super(id, name, birthyear, phone, email, address);
	}
   
   public Manager() {
		super();
	}
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", service=" + service.name + "]";
	}
    
}
