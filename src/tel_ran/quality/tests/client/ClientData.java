package tel_ran.quality.tests.client;

public class ClientData extends PersonData{
	
	int shabbat;
	String servicename;

	public ClientData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientData(int id, String name, int birthyear, String phone, String email, String city, String street,
			int bld, int appart, int shabbat, String servicename) {
		super(id, name, birthyear, phone, email, city, street, bld, appart);
		this.shabbat = shabbat;
		this.servicename = servicename;
		_type = "Client";
		// TODO Auto-generated constructor stub
	}

	public int getShabbat() {
		return shabbat;
	}

	public void setShabbat(int shabbat) {
		this.shabbat = shabbat;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Override
	public String toString() {
		return "ClientData [shabbat=" + shabbat + ", servicename=" + servicename + ", id=" + id + ", _type=" + _type
				+ ", name=" + name + ", birthyear=" + birthyear + ", phone=" + phone + ", email=" + email + ", city="
				+ city + ", street=" + street + ", bld=" + bld + ", appart=" + appart + "]";
	}

		
}
