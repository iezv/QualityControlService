package tel_ran.quality.entities;

import javax.persistence.Entity;

@Entity
public class Ceo extends Person {
	
	public Ceo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ceo(int id, String name, int birthyear, String phone, String email, Address address) {
		super(id, name, birthyear, phone, email, address);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Ceo [id=" + id + ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone + ", email=" + email	+ "]";
	}

}
