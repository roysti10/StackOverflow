public class view {
    public static void main(String[] args) {
        Account theModel = new Account("John", "password", "john@123.com", 12345678);
        Member member = new Member("John", "password", "john@123.com", 12345678);
        // showAccountView theView = new showAccountView(member);
        registrationView view = new registrationView();
        // theView.setVisible(true);
    }
}