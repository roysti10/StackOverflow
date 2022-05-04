import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class addanswers extends JFrame implements ActionListener{
    Container container = getContentPane();
    Question question;
    Member m;
    String user;
    String pass;
    String connectionLink;
    JLabel answer_label = new JLabel("ANSWER");
    JLabel answerdescriptionLabel = new JLabel("Answer");
    JTextArea answer_text = new JTextArea();
    JScrollPane answer_scroll = new JScrollPane (answer_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JButton postanswer = new JButton("Post Answer");

    addanswers(Question question, Member m, String connectionLink, String user, String pass){
        this.question = question;
        this.m = m;
        this.connectionLink = connectionLink;
        this.pass = pass;
        this.user = user;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        setTitle("Write your answer");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        answer_text.setLineWrap(false);
        answer_text.setEditable(true);
        answer_text.setVisible(true);

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        answer_label.setFont(new Font("Serif", Font.BOLD, 28));
        answer_label.setForeground(new java.awt.Color(255,165,0));
        answerdescriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
        answerdescriptionLabel.setForeground(new java.awt.Color(255,165,0));
        answer_label.setBounds(200,30,200,100);
      answerdescriptionLabel.setBounds(200, 130, 100, 100);
      answer_scroll.setBounds(150, 170, 300, 400);
      postanswer.setBounds(200, 120, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(answer_label);
        container.add(answerdescriptionLabel);
        container.add(answer_scroll);
        container.add(postanswer);
    }

    public void addActionEvent() {
        postanswer.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==postanswer){
            Answer ans = new Answer(answer_text.getText());
            question.addAnswer(ans);
            try{
              addanswer(ans, question.questionid, m.memid);
            }
            catch(Exception err){
              System.err.println(err);
            }
            this.dispose();
        }
    }

    public void addanswer(Answer ans, String questionid, String memid) throws Exception {
        String query = "INSERT INTO Answers VALUES (\'" + questionid + "\',\'"+memid + "\',\'" + ans.answer_text +"\'," + ans.voteCount +");";
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