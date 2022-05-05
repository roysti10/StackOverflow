package trash;
import javax.swing.*;

import Comment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class showyourcommentView extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel label = new JLabel("STACKOVERLOW");
    Comment ans;
    JLabel Label_title = new JLabel("View Comment");
    JLabel descriptionLabel = new JLabel("Description");
    JLabel titleField;
    JTextArea descriptionField;
    JButton close = new JButton("close");
    JScrollPane scroll;

    showyourcommentView(Comment ans) {

        this.ans = ans;
        descriptionField = new JTextArea(this.ans.text);
        scroll = new JScrollPane (descriptionField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, 22));

        label.setBounds(450,30,700,100);
        Label_title.setBounds(450,120,500,50);
        descriptionLabel.setBounds(500, 320, 500, 30);
        scroll.setBounds(150, 370, 850, 500);
        close.setBounds(0, 60, 150, 30);
    }

    public void addComponentsToContainer() {
        container.add(label);
        container.add(Label_title);
        container.add(descriptionLabel);
        container.add(scroll);
        container.add(close);
    }

    public void addActionEvent() {
      close.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()== close){
        this.dispose();
      }
    }

}
