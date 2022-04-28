import java.sql.*;

public class conn
{
	public Connection c = null;
	public Statement stmt = null;
	
	public conn()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stackoverflow", "postgres", "postgres");
			stmt = c.createStatement();
            System.out.println("Connected");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
	}
}
