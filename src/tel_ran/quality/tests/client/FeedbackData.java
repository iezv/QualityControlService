package tel_ran.quality.tests.client;

import java.util.Date;

public class FeedbackData {
	Date date;
	
	String comment;
		int ques1;
		int ques2;
		int ques3;
		int ques4;
		int ques5;
	int clientid;	
	String servicename;
	
	public FeedbackData(Date date, String comment, int ques1, int ques2, int ques3, int ques4,
			int ques5, int clientid, String servicename) {
		super();
		this.date = date;
		this.comment = comment;
		this.ques1 = ques1;
		this.ques2 = ques2;
		this.ques3 = ques3;
		this.ques4 = ques4;
		this.ques5 = ques5;
		this.clientid = clientid;
		this.servicename = servicename;
	}

	public FeedbackData() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public int getQues1() {
		return ques1;
	}

	public int getQues2() {
		return ques2;
	}

	public int getQues3() {
		return ques3;
	}

	public int getQues4() {
		return ques4;
	}

	public int getQues5() {
		return ques5;
	}

	public int getClientid() {
		return clientid;
	}

	public String getServicename() {
		return servicename;
	}

	@Override
	public String toString() {
		return "FeedbackData [date=" + date + ", comment=" + comment + ", ques1=" + ques1 + ", ques2="
				+ ques2 + ", ques3=" + ques3 + ", ques4=" + ques4 + ", ques5=" + ques5 + ", clientid=" + clientid
				+ ", servicename=" + servicename + "]";
	}

	
}
