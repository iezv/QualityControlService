package tel_ran.quality.entities;

import static tel_ran.quality.api.QualityConstants.*;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	int id;
	LocalDate startDate;
	LocalDate closeDate;
	String status;
	String questCod; 
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
	public String getQuestCod() {
		return questCod;
	}

	public void setQuestCod(String questCod) {
		this.questCod = questCod;
	}

	@ManyToOne
	Service service;
	
	public Ticket(LocalDate startDate) {
		super();
		this.startDate = startDate;
		status = "open";
		}

	public Ticket() {
		super();
	}

	
	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public Service getService() {
		return service;
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
		Ticket other = (Ticket) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", startDate=" + startDate + ", closeDate=" + closeDate + ", service="
				+ service + "]";
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
			} catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
	}

	

}
