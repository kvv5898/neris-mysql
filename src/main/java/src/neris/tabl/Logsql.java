package src.neris.tabl;

public class Logsql {

	private Integer log_id;
	private String user_name;
	private String date;
	private String sql_code;


	public Logsql (Integer log_id, String user_name, String date, String sql_code) {
		this.log_id = log_id;
		this.user_name = user_name;
		this.user_name = date;
		this.sql_code = sql_code;
	}

	public Logsql () {
		// TODO Auto-generated constructor stub
	}

	public Integer getlog_id() {
		return log_id;
	}

	public void setlog_id(Integer log_id) {
		this.log_id = log_id;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getuser_name() {
		return user_name;
	}

	public void setuser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getsql_code() {
		return sql_code;
	}

	public void setsql_code(String sql_code) {
		this.sql_code = sql_code;
	}

}
