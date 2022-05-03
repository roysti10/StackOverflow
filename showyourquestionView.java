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
    JButton AddAnswerButton = new JButton("Add Answer");
    JButton ViewAnswers = new JButton("View Answers");
    JButton ViewComments = new JButton("View Comments");
    JButton AddCommentButton = new JButton("Add Comments");
    JButton backButton = new JButton("BACK");
    JButton logoutButton = new JButton("LOGOUT");
    JButton upvote = new JButton("UPVOTE");
    JButton downvote = new JButton("DOWNVOTE");
    JScrollPane scroll;

    showyourquestionView(Question question) {

        this.question = question;
        Member m = null;
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

    public void CommentFrame(){
      JFrame comment = new JFrame("Write your comment");
      JLabel comment_label = new JLabel("COMMENT");
      JLabel commentdescriptionLabel = new JLabel("Comment");
      JTextArea comment_text = new JTextArea();
      JScrollPane comment_scroll = new JScrollPane (comment_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      JButton postcomment = new JButton("Post Comment");
      comment_label.setFont(new Font("Serif", Font.BOLD, 28));
      comment_label.setForeground(new java.awt.Color(255,165,0));
      commentdescriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
      commentdescriptionLabel.setForeground(new java.awt.Color(255,165,0));
      comment.setLayout(null);
      comment.setSize(600, 700);
      comment.setVisible(true);
      comment.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      comment.setResizable(false);
      comment_label.setBounds(200,30,200,100);
      comment.setLocationRelativeTo(null);
      commentdescriptionLabel.setBounds(200, 130, 100, 100);
      comment_scroll.setBounds(150, 170, 300, 400);
      postcomment.setBounds(200, 120, 150, 30);
      comment.add(comment_label);
      comment.add(commentdescriptionLabel);
      comment.add(comment_scroll);
      comment.add(postcomment);
      comment_text.setLineWrap(false);
      comment_text.setEditable(true);
      comment_text.setVisible(true);
      postcomment.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Comment com = new Comment(comment_text.getText());
            question.addComment(com);
            try{
              addcomment(com, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            comment.dispose();
          }
      });
    }
    public void AnswerFrame(){
      JFrame answer = new JFrame("Write your answer");
      JLabel answer_label = new JLabel("ANSWER");
      JLabel answerdescriptionLabel = new JLabel("Answer");
      JTextArea answer_text = new JTextArea();
      JScrollPane answer_scroll = new JScrollPane (answer_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      JButton postanswer = new JButton("Post Answer");
      answer_label.setFont(new Font("Serif", Font.BOLD, 28));
      answer_label.setForeground(new java.awt.Color(255,165,0));
      answerdescriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
      answerdescriptionLabel.setForeground(new java.awt.Color(255,165,0));
      answer.setLayout(null);
      answer.setSize(600, 700);
      answer.setVisible(true);
      answer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      answer.setResizable(false);
      answer.setLocationRelativeTo(null);
      answer_label.setBounds(200,30,200,100);
      answerdescriptionLabel.setBounds(200, 130, 100, 100);
      answer_scroll.setBounds(150, 170, 300, 400);
      postanswer.setBounds(200, 120, 150, 30);
      answer.add(answer_label);
      answer.add(answerdescriptionLabel);
      answer.add(answer_scroll);
      answer.add(postanswer);
      answer_text.setLineWrap(false);
      answer_text.setEditable(true);
      answer_text.setVisible(true);
      postanswer.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            Answer ans = new Answer(answer_text.getText());
            question.addAnswer(ans);
            try{
              addanswer(ans, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            answer.dispose();
          }
      });
  }


    public void ViewCommentFrame(){
      JFrame frame = new JFrame();
      JLabel label = new JLabel("COMMENT");

      label.setFont(new Font("Serif", Font.BOLD, 28));
      label.setForeground(new java.awt.Color(255,165,0));
      label.setBounds(200,30,300,50);
      frame.add(label);
      JButton button;

      for(int i=0;i<question.comments.size();i++){
        System.out.println("Hi");
        JLabel comment = new JLabel(question.comments.get(i).text);
        comment.setBounds(200,100,200,30);
        frame.add(comment);
        JLabel vote = new JLabel(Integer.toString(question.comments.get(i).voteCount));
        vote.setBounds(250,200,50,30);
        frame.add(vote);

        Comment ans = question.comments.get(i);
        button = new JButton("UPVOTE");
        button.setBounds(100,300,100,30);
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
            frame.dispose();
            ViewCommentFrame();
          }
        });
        frame.add(button);

        button = new JButton("DOWNVOTE");
        button.setBounds(300,300,150,30);
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
            frame.dispose();
            ViewCommentFrame();
          }
        });
        frame.add(button);
        }
      frame.setLayout(null);
      frame.setSize(600, 500);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setResizable(false);
    }

    public void ViewAnswerFrame(){
      JFrame frame = new JFrame();
      JLabel label = new JLabel("ANSWER");

      label.setFont(new Font("Serif", Font.BOLD, 28));
      label.setForeground(new java.awt.Color(255,165,0));
      label.setBounds(200,30,300,50);
      frame.add(label);
      JButton button;

      for(int i=0;i<question.answers.size();i++){
        System.out.println(question.answers.get(i).answer_text);
        JLabel comment = new JLabel(question.answers.get(i).answer_text);
        comment.setBounds(200,100,200,30);
        frame.add(comment);
        JLabel vote = new JLabel(Integer.toString(question.answers.get(i).voteCount));
        vote.setBounds(250,200,50,30);
        frame.add(vote);

        Answer ans = question.answers.get(i);
        button = new JButton("UPVOTE");
        button.setBounds(100,300,100,30);
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            ans.incrementVoteCount();
            try{

                updateanswerVote(ans, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            frame.dispose();
            ViewAnswerFrame();
          }
        });
        frame.add(button);

        button = new JButton("DOWNVOTE");
        button.setBounds(300,300,150,30);
        button.addActionListener(new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
            ans.decrementVoteCount();
            try{

                updateanswerVote(ans, question.questionid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            frame.dispose();
            ViewAnswerFrame();
          }
        });
        frame.add(button);
        }
      frame.setLayout(null);
      frame.setSize(600, 500);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()== AddAnswerButton){
        AnswerFrame();
      }
      if(e.getSource()==AddCommentButton){
        CommentFrame();
      }
      if(e.getSource()==ViewAnswers){
        ViewAnswerFrame();
      }
      if(e.getSource()==ViewComments){
        ViewCommentFrame();
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


    public void addanswer(Answer ans, String questionid) throws Exception {
      String query = "INSERT INTO Answers VALUES (\'" + questionid + "\',\'"+ans.answer_text +"\'," + ans.voteCount +");";
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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

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

    public void updatequestionVote(Question q, int voteType) throws Exception {
      String query = "UPDATE question set votecount="+q.voteCount + " where questionid=" + q.questionid + ";";
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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

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

    public void addcomment(Comment com, String questionid) throws Exception {
      String query = "INSERT INTO Comments VALUES (\'" + questionid + "\',\'"+com.text +"\'," + com.voteCount + ");";
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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

                     // build query, here we get info about all databases"

                     // execute query
          Statement statement = connection.createStatement ();
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            this.question.addAnswer(new Answer(rs.getString("answer_text"), rs.getInt("vote_count")));
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
          "jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");

                     // build query, here we get info about all databases"

                     // execute query
          Statement statement = connection.createStatement ();
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            this.question.addComment(new Comment(rs.getString("comment_text"), rs.getInt("vote_count")));
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
          Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5433/stackoverflow", "postgres", "postgres");
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
