import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class addanswers extends JFrame implements ActionListener{
    Container container = getContentPane();
    Question question;
    Member m;
    String user;
    String pass;
    String connectionLink;
    JLabel answer_label = new JLabel("ANSWER");
    // JLabel answerdescriptionLabel = new JLabel("Answer");
    JTextArea answer_text = new JTextArea();
    // JScrollPane answer_scroll = new JScrollPane (answer_text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JButton postanswer = new JButton("Post Answer");
    conn connection;
    addanswers(Question question, Member m, conn connection){
        this.question = question;
        this.m = m;
        this.connection = connection;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        setTitle("Write your answer");
        setVisible(true);
        setBounds(10, 10, 600, 600);
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
        // answerdescriptionLabel.setFont(new Font("Serif", Font.BOLD, 24));
        // answerdescriptionLabel.setForeground(new java.awt.Color(255,165,0));
        answer_label.setBounds(200,30,200,100);
    //   answerdescriptionLabel.setBounds(200, 130, 100, 100);
        answer_text.setBounds(150, 170, 300, 300);
        postanswer.setBounds(200, 120, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(answer_label);
        // container.add(answerdescriptionLabel);
        container.add(answer_text);
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
            connection.c.setAutoCommit(false);
            Statement statement = connection.createStatement ();
            statement.executeUpdate(query);
            statement.close();
            connection.c.commit();
        }
        catch(Exception e){
            throw e;
        }
  
      }
    }