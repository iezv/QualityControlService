package tel_ran.quality.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import javax.persistence.*;
import static tel_ran.quality.api.QualityConstants.*;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	int id;
	LocalDate date;
	String comment;
	
	@Embedded
	Result result;

	@OneToOne
	Client client;

	@OneToOne
	Service service;
	
	public Feedback(LocalDate date, String comment, Result result) {
		super();
		this.date = date;
		this.comment = comment;
		this.result = result;
	}

	public Feedback() {
		super();
	}

	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public Client getClient() {
		return client;
	}

	public Result getResult() {
		return result;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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
		Feedback other = (Feedback) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReceivedFeedback [id=" + id + ", date=" + date + ", comment=" + comment + ", result=" + result
				+ ", client=" + client + ", service=" + service + "]";
	}

	public void setData(Map<String, Object> data) throws IllegalArgumentException {
		if (data == null)
			new IllegalArgumentException("Data is null");
		try {
			if (id == 0) {
				Integer Id = (Integer) data.get(ID);
				if (Id != 0)
					id = Id;
			}
			Date tmp = new Date((long) data.get(DATE));
			date = tmp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			comment = (String) data.get(COMMENT);
			Integer ques1 = (Integer) data.get( QUESTION1 );
			Integer ques2 = (Integer) data.get( QUESTION2 );
			Integer ques3 = (Integer) data.get( QUESTION3 );
			Integer ques4 = (Integer) data.get( QUESTION4 );
			Integer ques5 = (Integer) data.get( QUESTION5 );
			result = new Result(ques1, ques2, ques3, ques4, ques5);
				
		} catch (Exception e) {
			throw new IllegalArgumentException("Wrong data in the map");
		}
	}

}
