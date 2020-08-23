package src.neris.tabl;

public class Org {

	private Integer organization_id;
	private String org_name;
	private String tel;
	private String address;
	private String organization_info;
	

	public Org (Integer organization_id, String org_name, String tel, String address, String organization_info) 
	{
		this.organization_id = organization_id;
		this.org_name = org_name;
		this.tel = tel;
		this.address = address;
		this.organization_info = organization_info;
	}

	public Org() {
		// TODO Auto-generated constructor stub
	}

	public Integer getorganization_id() {
		return organization_id;
	}

	public void setorganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}

	public String getorg_name() {
		return org_name;
	}

	public void setorg_name(String org_name) {
		this.org_name = org_name;
	}

	public String gettel() {
		return tel;
	}

	public void settel(String tel) {
		this.tel = tel;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}
	
	public String getorganization_info() {
		return organization_info;
	}

	public void setorganization_info(String organization_info) {
		this.organization_info = organization_info;
	}

}
