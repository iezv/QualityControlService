package tel_ran.quality.entities;
import static tel_ran.quality.api.QualityConstants.*;
import java.util.Map;
import javax.persistence.*;
@Entity
public abstract class Person {
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	int id;
	String name;
	int birthyear;
	String phone;
	String email;
	
	@Embedded
	Address address;
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public Person(int id, String name, int birthyear, String phone, String email, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthyear = birthyear;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public Person() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public Address getAddress() {
		return address;
	}

	public int getBirthyear() {
		return birthyear;
	}

	@Override
	public abstract String toString();
	
	public void setData(Map<String,Object>data) throws IllegalArgumentException {
		if( data==null ) 
			new IllegalArgumentException("Data is null");
		try {
			if( id==0 ) {
				Integer Id=(Integer) data.get(ID);
				if ( Id!=0 ) 
					id = Id;
			}
			name = (String) data.get( NAME );
			phone = (String) data.get( PHONE );
			email = (String) data.get( EMAIL );
			birthyear = (Integer) data.get( BIRTHYEAR );
			String street = (String) data.get( STREET );
			String city = (String) data.get( CITY );
			Integer bld = (Integer) data.get( BUILDING );
			Integer appart = (Integer) data.get( APPARTMENT );
			address = new Address ( city, street, bld==null ? 0 : bld, appart==null ? 0 : appart );
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
	}
	
}
