package src.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import src.neris.tabl.Ngroup;
import src.neris.tabl.Guarantee;
import src.neris.tabl.History;
import src.neris.tabl.Logsql;
import src.neris.tabl.Org;
import src.neris.tabl.Received;
import src.neris.tabl.Validity;

import src.other.date_time;

public class Equipment {

	public static List<Ngroup> find_ngroup(Connection conn) throws SQLException {
		String sql = "Select * from ngroup";
		System.out.println("Search info ngroup in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Ngroup> list = new ArrayList<Ngroup>();
		while (rs.next()) {

			Integer group_id = rs.getInt("group_id");
			String description = rs.getString("description");
			String model = rs.getString("model");
			String group_info = rs.getString("group_info");

			Ngroup us = new Ngroup(group_id, description, model, group_info);

			us.setgroup_id(group_id);
			us.setdescription(description);
			us.setmodel(model);
			us.setgroup_info(group_info);
			list.add(us);
		}
		return list;
	}

	public static Integer find_ngroup_duplicate(Connection conn, Ngroup ngroup) throws SQLException {

		String sql = "Select count(*)  as count from ngroup WHERE description =? AND model =? AND group_info =?";
		System.out.println("Search find_ngroup_duplicate in DB: ");
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, ngroup.getdescription());
		pstm.setString(2, ngroup.getmodel());
		pstm.setString(3, ngroup.getgroup_info());

		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		System.out.println("find_ngroup_duplicate count: " + count);
		return count;
	}

	public static Integer find_org_duplicate(Connection conn, Org org) throws SQLException {

		String sql = "Select count(*)  as count from organization WHERE org_name =? AND tel =? AND address =?";
		System.out.println("Search find_org_duplicate in DB: " );
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, org.getorg_name());
		pstm.setString(2, org.gettel());
		pstm.setString(3, org.getaddress());

		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		System.out.println("find_org_duplicate count: " + count);
		return count;
	}
	
	public static Integer find_validity_duplicate(Connection conn, Validity validity) throws SQLException {
		
		String sql = "Select count(*)  as count from validity WHERE org_name =? AND contract =?";
		System.out.println("Search find_validity_duplicate in DB: " );
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, validity.getorg_name());
		pstm.setString(2, validity.getcontract());

		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		System.out.println("find_validity_duplicate: " + count);
		return count;
	}
	
	public static List<History> find_history(Connection conn) throws SQLException {
		String sql = "Select * from history";
		System.out.println("Search info history in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<History> list = new ArrayList<History>();
		while (rs.next()) {
			
			Integer history_id = rs.getInt("history_id");
			String sn = rs.getString("sn");
			String date = rs.getString("date");
			String status = rs.getString("status");
			String organization = rs.getString("organization");

			History us = new History();

			us.sethistory_id(history_id);
			us.setsn(sn);
			us.setdate(date);
			us.setstatus(status);
			us.setorganization(organization);
			list.add(us);
		}
		return list;
	}
	
public static Integer find_history_duplicate(Connection conn, History history) throws SQLException {
	    System.out.println("Search find_history_duplicate in DB: " );
		String sql = "Select count(*)  as count from history WHERE sn =? AND status =? AND organization =?";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, history.getsn());
		pstm.setString(2, history.getstatus());
		pstm.setString(3, history.getorganization());

		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		
		return count;
	}
	
	public static List<String> find_ngroup_name_column(Connection conn, String name_column) throws SQLException {
		String sql = "SELECT " + name_column + " FROM ngroup GROUP BY " + name_column + "";
		System.out.println("Search info find_ngroup_name_column in DB by " + name_column);
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<String> us = new ArrayList<String>();
		while (rs.next()) {
			String name = rs.getString(name_column);
			us.add(name);
		}

		return us;
	}

	public static List<String> find_org_name_column(Connection conn, String name_column) throws SQLException {
		String sql = "SELECT " + name_column + " FROM organization GROUP BY " + name_column + "";
		System.out.println("Search info find_org_name_column in DB by " + name_column);
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<String> us = new ArrayList<String>();
		while (rs.next()) {
			String name = rs.getString(name_column);
			us.add(name);
		}

		return us;
	}
	
	public static List<Ngroup> find_id_gr(Connection conn, Integer group_id) throws SQLException {
		String sql = "Select * from ngroup WHERE group_id=?";
		System.out.println("Search info ngroup in DB by group_id");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, group_id);
		ResultSet rs = pstm.executeQuery();
		List<Ngroup> list = new ArrayList<Ngroup>();
		while (rs.next()) {

			String description = rs.getString("description");
			String model = rs.getString("model");
			String group_info = rs.getString("group_info");

			Ngroup us = new Ngroup(group_id, description, model, group_info);

			us.setgroup_id(group_id);
			us.setdescription(description);
			us.setmodel(model);
			us.setgroup_info(group_info);
			list.add(us);
		}
		return list;
	}

	public static List<Org> find_org(Connection conn) throws SQLException {
		String sql = "Select * from organization";
		System.out.println("Search info organization in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Org> list = new ArrayList<Org>();
		while (rs.next()) {

			Integer organization_id = rs.getInt("organization_id");
			String org_name = rs.getString("org_name");
			String tel = rs.getString("tel");
			String address = rs.getString("address");
			String organization_info = rs.getString("organization_info");

			Org us = new Org(organization_id, org_name, tel, address, organization_info);

			us.setorganization_id(organization_id);
			us.setorg_name(org_name);
			us.settel(tel);
			us.setaddress(address);
			us.setorganization_info(organization_info);
			list.add(us);
		}
		return list;
	}

	public static List<Logsql> find_logsql(Connection conn) throws SQLException {
		String sql = "Select * from logsql";
		System.out.println("Search info logsql in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Logsql> logsql = new ArrayList<Logsql>();
		while (rs.next()) {
			
			Integer log_id = rs.getInt("log_id");
			String user_name = rs.getString("user_name");
			String date = rs.getString("date");
			String sql_code = rs.getString("sql_code");

			Logsql us = new Logsql(log_id, user_name, date, sql_code);

			us.setlog_id(log_id);
			us.setuser_name(user_name);
			us.setdate(date);
			us.setsql_code(sql_code);
			
			logsql.add(us);
		}
		return logsql;
	}
	
	
	public static List<Org> find_id_org(Connection conn, Integer organization_id) throws SQLException {
		String sql = "Select * from organization WHERE organization_id=?";
		System.out.println("Search info organization in DB by organization_id");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, organization_id);
		ResultSet rs = pstm.executeQuery();
		List<Org> list = new ArrayList<Org>();
		while (rs.next()) {

			String org_name = rs.getString("org_name");
			String tel = rs.getString("tel");
			String address = rs.getString("address");
			String organization_info = rs.getString("organization_info");

			Org us = new Org(organization_id, org_name, tel, address, organization_info);

			us.setorganization_id(organization_id);
			us.setorg_name(org_name);
			us.settel(tel);
			us.setaddress(address);
			us.setorganization_info(organization_info);
			list.add(us);
		}
		return list;
	}

	public static List<Org> find_name_org(Connection conn, String org_name) throws SQLException {
		String sql = "Select * from organization WHERE org_name=?";
		System.out.println("Search info organization in DB by: " + org_name);
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, org_name);
		ResultSet rs = pstm.executeQuery();
		List<Org> list = new ArrayList<Org>();

		if (rs.next()) {
			System.out.println(org_name + " found successfully in the database");

			Integer organization_id = rs.getInt("organization_id");
			org_name = rs.getString("org_name");
			String tel = rs.getString("tel");
			String address = rs.getString("address");
			String organization_info = rs.getString("organization_info");

			Org us = new Org(organization_id, org_name, tel, address, organization_info);

			us.setorganization_id(organization_id);
			us.setorg_name(org_name);
			us.settel(tel);
			us.setaddress(address);
			us.setorganization_info(organization_info);
			list.add(us);
		}

		else {
			System.out.println(org_name + " not found in the database");
			list = null;

		}

		return list;
	}

	public static List<Validity> find_validity(Connection conn) throws SQLException {
		String sql = "Select * from validity";
		System.out.println("Search info validity in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Validity> list = new ArrayList<Validity>();
		while (rs.next()) {

			Integer validity_id = rs.getInt("validity_id");
			String date = rs.getString("date");
			Integer month = rs.getInt("month");
			String org_name = rs.getString("org_name");
			String contract = rs.getString("contract");
			String validity_info = rs.getString("validity_info");

			Validity us = new Validity(validity_id, date, month, org_name, contract, validity_info);

			us.setvalidity_id(validity_id);
			us.setdate(date);
			us.setmonth(month);
			us.setorg_name(org_name);
			us.setcontract(contract);
			us.setvalidity_info(validity_info);
			list.add(us);
		}
		return list;
	}

	public static List<Validity> find_validity_for_org(Connection conn, String org_name) throws SQLException {
		String sql = "SELECT * from validity WHERE org_name=? ORDER by date";
		System.out.println("Search info find_validity_for_org in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, org_name);
		ResultSet rs = pstm.executeQuery();
		List<Validity> list = new ArrayList<Validity>();
		while (rs.next()) {

			Integer validity_id = rs.getInt("validity_id");
			System.out.println("validity_id - " + validity_id);
			String date = rs.getString("date");
			Integer month = rs.getInt("month");
			String org_name2 = rs.getString("org_name");
			String contract = rs.getString("contract");
			String validity_info = rs.getString("validity_info");

			Validity us = new Validity(validity_id, date, month, org_name2, contract, validity_info);

			us.setvalidity_id(validity_id);
			us.setdate(date);
			us.setmonth(month);
			us.setorg_name(org_name2);
			us.setcontract(contract);
			us.setvalidity_info(validity_info);
			list.add(us);
		}
		return list;
	}

	public static Integer find_validity_for_contract(Connection conn, String contract) throws SQLException {
		String sql = "SELECT validity_id from validity WHERE contract=?";
		System.out.println("Search info find_validity_for_contract in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, contract);
		ResultSet rs = pstm.executeQuery();
		Integer validity_id = null;
		if (rs.next()) {

			validity_id = rs.getInt("validity_id");
			
		}
		return validity_id;
	}
	
	
	public static String find_validity_for_contract_return_orgname(Connection conn, String contract) throws SQLException {
		String sql = "SELECT org_name from validity WHERE contract=?";
		System.out.println("Search info find_validity_for_contract_return_orgname in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, contract);
		ResultSet rs = pstm.executeQuery();
		String org_name = null;
		if (rs.next()) {

			org_name = rs.getString("org_name");
			
		}
		return org_name;
	}
	
	public static List<Guarantee> find_guar(Connection conn) throws SQLException {
		String sql = "SELECT  g.guarantee_id, v.date, o.org_name, v.month, v.contract, v.validity_info " + "FROM guarantee g "
				+ "INNER JOIN validity v ON v.validity_id=g.validity_id "
				+ "INNER JOIN organization o ON o.organization_id=g.organization_id " + "ORDER BY v.date";
		System.out.println("Search info Guarantee in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Guarantee> list = new ArrayList<Guarantee>();
		while (rs.next()) {

			Integer guarantee_id = rs.getInt("guarantee_id");
			String date = rs.getString("date");
			String org_name = rs.getString("org_name");
			Integer month = rs.getInt("month");
			String contract = rs.getString("contract");
			String validity_info = rs.getString("validity_info");

			Guarantee us = new Guarantee(guarantee_id, date, org_name, month, contract, validity_info);

			us.setguarantee_id(guarantee_id);
			us.setdate(date);
			us.setorg_name(org_name);
			us.setmonth(month);
			us.setcontract(contract);
			us.setvalidity_info(validity_info);
			list.add(us);
		}
		return list;
	}

	public static List<Guarantee> find_id_guar(Connection conn, Integer guarantee_id) throws SQLException {
		String sql = "SELECT  g.guarantee_id, v.date, o.org_name, v.month, v.contract " + "FROM guarantee g "
				+ "INNER JOIN validity v ON v.validity_id=g.validity_id "
				+ "INNER JOIN organization o ON o.organization_id=g.organization_id " + "WHERE g.guarantee_id=?";
		System.out.println("Search info Guarantee in DB by guarantee_id");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, guarantee_id);
		ResultSet rs = pstm.executeQuery();
		List<Guarantee> list = new ArrayList<Guarantee>();
		while (rs.next()) {
			guarantee_id = rs.getInt("guarantee_id");
			String date = rs.getString("date");
			String org_name = rs.getString("org_name");
			Integer month = rs.getInt("month");
			String contract = rs.getString("contract");
			String validity_info = rs.getString("validity_info");
			Guarantee us = new Guarantee(guarantee_id, date, org_name, month, contract, validity_info);

			us.setguarantee_id(guarantee_id);
			us.setdate(date);
			us.setorg_name(org_name);
			us.setmonth(month);
			us.setcontract(contract);
			us.setvalidity_info(validity_info);
			list.add(us);
		}
		return list;
	}

	public static List<String> find_guar_name_column(Connection conn, String name_column) throws SQLException {
		String sql = "SELECT " + name_column + " FROM guarantee GROUP BY " + name_column + "";
		System.out.println("Search info find_guar_name_column in DB by " + name_column);
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<String> us = new ArrayList<String>();
		while (rs.next()) {
			String name = rs.getString(name_column);
			us.add(name);
		}
		;
		return us;
	}


	public static Integer validity_add(Connection conn, Validity validity, String user_name) throws SQLException {
		String sql = "Insert into validity (date, month, org_name, contract, validity_info) values (?,?,?,?,?)";

		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, validity.getdate());
		add.setInt(2, validity.getmonth());
		add.setString(3, validity.getorg_name());
		add.setString(4, validity.getcontract());
		add.setString(5, validity.getvalidity_info());
		add.executeUpdate();
		
		sql = "SELECT validity_id from validity WHERE date=? AND month=? AND org_name=? AND contract=? AND validity_info=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, validity.getdate());
		pstm.setInt(2, validity.getmonth());
		pstm.setString(3, validity.getorg_name());
		pstm.setString(4, validity.getcontract());
		pstm.setString(5, validity.getvalidity_info());
		ResultSet rs = pstm.executeQuery();
		Integer validity_id = null;
		if (rs.next()) {
			validity_id = rs.getInt("validity_id");
		}
		System.out.println("validity_id: " + validity_id);
		if (validity_id !=0) {
			
			Logsql logsql = new Logsql();			
			logsql.setdate(date_time.date());			
			logsql.setuser_name(user_name);
			logsql.setsql_code("Insert into validity (date, month, org_name, contract, validity_info) values ("+validity.getdate()+","+validity.getmonth()+","+validity.getorg_name()+","+validity.getcontract()+","+validity.getvalidity_info()+")");
		
			Equipment.add_logsql(conn, logsql);
		} 
		
		return validity_id;
	}

	public static Integer find_received_sn(Connection conn, String sn) throws SQLException {
		String sql = "Select count(*)  as count  as count from received WHERE sn=?";
		System.out.println("Search find_received_sn in DB by sn");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, sn);
		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}

	public static void add_received(Connection conn, Received add_received, String user_name) throws SQLException {
		String sql = "Insert into received (sn,  group_id, guarantee_id) values (?,?,?)";
		System.out.println("add_received DB for SN: " + add_received.getsn());
		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, add_received.getsn());
		add.setInt(2, add_received.getgroup_id());
		add.setInt(3, add_received.getguarantee_id());

		add.executeUpdate();
		
		Logsql logsql = new Logsql();			
		logsql.setdate(date_time.date());			
		logsql.setuser_name(user_name);
		logsql.setsql_code("Insert into received (sn,  group_id, guarantee_id) values ("+add_received.getsn()+","+add_received.getgroup_id()+","+add_received.getguarantee_id()+")");
	
		Equipment.add_logsql(conn, logsql);
	}

	
	public static void add_history(Connection conn, History history) throws SQLException {
		String sql = "Insert into history (sn, date,  status, organization) values (?,?,?,?)";
		System.out.println("add_history DB");
		PreparedStatement add = conn.prepareStatement(sql);
		
		add.setString(1, history.getsn());
		add.setString(2, date_time.date());
		add.setString(3, history.getstatus());
		add.setString(4, history.getorganization());

		add.executeUpdate();
	}
	
	public static void add_logsql(Connection conn, Logsql logsql) throws SQLException {
		String sql = "Insert into logsql (user_name, date,  sql_code) values (?,?,?)";
		System.out.println("add_logsql DB");
		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, logsql.getuser_name());
		add.setString(2, logsql.getdate());
		add.setString(3, logsql.getsql_code());

		add.executeUpdate();
	}
	
	
	public static List<Received> find_er(Connection conn) throws SQLException {
		String sql = "SELECT r.equipment_id, r.sn, g.model, o.org_name, v.contract "
				+ "FROM received r, ngroup g, guarantee gua, organization o, validity v "
				+ "WHERE r.group_id=g.group_id " + "AND r.guarantee_id=gua.guarantee_id "
				+ "AND o.organization_id=gua.organization_id " + "AND v.validity_id=gua.validity_id";
		System.out.println("Search info Received in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<Received> list = new ArrayList<Received>();
		while (rs.next()) {

			Integer equipment_id = rs.getInt("equipment_id");
			String sn = rs.getString("sn");
			String model = rs.getString("model");
			String org_name = rs.getString("org_name");
			String contract = rs.getString("contract");

			Received us = new Received(equipment_id, sn, model, org_name, contract);

			us.setequipment_id(equipment_id);
			us.setsn(sn);
			us.setmodel(model);
			us.setorg_name(org_name);
			us.setcontract(contract);
			list.add(us);
		}
		return list;
	}

	public static List<String> find_sn_er(Connection conn) throws SQLException {
		System.out.println("Search info find_sn_er in DB");
		String sql = "SELECT sn FROM received";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			String sn = rs.getString("sn");
			list.add(sn);
		}
		return list;
	}
	

	public static List<String> find_history_status(Connection conn) throws SQLException {
		System.out.println("Search info find_sn_er in DB");
		List<String> history = new ArrayList<String>();
		String sql = "Select count(*)  as count from history";
		PreparedStatement pstm = conn.prepareStatement(sql);
		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			count = rs.getInt("count");
		}
		if (count>0) {
		
		sql = "SELECT  status FROM history";
		pstm = conn.prepareStatement(sql);
		rs = pstm.executeQuery();
		if (rs.next()) {
			history.add(rs.getString("status"));	
		}
		else { 
			history=null;
		}
		}
		return history;
	}
	
	public static void group_add(Connection conn, Ngroup ngroup, String user_name) throws SQLException {
		String sql = "Insert into ngroup (description,  model, group_info) values (?,?,?)";

		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, ngroup.getdescription());
		add.setString(2, ngroup.getmodel());
		add.setString(3, ngroup.getgroup_info());

		add.executeUpdate();
		
		Logsql logsql = new Logsql();			
		logsql.setdate(date_time.date());			
		logsql.setuser_name(user_name);
		logsql.setsql_code("Insert into ngroup (description,  model, group_info) values ("+ngroup.getdescription()+","+ngroup.getmodel()+","+ngroup.getgroup_info()+")");
	
		Equipment.add_logsql(conn, logsql);

	}

	public static Integer org_add(Connection conn, Org org, String user_name) throws SQLException {
		String sql = "Insert into organization (org_name, tel, address, organization_info) values (?,?,?,?)";

		PreparedStatement add = conn.prepareStatement(sql);

		add.setString(1, org.getorg_name());
		add.setString(2, org.gettel());
		add.setString(3, org.getaddress());
		add.setString(4, org.getorganization_info());

		add.executeUpdate();

		sql = "SELECT organization_id from organization WHERE org_name=? AND tel=? AND address=? AND organization_info=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, org.getorg_name());
		pstm.setString(2, org.gettel());
		pstm.setString(3, org.getaddress());
		pstm.setString(4, org.getorganization_info());
		ResultSet rs = pstm.executeQuery();
		Integer organization_id = null;
		if (rs.next()) {
			organization_id = rs.getInt("organization_id");
		}
		
if (organization_id !=0) {
			
			Logsql logsql = new Logsql();			
			logsql.setdate(date_time.date());			
			logsql.setuser_name(user_name);
			logsql.setsql_code("Insert into organization (org_name, tel, address, organization_info) values ("+org.getorg_name()+","+org.gettel()+","+org.getaddress()+","+org.getorganization_info()+")");
		
			Equipment.add_logsql(conn, logsql);
		} 
		
		return organization_id;
		
	}
	
	public static void guarantee_add(Connection conn, Integer organization_id, Integer validity_id, String user_name) throws SQLException {
		System.out.println("Insert guarantee in DB for organization_id ("+organization_id+") validity_id("+validity_id+")");
		String sql = "Insert into guarantee (organization_id, validity_id) values (?,?)";

		PreparedStatement add = conn.prepareStatement(sql);

		add.setInt(1, organization_id);
		add.setInt(2,validity_id);
		
		add.executeUpdate();
		
		Logsql logsql = new Logsql();			
		logsql.setdate(date_time.date());			
		logsql.setuser_name(user_name);
		logsql.setsql_code("Insert into guarantee (organization_id, validity_id) values ("+organization_id+","+validity_id+")");
	
		Equipment.add_logsql(conn, logsql);
	}
	
	public static Integer find_guar_orgid_valid(Connection conn, Integer organization_id, Integer validity_id) throws SQLException {
		String sql = "Select count(*)  as count from guarantee WHERE organization_id=? AND validity_id=?";
		System.out.println("Search find_guar_orgid_valid in DB");
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, organization_id);
		pstm.setInt(2, validity_id);
		Integer count = null;
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			count = rs.getInt("count");
		}
		else {
			System.out.println("find_guar_orgid_valid  not found in the database");
		}
		return count;
	}
	
}
