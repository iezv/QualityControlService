package tel_ran.quality.api;

public class IdAddress {
	int id;
	String city;
	String street;
	int bld;
	int appart;
	
	public IdAddress(int id, String city, String street, int bld, int appart) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.bld = bld;
		this.appart=appart;
	}

	public int getAppart() {
		return appart;
	}

	public IdAddress() {
		super();
	}

	public int getId() {
		return id;
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
	
	
}
