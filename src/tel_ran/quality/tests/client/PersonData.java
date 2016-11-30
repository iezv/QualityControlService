package tel_ran.quality.tests.client;

public class PersonData {
	int id;
	String _type;
	String name;
	int birthyear;
	String phone;
	String email;
	String city;
	String street;
	int bld;
	int appart;
	
	public PersonData(int id, String name, int birthyear, String phone, String email, String city, String street,
			int bld, int appart) {
		super();
		this.id = id;
		this.name = name;
		this.birthyear = birthyear;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.street = street;
		this.bld = bld;
		this.appart = appart;
		_type = "Person";
	}

	public PersonData() {
		super();
	}

	public int getId() {
		return id;
	}

	public String get_type() {
		return _type;
	}

	public String getName() {
		return name;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
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

			
}
