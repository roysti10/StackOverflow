import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class viewanswers extends JFrame implements ActionListener{
    Container container = getContentPane();
    Question question;
    conn connection;
    Member m;
    String user;
    String pass;
    String connectionLink;
    JLabel label = new JLabel("ANSWER");
    JButton button;
    int voteType;

    viewanswers(Question question, Member m, conn c1){
        this.question = question;
        this.m = m;
        this.connection = c1;
        
          this.voteType  = -1;
        
        setLayoutManager();
        setLocationAndSize();
        setTitle("View answers");
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
        for(int i=0;i<question.answers.size();i++){
            System.out.println(question.answers.get(i).answer_text);
            JLabel answer = new JLabel(question.answers.get(i).answer_text);
            answer.setBounds(100,100 * i +100,300,30);
            container.add(answer);
            JLabel vote = new JLabel(Integer.toString(question.answers.get(i).voteCount));
            vote.setBounds(250,(100 * i + 130),50,30);
            container.add(vote);
    
            Answer ans = question.answers.get(i);
            button = new JButton("UPVOTE");
            button.setBounds(100,100 * i + 160,100,30);
            button.addActionListener(new ActionListener(){
              @Override
              public void actionPerformed(ActionEvent e){
                ans.incrementVoteCount();
                voteType = 1;
                try{
                    updateanswerVote(ans, question.questionid, 1);
                }
                catch(Exception err){
                  System.err.println(err);
                }
                dispose();
                new viewanswers(question, m, connection);
              }
            });
            if(voteType==1){
              button.setEnabled(false);
            }
            container.add(button);
    
            button = new JButton("DOWNVOTE");
            button.setBounds(300,100 * i + 160,150,30);
            button.addActionListener(new ActionListener(){
              @Override
              public void actionPerformed(ActionEvent e){
                ans.decrementVoteCount();
                voteType=0;
                try{
    
                    updateanswerVote(ans, question.questionid, 0);
                }
                catch(Exception err){
                  System.err.println(err);
                }
                dispose();
                new viewanswers(question, m, connection);
              }
            });
            container.add(button);
    }}


    
  
      public void updateanswerVote(Answer com, String questionid, int voteType) throws Exception {
        String query = "UPDATE Answers set voteCount="+com.voteCount + "where questionid=\'" + questionid+"\';";
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