package tel_ran.quality.tests.client;

public class TicketData {
	int id;
	String status;
	
	
	public TicketData(int id, String status) {
		super();
		this.id = id;
		this.status = status;
		}
	
	public TicketData() {
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
		return "TicketData [id=" + id + ", status=" + status +  "]";
	}
	
}
