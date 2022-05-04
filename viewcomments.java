import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class viewcomments extends JFrame implements ActionListener{
    Container container = getContentPane();
    Question question;
    Member m;
    String user;
    String pass;
    String connectionLink;
    JLabel label = new JLabel("Comment");
    JButton button;

    viewcomments(Question question, Member m, String connectionLink, String user, String pass){
        this.question = question;
        this.m = m;
        this.connectionLink = connectionLink;
        this.pass = pass;
        this.user = user;
        setLayoutManager();
        setLocationAndSize();
        setTitle("View comments");
        setVisible(true);
        setBounds(10, 10, 600, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        ;
    }

    public void setLocationAndSize() {
      label.setFont(new Font("Serif", Font.BOLD, 28));
      label.setForeground(new java.awt.Color(255,165,0));
      label.setBounds(200,30,300,50);
      container.add(label);
      JButton button;

      for(int i=0;i<question.comments.size();i++){
        System.out.println("Hi");
        JLabel comment = new JLabel(question.comments.get(i).text);
        comment.setBounds(100,100 * i +100,300,30);
        container.add(comment);
        JLabel vote = new JLabel(Integer.toString(question.comments.get(i).voteCount));
        vote.setBounds(250,100 * i + 130,50,30);
        container.add(vote);

        Comment ans = question.comments.get(i);
        button = new JButton("UPVOTE");
        button.setBounds(100,100 * i + 160,100,30);
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            ans.incrementVoteCount();
            try{

                updatecommentVote(ans, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            dispose();
            new viewcomments(question, m, connectionLink, user, pass);
          }
        });
        container.add(button);

        button = new JButton("DOWNVOTE");
        button.setBounds(300,100 * i + 160,150,30);
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            ans.decrementVoteCount();
            try{

                updatecommentVote(ans, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            dispose();
            new viewcomments(question, m, connectionLink, user, pass);
          }
        });
        this.add(button);
        }
    }


    public void updatecommentVote(Comment com, String questionid) throws Exception {
        String query = "UPDATE Comments set voteCount="+com.voteCount + "where questionid=" + questionid+";";
        System.out.println(query);
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println (e);
            System.exit (-1);
        }
        try {
            Connection connection = DriverManager.getConnection(
                       //"jdbc:postgresql://dbhost:port/dbname", "user", "dbpass");
            this.connectionLink, this.user, this.pass);
  
                       // build query, here we get info about all databases"
  
                       // execute query
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement ();
            statement.executeUpdate(query);
            statement.close();
            connection.commit();
            connection.close();
        }
        catch(Exception e){
            throw e;
        }
  
      }
  
      public void updateanswerVote(Answer com, String questionid) throws Exception {
        String query = "UPDATE Answers set voteCount="+com.voteCount + "where questionid=\'" + questionid+"\';";
        System.out.println(query);
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.err.println (e);
            System.exit (-1);
        }
        try {
            Connection connection = DriverManager.getConnection(
                       //"jdbc:postgresql://dbhost:port/dbname", "user", "dbpass");
            this.connectionLink, this.user, this.pass);
  
                       // build query, here we get info about all databases"
  
                       // execute query
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement ();
            statement.executeUpdate(query);
            statement.close();
            connection.commit();
            connection.close();
        }
        catch(Exception e){
            throw e;
        }
  
      }
    }