import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class showyourquestionView extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel label = new JLabel("STACKOVERLOW");
    Question question;
    JLabel Label_title = new JLabel("View question");
    JLabel title = new JLabel("Title");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel titleField;
    JTextArea descriptionField;
    JLabel name = new JLabel("Question posted by");
    JLabel nameField;
    String connectionLink;
    String user;
    String pass;
    JButton AddAnswerButton = new JButton("Add Answer");
    JButton ViewAnswers = new JButton("View Answers");
    JButton ViewComments = new JButton("View Comments");
    JButton AddCommentButton = new JButton("Add Comments");
    JButton backButton = new JButton("BACK");
    JButton logoutButton = new JButton("LOGOUT");
    JButton upvote = new JButton("UPVOTE");
    JButton downvote = new JButton("DOWNVOTE");
    JScrollPane scroll;
    Member m;

    showyourquestionView(Question question, String connectionLink, String user, String pass) {

        this.question = question;
        this.connectionLink = connectionLink;
        this.user = user;
        this.pass = pass;
        this.m = null;
        try{
          m = this.getMember();
        }
        catch(Exception err){
          System.err.println(err);
        }
        try{
          getcomment();
          getanswer();
        }
        catch(Exception err){
          System.err.println(err);
        }
        titleField = new JLabel(this.question.title);
        descriptionField = new JTextArea(this.question.description);
        scroll = new JScrollPane (descriptionField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        nameField = new JLabel(m.name);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        setTitle("Write your question");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        descriptionField.setEditable(false);
        descriptionField.setVisible(true);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(new java.awt.Color(255,165,0));
        Label_title.setFont(new Font("Serif", Font.BOLD, 28));
        Label_title.setForeground(new java.awt.Color(41,86,143));
        name.setFont(new Font("Serif", Font.BOLD, 24));
        title.setFont(new Font("Serif", Font.BOLD, 22));
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, 22));

        label.setBounds(450,30,700,100);
        logoutButton.setBounds(900, 30, 150, 30);
        Label_title.setBounds(450,120,500,50);
        name.setBounds(350,200,500,30);
        title.setBounds(350, 250, 100, 30);
        descriptionLabel.setBounds(500, 320, 700, 30);
        nameField.setBounds(550, 200, 500, 30);
        titleField.setBounds(450,250, 750, 30);
        scroll.setBounds(250, 370, 650, 300);
        AddAnswerButton.setBounds(50, 500, 150, 30);
        AddCommentButton.setBounds(50, 600, 150, 30);
        ViewAnswers.setBounds(50,10, 150, 30);
        ViewComments.setBounds(50, 60, 150, 30);
        backButton.setBounds(900,60,150,30);
        upvote.setBounds(750,60,150,30);
        downvote.setBounds(750, 100, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(label);
        container.add(Label_title);
        container.add(title);
        container.add(descriptionLabel);
        container.add(titleField);
        container.add(AddAnswerButton);
        container.add(scroll);
        container.add(AddCommentButton);
        container.add(ViewAnswers);
        container.add(ViewComments);
        container.add(backButton);
        container.add(name);
        container.add(nameField);
        container.add(upvote);
        container.add(downvote);
        container.add(logoutButton);
    }

    public void addActionEvent() {
      AddAnswerButton.addActionListener(this);
      AddCommentButton.addActionListener(this);
      ViewAnswers.addActionListener(this);
      ViewComments.addActionListener(this);
      backButton.addActionListener(this);
      upvote.addActionListener(this);
      downvote.addActionListener(this);
      logoutButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()== AddAnswerButton){
        new addanswers(question, m, connectionLink, user, pass);
      }
      if(e.getSource()==AddCommentButton){
        new addcomments(question, m, connectionLink, user, pass);
      }
      if(e.getSource()==ViewAnswers){
        new viewanswers(question, m, connectionLink, user, pass);
      }
      if(e.getSource()==ViewComments){
        new viewcomments(question, m, connectionLink, user, pass);
      }
      if(e.getSource()==backButton){
        setVisible(false);
      }
      if(e.getSource()==upvote){
        try{
          this.question.incrementVoteCount();
          updatequestionVote(question,1);
          upvote.setEnabled(false);
          downvote.setEnabled(true);
        }
        catch(Exception err){
          System.err.println(err);
        }
      }
      if(e.getSource()==downvote){
        try{
          this.question.decrementVoteCount();
          updatequestionVote(question, 0);
          upvote.setEnabled(true);
          downvote.setEnabled(false);
        }
        catch(Exception err){
          System.err.println(err);
        }
      }
    }

    public void updatequestionVote(Question q, int voteType) throws Exception {
      String query = "UPDATE question set votecount="+q.voteCount + " where questionid=\'" + q.questionid + "\';";
      String query2;
      if(voteType == 1){
        query2 = "Insert into Vote values(\'"+q.questionid+"\',\'"+q.memid+"\'," + true+");";
      }
      else{
        query2 = "Insert into Vote values(\'"+q.questionid+"\',\'"+q.memid+"\'," + false+");";
      }
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

          statement = connection.createStatement();
          statement.executeUpdate("DELETE FROM Vote WHERE questionid = \'"+q.questionid+"\' and memid=\'" + q.memid + "\';");
          statement = connection.createStatement();
          statement.executeUpdate(query2);

          statement.close();
          connection.commit();
          connection.close();
      }
      catch(Exception e){
          throw e;
      }

    }

    public void getanswer() throws Exception {
      String query = "SELECT * FROM Answers WHERE questionid=\'"+question.questionid+"\';";
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
          Statement statement = connection.createStatement ();
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            this.question.addAnswer(new Answer(rs.getString("answer_text"), rs.getInt("votecount")));
          }
          rs.close();
          statement.close();
          connection.close();
      }
      catch(Exception e){
          throw e;
      }

    }

    public void getcomment() throws Exception {
      String query = "SELECT * FROM Comments WHERE questionid=\'"+question.questionid+"\';";
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
          Statement statement = connection.createStatement ();
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            this.question.addComment(new Comment(rs.getString("text"), rs.getInt("votecount")));
          }
          rs.close();
          statement.close();
          connection.close();
      }
      catch(Exception e){
          throw e;
      }

    }

    public Member getMember() throws Exception{
      String query = "SELECT * FROM users WHERE memid=\'"+question.memid+"\';";
      System.out.println(query);
      try {
          Class.forName("org.postgresql.Driver");
      }
      catch (ClassNotFoundException e) {
          System.err.println (e);
          System.exit (-1);
      }
      try {
          Connection connection = DriverManager.getConnection(this.connectionLink, this.user, this.pass);
          Statement statement = connection.createStatement ();
          ResultSet set = statement.executeQuery(query);
          if(set.next()){
            Member m = new Member(set.getString("name"), set.getString("password"), set.getString("email"), set.getString("phone"), set.getString("memid"), 
            set.getBoolean("isModerator"), set.getBoolean("isAdmin"), set.getBoolean("acc_blocked"), set.getInt("reputation"));
            set.close();
          statement.close();
          connection.close();
          return m;
          }
          else{
            set.close();
            statement.close();
            connection.close();
            return null;
          }
      }
      catch(Exception e){
        throw e;
      }
    }
}
