package src.neris.tabl;

public class Auth {

	   private Integer id;
	   private String date_start;
	   private String date_end;
	   private String ip;
	   private String host;
	   private String getway;
	   private String user_in;
	   private String pass_in;
	   private String session;
	   private String status;
	    
	   public Auth (Integer id, String date_start, String date_end, String ip, String host, String getway, String user_in, String pass_in, String session, String status) {
		   this.id = id;
		   this.date_start = date_start;
		   this.date_end = date_end;
		   this.ip = ip;
		   this.ip = host;
		   this.ip = getway;
		   this.user_in = user_in;
		   this.pass_in = pass_in;
		   this.session = session;
		   this.status = status;
	       
	      
	   }
	    
	   	   
	public Integer id() {
	       return id;
	   }
	 
	   public void setid(Integer id) {
	       this.id = id;
	   }
	   
	   public String date_start() {
	       return date_start;
	   }
	 
	   public void setdate_start(String date_start) {
	       this.date_start = date_start;
	   }
	   
	   public String date_end() {
	       return date_end;
	   }
	 
	   public void setdate_end(String date_end) {
	       this.date_end = date_end;
	   }
	   
	   public String getip() {
	       return ip;
	   }
	 
	   public void setip(String ip) {
	       this.ip = ip;
	   }
	 
	   public String gethost() {
	       return host;
	   }
	 
	   public void sethost(String host) {
	       this.host = host;
	   }
	   
	   public String getgetway() {
	       return getway;
	   }
	 
	   public void setgetway(String getway) {
	       this.getway = getway;
	   }
	   
	   public String getuser_in() {
	       return user_in;
	   }
	 
	   public void setuser_in(String user_in) {
	       this.user_in = user_in;
	   }
	   
	   
	   public String getpass_in() {
	       return pass_in;
	   }
	 
	   public void setpass_in(String pass_in) {
	       this.pass_in = pass_in;
	   }
	   
	   public String getsession() {
	       return session;
	   }
	 
	   public void setsession(String session) {
	       this.session = session;
	   }
	   
	   public String getstatus() {
	       return status;
	   }
	 
	   public void setstatus(String status) {
	       this.status = status;
	   }
}