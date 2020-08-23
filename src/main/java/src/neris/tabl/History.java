package src.neris.tabl;

public class History {

	private Integer history_id;
	private String sn;
	private String date;
	private String status;
	private String organization;

	public History (Integer history_id, String sn, String date, String status, String organization) {
		this.history_id = history_id;
		this.sn = sn;
		this.sn = date;
		this.status = status;
		this.organization = organization;
	}

	public History () {
		// TODO Auto-generated constructor stub
	}

	public Integer gethistory_id() {
		return history_id;
	}

	public void sethistory_id(Integer history_id) {
		this.history_id = history_id;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getsn() {
		return sn;
	}

	public void setsn(String sn) {
		this.sn = sn;
	}
	
	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}

	public String getorganization() {
		return organization;
	}

	public void setorganization(String organization) {
		this.organization = organization;
	}

}
