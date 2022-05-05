import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createQuestionView extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel label = new JLabel("STACKOVERLOW");
    Question question;
    JLabel Label_title = new JLabel("Write your question");
    JLabel title = new JLabel("Title");
    JLabel descriptionLabel = new JLabel("Description");
    JTextField titleTextField = new JTextField();
    JTextArea descriptionTextField= new JTextArea();
    JButton PostButton = new JButton("Post your question");
    JScrollPane scroll = new JScrollPane (descriptionTextField,
    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    Member member;
    conn connection;
    createQuestionView(Question question, Member m, conn c1) {
        this.question = question;
        this.member = m;
        this.connection =c1;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Write your question");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        descriptionTextField.setLineWrap(false);
        descriptionTextField.setEditable(true);
        descriptionTextField.setVisible(true);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(new java.awt.Color(255,165,0));
        Label_title.setFont(new Font("Serif", Font.BOLD, 28));
        Label_title.setForeground(new java.awt.Color(41,86,143));
        title.setFont(new Font("Serif", Font.BOLD, 22));
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, 22));

        label.setBounds(450,30,700,100);
        Label_title.setBounds(450,120,500,50);
        title.setBounds(150, 250, 100, 30);
        descriptionLabel.setBounds(500, 320, 500, 30);
        titleTextField.setBounds(250,250, 750, 30);
        scroll.setBounds(150, 370, 850, 400);
        PostButton.setBounds(500, 900, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(label);
        container.add(Label_title);
        container.add(title);
        container.add(descriptionLabel);
        container.add(titleTextField);
        container.add(PostButton);
        container.add(scroll);

    }

    public void addActionEvent() {
      PostButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()== PostButton){
          try{
            this.question.title = titleTextField.getText();
            this.question.description = descriptionTextField.getText();
            this.question.memid = member.memid;
            updatequestion();
          }
          catch(Exception ex){
            System.err.println(ex);
          }
          this.setVisible(false);
          showyourquestionView syqv = new showyourquestionView(this.question, connection);
          syqv.setVisible(true);
          this.dispose();
      }
    }


    public void updatequestion() throws Exception {
      String query = "INSERT INTO QUESTION VALUES (\'"+this.question.questionid + "\',\'" + this.question.title +"\',\'"+this.question.description+"\'," + this.question.voteCount+",\'" + this.member.memid + "\');";
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
