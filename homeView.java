import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class homeView extends JFrame implements ActionListener {

    Container container = getContentPane();
    // JFrame frame = new JFrame();
    JPanel pnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel label = new JLabel("STACKOVERFLOW");
    Member m1;
    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane(pnl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JButton membverView = new JButton("MEMBER");
    JButton createQuestion = new JButton("Create question");
    homeView(Member member){
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
        m1 = member;
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
    }

    public void addComponentsToContainer() {
        container.add(label);
        container.add(scroll);
        container.add(membverView);
        container.add(createQuestion);
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
            questionLabel.setBounds(100,(i+1)*200,100,30);
            container.add(questionLabel);

            JLabel questionDes = new JLabel(questions.get(i).description);
            // questionDes.setFont(new Font("Serif", Font.BOLD, 20));
            questionDes.setBounds(100,(i+1)*200 + 50,100,30);
            container.add(questionDes);

            JLabel questionVote = new JLabel(Integer.toString(questions.get(i).voteCount)+" votes");
            questionVote.setBounds(50,(i+1)*200+5,50,30);
            container.add(questionVote);

            JButton button = new JButton("View Question");
            button.setBounds(800,(i+1)*200+5,50,30);
            pnl.add(button);

            button.addActionListener(new ActionListener(){
              @Override
              public void actionPerformed(ActionEvent e){

                showyourquestionView syqv = new showyourquestionView(qs);
              }
            });
        }
    }

    public void addActionEvent() {
        // button.addActionListener(this);
        membverView.addActionListener(this);
    //     resetButton.addActionListener(this);
    createQuestion.addActionListener(this);
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
            showAccountView sav = new showAccountView(m1);
            sav.setVisible(true);
        }
        if(e.getSource()==createQuestion){
          createQuestionView cqv = new createQuestionView(new Question("", ""));
        }

    }

    public ArrayList<Question> add_questions(ArrayList<Question> questions) throws Exception {
      String query = "SELECT * FROM Questions;";
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
          ResultSet rs = statement.executeQuery(query);
          while(rs.next()){
            questions.add(new Question(rs.getString("title"), rs.getString("description"), rs.getInt("vote_count")));
          }
          rs.close();
          statement.close();
          connection.close();
          return questions;
      }
      catch(Exception e){
          throw e;
      }

    }
}
