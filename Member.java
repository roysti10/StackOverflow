import java.util.ArrayList;

public class Member extends Account {
    public ArrayList<Question> questions;

    public Member(String name, String password, String email, int phone) {
        super(name, password, email, phone);
        this.questions = new ArrayList<Question>();
    }

    public String getEmail() {
        return this.email;
    }

    public boolean createQuestion(Question ques) {
        this.questions.add(ques);
        return true;
    }

    public int getReputation() {
        System.out.println(this.reputation);
        return this.reputation;
    }
}