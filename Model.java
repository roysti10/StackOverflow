import javax.swing.*;

//import Account.accStatus;

import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class Model {

    Model() {}
    
//      public void (int id) {
// 		try
// 		{
// 			conn c1 = new conn();
// 			ResultSet rs = c1.stmt.executeQuery("select * from Question where id = '"+id+"';");
//             Account questions = null;
// 			while(rs.next()) {
// 				// accStatus acs =  rs.getString("acc");
// 				questions = new Account(rs.getString("name"), rs.getString("password"),rs.getString("email"), rs.getString("acc"), rs.getInt("id"), rs.getInt("phone"),rs.getInt("reputation"));
//                 break;
// 			}
			
// 			if(questions == null) {
// 				throw new Exception("no questions are present");
// 			}

//             return questions;
// 		}
// 		catch(Exception e)
// 		{
//             return null;
// 		}	
//     }
// }*///String query = "INSERT INTO Question () VALUES (" + theView.getquantity() + ","
// + theView.getcost() + ");";



	public ArrayList<Question> getQuestions()
	{

		
		//List<List<String>> fetchedQuestions = new ArrayList<List<String>>();

		ArrayList<Question> listOfQuestion = new ArrayList<Question>;
		try {
			
			conn c1 = new conn();
			String query = "Select title,description,viewCount,voteCount,flagCount,closingRemark from Question;";
			ResultSet rs = c1.stmt.executeQuery(query);

			while(rs.next())
			{
				String title = rs.getString("title");
				String description = rs.getString("description");
				int viewCount= rs.getInt("viewCount");
				int voteCount= rs.getInt("voteCount");
				int flagCount =rs.getInt("flagCount");
				//int closingRemark = rs.getString("closingRemark");

				Question question = new Question(title,description);


				List<String> q = new ArrayList<String>();
				question.title=title;
				question.description=description;
				question.viewCount=viewCount;
				question.voteCount=viewCount;
				question.flagCount=flagCount;
				//question.closingRemark=closingRemark;

				listOfQuestion.add(question);

			}

		} catch (Exception e) {
			System.out.println("Model.getQuestions failed! "+e);
		}

		return listOfQuestion;
	}
	public boolean resetPassword(String name,String password){
		try
		{
			conn c1 = new conn();
			ResultSet rs = c1.stmt.executeQuery("UPDATE Account SET password = '"+password+"' WHERE name= '"+name+"';");
			
			return true;

            
		}
		catch(Exception e)
		{
            return false;
		}
    }

	





	public boolean updateDetails(String email,int phone, String name)
	{
		try
		{
			conn c1 = new conn();
			c1.stmt.executeUpdate("UPDATE Account SET email = '"+email+"', phone = "+phone+" WHERE name= '"+name+"';");
			
			return true;		

            
		}
		catch(Exception e)
		{
			System.out.println(e);
            return false;
		}
		
    }

	




	public boolean registerUser(String name,String password, String email , String accStatus ,int phone,int id,int reputation)
	{
		try
		{    System.out.println("HI");
			conn c1 = new conn();
			c1.stmt.executeUpdate("Insert into Account values ('"+name+"','"+ password+"','"+email+"','"+accStatus+"',"+phone+","+id+","+reputation+");");
			System.out.println("BYE");
			return true;


		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		
		}
	}
	public static void main(String[] args) 
	{
		Model m = new Model();
		m.registerUser("Rohan", "123", "rohanm@gmail.com", "ACTIVE", 47364536, 10, 0);
		m.updateDetails("abcd@gmail.com",489489489,"Rohan");
	}
}


public boolean createQuestion(String name,String password, String email , String accStatus ,int phone,int id,int reputation)
{
	try
	{    System.out.println("HI");
		conn c1 = new conn();
		c1.stmt.executeUpdate("Insert into Account values ('"+name+"','"+ password+"','"+email+"','"+accStatus+"',"+phone+","+id+","+reputation+");");
		System.out.println("BYE");
		return true;


	}
	catch(Exception e)
	{
		System.out.println(e);
		return false;
	
	}
}
}