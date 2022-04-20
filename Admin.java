import java.util.ArrayList;

public class Admin extends Member {
    public ArrayList<Member> blocked;

    public Admin(String name, String password, String email, int phone) {
        super(name, password, email, phone);
        this.blocked = new ArrayList<Member>();
    }

    public boolean blockMember(Member member) {
        this.blocked.add(member);
        return true;
    }

    public boolean unblockMember(Member member) {
        if (this.blocked.contains(member)) {
            this.blocked.remove(member);
            return true;
        }
        return false;
    }

    public boolean deleteQuestion(Question question) {
        if (this.questions.contains(question)) {
            this.questions.remove(question);
            return true;
        }
        return false;
    }
}