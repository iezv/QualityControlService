package tel_ran.quality.tests.client;

public class EmployeeData extends PersonData {
	String servicename;

	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeData(int id, String name, int birthyear, String phone, String email, String city, String street,
			int bld, int appart, String servicename) {
		super(id, name, birthyear, phone, email, city, street, bld, appart);
		this.servicename = servicename;
		_type = "Employee";
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Override
	public String toString() {
		return "EmployeeData [servicename=" + servicename + ", id=" + id + ", _type=" + _type + ", name=" + name
				+ ", birthyear=" + birthyear + ", phone=" + phone + ", email=" + email + ", city=" + city + ", street="
				+ street + ", bld=" + bld + ", appart=" + appart + "]";
	}
	
	
	
	
}
