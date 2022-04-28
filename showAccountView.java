import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class showAccountView extends JFrame implements ActionListener {
 
    Container container = getContentPane();
    JLabel label = new JLabel("MEMBER DETAILS");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel emailLabel = new JLabel("EMAIL");
    JLabel phoneLabel = new JLabel("PHONE");
    JLabel reputationsLabel = new JLabel("REPUTATION");
    JLabel userTextField = new JLabel();
    JPasswordField passwordField = new JPasswordField();
    JLabel emailTextField = new JLabel();
    JLabel phoneTextField = new JLabel();
    JLabel reputationsTextField = new JLabel();
    JButton updateButton = new JButton("UPDATE");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");

    JButton backButton = new JButton("BACK");
    JLabel questionLabel = new JLabel("YOUR QUESTIONS");
    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane (textArea, 
   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    showAccountView(Member account) {
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
        userTextField.setText(account.name);
        passwordField.setText(account.password);
        emailTextField.setText(account.email);
        phoneTextField.setText(Integer.toString(account.phone));
        reputationsTextField.setText(Integer.toString(account.reputation));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(new java.awt.Color(41,86,143));

        label.setBounds(400,30,300,50);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        emailLabel.setBounds(50, 290, 100, 30);
        phoneLabel.setBounds(50, 360, 100, 30);
        reputationsLabel.setBounds(50, 430, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        emailTextField.setBounds(150, 290, 150, 30);
        phoneTextField.setBounds(150, 360, 150, 30);
        reputationsTextField.setBounds(150, 430, 150, 30);
        showPassword.setBounds(150, 500, 150, 30);
        updateButton.setBounds(50, 600, 100, 30);
        resetButton.setBounds(200, 600, 100, 30);
        questionLabel.setBounds(500, 100, 150, 30);
        scroll.setBounds(500, 150, 500, 800);
    }
 
    public void addComponentsToContainer() {
        container.add(label);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(emailLabel);
        container.add(phoneLabel);
        container.add(reputationsLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(emailTextField);
        container.add(phoneTextField);
        container.add(reputationsTextField);
        container.add(showPassword);
        container.add(updateButton);
        container.add(resetButton);
        container.add(questionLabel);
        container.add(scroll);
    }
 
    public void addActionEvent() {
        updateButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public void populateQuestion() {
        Model m = new Model();

        ArrayList<Question> questions = new ArrayList<Question>();

        for (int i = 0; i < questions.size(); i++) {

            textArea.append(i + " " + questions.get(i).title + "\n");

        }
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == updateButton) {
            updateDetails();
        }
        if (e.getSource() == resetButton) {
            resetPasswordFrame();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }

    public void resetPasswordFrame() {
        JFrame resetPassword = new JFrame("Reset Password");
        JLabel newPasswordLabel = new JLabel("New Password");
        JPasswordField newPasswordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton resetButton = new JButton("RESET");
        resetPassword.setLayout(null);
        resetPassword.setSize(400, 400);
        resetPassword.setVisible(true);
        resetPassword.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resetPassword.setResizable(false);
        resetPassword.setLocationRelativeTo(null);
        newPasswordLabel.setBounds(50, 50, 100, 30);
        newPasswordField.setBounds(150, 50, 150, 30);
        confirmPasswordLabel.setBounds(50, 100, 100, 30);
        confirmPasswordField.setBounds(150, 100, 150, 30);
        resetButton.setBounds(150, 150, 100, 30);
        resetPassword.add(newPasswordLabel);
        resetPassword.add(newPasswordField);
        resetPassword.add(confirmPasswordLabel);
        resetPassword.add(confirmPasswordField);
        resetPassword.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                    passwordField.setText(newPasswordField.getText());
                    JOptionPane.showMessageDialog(resetPassword, "Password Changed Successfully");
                    resetPassword.dispose();
                } else {
                    JOptionPane.showMessageDialog(resetPassword, "Password Mismatch");
                }
            }
        });
    }

    public void updateDetails() {
        JFrame updateDetails = new JFrame("Update Details");
        JLabel newEmailLabel = new JLabel("New Email");
        JTextField newEmailField = new JTextField();
        JLabel newPhoneLabel = new JLabel("New Phone");
        JTextField newPhoneField = new JTextField();
        JButton button = new JButton("UPDATE");
        updateDetails.setLayout(null);
        updateDetails.setSize(400, 400);
        updateDetails.setVisible(true);
        updateDetails.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        updateDetails.setResizable(false);
        updateDetails.setLocationRelativeTo(null);
        newEmailLabel.setBounds(50, 50, 100, 30);
        newEmailField.setBounds(150, 50, 150, 30);
        newPhoneLabel.setBounds(50, 100, 100, 30);
        newPhoneField.setBounds(150, 100, 150, 30);
        button.setBounds(150, 150, 100, 30);
        updateDetails.add(newEmailLabel);
        updateDetails.add(newEmailField);
        updateDetails.add(newPhoneLabel);
        updateDetails.add(newPhoneField);
        updateDetails.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailTextField.setText(newEmailField.getText());
                phoneTextField.setText(newPhoneField.getText());
                emailLabel.setText(newEmailField.getText());
                phoneLabel.setText(newPhoneField.getText());
                JOptionPane.showMessageDialog(updateDetails, "Details Updated Successfully");
                updateDetails.dispose();
            }
        });

    }
 
}