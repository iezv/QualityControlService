package tel_ran.quality.api;

import java.util.Date;

public class IdTicket {
	int id;
	String status;
		
	public IdTicket(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public IdTicket() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", status=" + status  + "]";
	}

}
