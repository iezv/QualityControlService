package tel_ran.quality.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	String city;
	String street;
	int bld;
	int appart;
	
	public Address(String city, String street, int bld, int appart) {
		super();
		this.city = city;
		this.street = street;
		this.bld = bld;
		this.appart = appart;
	}

	public Address() {
		super();
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public int getBld() {
		return bld;
	}

	public int getAppart() {
		return appart;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", bld=" + bld + ", appart=" + appart + "]";
	}
	
}
