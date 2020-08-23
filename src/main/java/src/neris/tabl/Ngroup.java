package src.neris.tabl;

public class Ngroup {

	private Integer group_id;
	private String description;
	private String model;
	private String group_info;

	public Ngroup(Integer group_id, String description, String model, String group_info) {
		this.group_id = group_id;
		this.description = description;
		this.model = model;
		this.group_info = group_info;
	}

	public Ngroup() {
		// TODO Auto-generated constructor stub
	}

	public Integer getgroup_id() {
		return group_id;
	}

	public void setgroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public String getmodel() {
		return model;
	}

	public void setmodel(String model) {
		this.model = model;
	}

	public String getgroup_info() {
		return group_info;
	}

	public void setgroup_info(String group_info) {
		this.group_info = group_info;
	}

}
