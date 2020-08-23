package src.other;

import java.util.ArrayList;

public class StoreSession {

	private static ArrayList<String> sessions = new ArrayList<String>();
	private static ArrayList<String> users = new ArrayList<String>();

	public static void addsessionid(String ssid) {
		sessions.add(ssid);
	}

	public static ArrayList<String> getSessionID() {

		return StoreSession.sessions;
	}

	public static ArrayList<String> getuser() {

		return StoreSession.users;
	}

	public static int sizeuser() {
		int online_us = users.size();
		return online_us;
	}

	public static int sizesession() {
		int online_us = sessions.size();
		return online_us;
	}

	public static void adduser(String user, String ssid) {

		if (sessions.contains(ssid)) {
			System.out.println("adduser id : " + ssid + " with " + user + " completed successfully");
			users.add(user);
		} else {
			System.out.println("session id : " + ssid + "not found");
		}
	}

	public static void deleteuser(String user) {
		System.out.println("Logout (delete) user : " + users.remove(user));
	}

}
