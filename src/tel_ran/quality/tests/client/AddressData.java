package tel_ran.quality.tests.client;

public class AddressData {
	int id;
	String city;
	String street;
	int bld;
	int appart;
	
	public AddressData(int id, String city, String street, int bld, int appart) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.bld = bld;
		this.appart = appart;
	}
	
	public AddressData() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppart() {
		return appart;
	}

	@Override
	public String toString() {
		return "AddressData [id=" + id + ", city=" + city + ", street=" + street + ", bld=" + bld + ", appart=" + appart
				+ "]";
	}

}
