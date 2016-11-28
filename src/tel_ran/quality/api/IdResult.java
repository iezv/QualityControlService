package tel_ran.quality.api;

public class IdResult {
	int id;
	int ques1;
	int ques2;
	int ques3;
	int ques4;
	int ques5;
	
	public IdResult(int id, int ques1, int ques2, int ques3, int ques4, int ques5) {
		super();
		this.id = id;
		this.ques1 = ques1;
		this.ques2 = ques2;
		this.ques3 = ques3;
		this.ques4 = ques4;
		this.ques5 = ques5;
	}

	public int getId() {
		return id;
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

}
