public class Model {

	Model() {
	}

	/*
	 * Account checkExists(int id) {
	 * try
	 * {
	 * conn c1 = new conn();
	 * ResultSet rs =
	 * c1.stmt.executeQuery("select * from Account where id = '"+id+"';");
	 * Account details = null;
	 * while(rs.next()) {
	 * // accStatus acs = rs.getString("acc");
	 * details = new Account(rs.getString("name"),
	 * rs.getString("password"),rs.getString("email"), rs.getString("acc"),
	 * rs.getInt("id"), rs.getInt("phone"),rs.getInt("reputation"));
	 * break;
	 * }
	 * 
	 * if(details == null) {
	 * throw new Exception("Account ID does not exist");
	 * }
	 * 
	 * return details;
	 * }
	 * catch(Exception e)
	 * {
	 * return null;
	 * }
	 * }
	 * }
	 */// String query = "INSERT INTO Question () VALUES (" + theView.getquantity() +
		// ","
		// + theView.getcost() + ");";

	public boolean resetPassword(String name, String password) {
		try {
			conn c1 = new conn();
			ResultSet rs = c1.stmt
					.executeQuery("UPDATE Account SET password = '" + password + "' WHERE name= '" + name + "';");

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateDetails(String email, int phone, String name) {
		try {
			conn c1 = new conn();
			c1.stmt.executeUpdate(
					"UPDATE Account SET email = '" + email + "', phone = " + phone + " WHERE name= '" + name + "';");

			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean registerUser(String name, String password, String email, String accStatus, int phone, int id,
			int reputation) {
		try {
			System.out.println("HI");
			conn c1 = new conn();
			c1.stmt.executeUpdate("Insert into Account values ('" + name + "','" + password + "','" + email + "','"
					+ accStatus + "'," + phone + "," + id + "," + reputation + ");");
			System.out.println("BYE");
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;

		}
	}

	public static void main(String[] args) {
		Model m = new Model();
		m.registerUser("Rohan", "123", "rohanm@gmail.com", "ACTIVE", 47364536, 10, 0);
		m.updateDetails("abcd@gmail.com", 489489489, "Rohan");
	}
}
