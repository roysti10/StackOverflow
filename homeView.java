import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeView extends JFrame implements ActionListener {
    
    Container container = getContentPane();
    // JFrame frame = new JFrame();
    JPanel pnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel label = new JLabel("STACKOVERFLOW");

    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane(pnl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JButton membverView = new JButton("MEMBER");

    homeView(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setTitle("Account Information");
        setVisible(true);
        setBounds(10, 10, 1100, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        populateQuestion();
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
    }
 
    public void addComponentsToContainer() {
        container.add(label);
        container.add(scroll);
        container.add(membverView);
        // container.add(pnl);
    }

    public void populateQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();

        
        JButton button = new JButton("View Question");
        button.setBounds(1000,(0+1)*200+5,50,30);
        pnl.add(button);

        button.addActionListener(this);

        for (int i = 0; i < questions.size(); i++) {

            JLabel questionLabel = new JLabel(questions.get(i).title);
            questionLabel.setFont(new Font("Serif", Font.BOLD, 20));
            questionLabel.setBounds(100,(i+1)*200,100,30);
            this.pnl.add(questionLabel);

            JLabel questionDes = new JLabel(questions.get(i).description);
            // questionDes.setFont(new Font("Serif", Font.BOLD, 20));
            questionDes.setBounds(100,(i+1)*200 + 50,100,30);
            this.pnl.add(questionDes);

            JLabel questionVote = new JLabel(Integer.toString(questions.get(i).voteCount)+" votes");
            questionVote.setBounds(50,(i+1)*200+5,50,30);
            this.pnl.add(questionVote);

        }
    }
 
    public void addActionEvent() {
        // button.addActionListener(this);
        membverView.addActionListener(this);
    //     resetButton.addActionListener(this);
    //     showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("View Question");

        if (e.getSource() == membverView) {
            // new memberView();
            System.out.println("Member View");
        }
        
    }
}
