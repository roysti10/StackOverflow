import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class homeView extends JFrame implements ActionListener {

    Container container = getContentPane();
    // JFrame frame = new JFrame();
    JPanel pnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel label = new JLabel("STACKOVERFLOW");
    Member m1;
    conn connection;
    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane(pnl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JButton membverView = new JButton("MEMBER");
    JButton createQuestion = new JButton("Create question");
    JButton logout = new JButton("LOGOUT");
    JButton listMembers = new JButton("LIST MEMBERS");
    homeView(Member member,conn c1){
        this.connection = c1;
        m1 = member;
        setLayoutManager();
        setLocationAndSize();
        populateQuestion();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Home Page");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(new java.awt.Color(255,165,0));
        label.setBounds(400,30,300,50);

        scroll.setBounds(50, 150, 1000, 800);
        membverView.setBounds(950, 50, 100, 50);
        createQuestion.setBounds(50,50, 200, 50);
        logout.setBounds(850, 50, 100,50);
        listMembers.setBounds(150, 150, 100, 50);
        
    }

    public void addComponentsToContainer() {
        container.add(label);
        container.add(scroll);
        container.add(membverView);
        container.add(createQuestion);
        container.add(logout);
        if(m1.isAdmin || m1.isModerator){
            container.add(listMembers);
        }
    }

    public void populateQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        try{
          questions = add_questions(questions);
        }
        catch(Exception err){
          System.err.println(err);
        }


        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Hi!");
            System.out.print(questions.get(i).title);
            JLabel questionLabel = new JLabel(questions.get(i).title);
            Question qs = questions.get(i);
            questionLabel.setFont(new Font("Serif", Font.BOLD, 20));
            questionLabel.setBounds(150,(i+2)*100,800,30);
            container.add(questionLabel);

            JLabel questionDes = new JLabel(questions.get(i).description);
            // questionDes.setFont(new Font("Serif", Font.BOLD, 20));
            questionDes.setBounds(150,(i+2)*100 + 50,800,30);
            container.add(questionDes);

            JLabel questionVote = new JLabel(Integer.toString(questions.get(i).voteCount)+"\r\r\n votes");
            questionVote.setBounds(70,(i+2)*100,80,60);
            container.add(questionVote);

            JButton button = new JButton("View Question");
            button.setBounds(800,(i+2)*100+5,150,30);
            container.add(button);

            button.addActionListener(new ActionListener(){
              @Override
              public void actionPerformed(ActionEvent e){
                dispose();
                new showyourquestionView(qs, connection);
                
              }
            });
        }
    }

    public void addActionEvent() {
        // button.addActionListener(this);
        membverView.addActionListener(this);
    //     resetButton.addActionListener(this);
    createQuestion.addActionListener(this);

    logout.addActionListener(this);
    listMembers.addActionListener(this);
    //     showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("View Question");

        if (e.getSource() == membverView) {
            // new memberView();
            // System.out.println("Member View");
            setVisible(false);
            dispose();
            showAccountView sav = new showAccountView(m1, connection);
            sav.setVisible(true);
        }
        if(e.getSource()==createQuestion){
            this.setVisible(false);
            this.dispose();
            createQuestionView cqv = new createQuestionView(new Question("", ""), m1, connection);
            cqv.setVisible(true);
        }
        if(e.getSource()==logout){
          this.setVisible(false);
          this.dispose();
          try{
            this.logout();
          }
          catch(Exception err){
            System.err.println(err);
          }
            new registrationView(connection);
        }
    }

    public ArrayList<Question> add_questions(ArrayList<Question> questions) throws Exception {
      String query = "SELECT * FROM Question;";
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
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            
            Question q  = new Question(rs.getString("title"), rs.getString("description"), rs.getInt("voteCount"));
            System.out.print(q.title);
            q.questionid = rs.getString("questionid");
            q.memid = rs.getString("memid");
            questions.add(q);
          }
          rs.close();
          statement.close();
          return questions;
      }
      catch(Exception e){
          throw e;
      }

    }

    public void logout() throws Exception{
      String query = "delete from currentmemid;";
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
      }
      catch(Exception e){
        throw e;
      }
}
}
