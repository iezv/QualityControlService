package tel_ran.quality.entities;

import static tel_ran.quality.api.QualityConstants.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Question {
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	int id;
	String body;
	public final int RAITING = 5;
			
	public Question(int id, String body) {
		super();
		this.id = id;
		this.body = body;
	}
	
	public Question() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", body=" + body + ", RAITING=" + RAITING + "]";
	}

	public void setData(Map<String,Object>data) throws IllegalArgumentException {
		if( data==null ) 
			new IllegalArgumentException("Data is null");
		try {
			if( id==0 ) {
				Integer Id=(Integer) data.get(ID);
				if ( Id!=0 ) 
					id = Id;
			}
			body = (String) data.get( BODY );
			} catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
	}
	
}
