public class view {
    public static void main(String[] args) {
        // showAccountView theView = new showAccountView(member);
        // Question question=new Question("","");
        String connectionlink = "jdbc:postgresql://127.0.0.1:5555/stackoverflow";
        String user = "postgres";
        String pass = "123456";
        new registrationView(connectionlink, user, pass);
        // theView.setVisible(true);
    }
}
