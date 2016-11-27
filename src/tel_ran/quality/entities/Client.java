package tel_ran.quality.entities;

import java.util.*;
import javax.persistence.*;
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
		return "Client [id=" + id + ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone +
				", email=" + email + ", address=" + address +
				"shabbat=" + shabbat + "]";
	}

	@Override
	public void setData(Map<String, Object> data) throws IllegalArgumentException {
		super.setData(data);
		try {
			shabbat = (boolean) data.get( SHABBAT );
			} catch (Exception e) {
			throw new IllegalArgumentException("Wrong field/s of Client");
		}

	}

}
