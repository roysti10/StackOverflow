public class view {
    public static void main(String[] args) {
        // showAccountView theView = new showAccountView(member);
        // Question question=new Question("","");
        String connectionLink = "jdbc:postgresql://127.0.0.1:5433/stackoverflow";
        String user = "postgres";
        String pass = "postgres";
        conn c1 = conn.getInstance(connectionLink, user, pass);
        new registrationView(c1);
        // theView.setVisible(true);
    }
}
