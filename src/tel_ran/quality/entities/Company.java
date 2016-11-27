package tel_ran.quality.entities;

import java.util.*;
import javax.persistence.*;
import static tel_ran.quality.api.QualityConstants.*;

@Entity
public class Company {
	
	@Id
	@Column(name = "name", nullable = false, insertable = true, updatable = true)
	String name;
	String city;
	
	@OneToOne
	Employee ceo;
	
	public Company(String name, String city) {
		super();
		this.name = name;
		this.city = city;
	}

	public Company() {
		super();
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public Employee getCeo() {
		return ceo;
	}
	
	public void setCeo(Employee ceo) {
		this.ceo = ceo;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", city=" + city + ", ceo=" + ceo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public void setData(Map<String,Object>data) throws IllegalArgumentException {
		System.out.println("123");
		if( data==null ) 
			new IllegalArgumentException("Data is null");
    try {
			if( name==null ) {
				String Id=(String) data.get( NAME );
				if ( Id!=null ) 
					name = Id;
			}
			city = (String) data.get( CITY );
			}
    catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
		 
	}

}
