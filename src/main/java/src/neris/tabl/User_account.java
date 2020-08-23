package src.neris.tabl;

public class User_account {

	private Integer user_id;
	private String date;
	private String surname;
	private String first_name;
	private String otchestvo;
	private String otdel;
	private String tel;
	private String email;
	private String user_name;
	private String password;
	private String rule;

	public User_account(Integer user_id, String date, String surname, String first_name, String otchestvo, String otdel,
			String tel, String email, String user_name, String password, String rule) {
		this.user_id = user_id;
		this.date = date;
		this.surname = surname;
		this.first_name = first_name;
		this.otchestvo = otchestvo;
		this.otdel = otdel;
		this.tel = tel;
		this.email = email;
		this.user_name = user_name;
		this.password = password;
		this.rule = rule;

	}

	public User_account() {
		// TODO Auto-generated constructor stub
	}

	public Integer getuser_id() {
		return user_id;
	}

	public void setuser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String surname) {
		this.surname = surname;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getotchestvo() {
		return otchestvo;
	}

	public void setotchestvo(String otchestvo) {
		this.otchestvo = otchestvo;
	}

	public String getotdel() {
		return otdel;
	}

	public void setotdel(String otdel) {
		this.otdel = otdel;
	}

	public String gettel() {
		return tel;
	}

	public void settel(String tel) {
		this.tel = tel;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getuser_name() {
		return user_name;
	}

	public void setuser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getrule() {
		return rule;
	}

	public void setrule(String rule) {
		this.rule = rule;
	}
}
