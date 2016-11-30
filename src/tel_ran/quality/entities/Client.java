package tel_ran.quality.entities;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static tel_ran.quality.api.QualityConstants.*;

@Entity
public class Client extends Person {
	boolean shabbat;
	    
	public Client(int id, String name, int birthyear, String phone, String email, Address address, boolean shabbat) {
		super(id, name, birthyear, phone, email, address);
		this.shabbat = shabbat;
	}
	
	public Client() {
		super();
	}
	@JsonIgnoreProperties({"manager"})
  	@ManyToMany
	Set<Service>services;
	
	public boolean isShabbat() {
		return shabbat;
	}

	public void setShabbat(boolean shabbat) {
		this.shabbat = shabbat;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone + ", email=" + email + ", address=" + address + 
				"shabbat=" + shabbat + ", services=" + (services==null?"NULL":services) + "]";
	}

	@Override
	public void setData(Map<String, Object> data) throws IllegalArgumentException {
		super.setData(data);
		try {
			System.out.println(data.get( SHABBAT ));
			Integer shabb =(Integer) data.get( SHABBAT );
			if(shabb==1) shabbat = true;
			else shabbat = false;
			} catch (Exception e) {
			throw new IllegalArgumentException("Wrong field/s of Client");
		}

	}

}
