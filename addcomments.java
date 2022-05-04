import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
public class addcomments extends JFrame implements ActionListener{
    Container container = getContentPane();
    Question question;
    Member m;
    String user;
    String pass;
    String connectionLink;
    JLabel comment_label = new JLabel("COMMENT");
    JLabel commentdescriptionLabel = new JLabel("Comment");
    JTextArea comment_text = new JTextArea();
    JScrollPane comment_scroll = new JScrollPane (comment_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JButton postcomment = new JButton("Post Comment");

    addcomments(Question question, Member m, String connectionLink, String user, String pass){
        this.question = question;
        this.m = m;
        this.connectionLink = connectionLink;
        this.pass = pass;
        this.user = user;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        setTitle("Write your question");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        comment_text.setLineWrap(false);
        comment_text.setEditable(true);
        comment_text.setVisible(true);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        comment_label.setFont(new Font("Serif", Font.BOLD, 28));
      comment_label.setForeground(new java.awt.Color(255,165,0));
      commentdescriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
      commentdescriptionLabel.setForeground(new java.awt.Color(255,165,0));
      comment_label.setBounds(200,30,200,100);
      commentdescriptionLabel.setBounds(200, 130, 100, 100);
      comment_scroll.setBounds(150, 170, 300, 400);
      postcomment.setBounds(200, 120, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(comment_label);
        container.add(commentdescriptionLabel);
        container.add(comment_scroll);
        container.add(postcomment);
    }

    public void addActionEvent() {
        postcomment.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==postcomment){
            Comment ans = new Comment(comment_text.getText());
            question.addComment(ans);
            try{
              addcomment(ans, question.questionid, m.memid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            this.dispose();
        }
    }

    public void addcomment(Comment com, String questionid, String memid) throws Exception {
        String query = "INSERT INTO Comments VALUES (\'" + questionid + "\',\'"+ memid + "\',\'" + com.text +"\'," + com.voteCount + ");";
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