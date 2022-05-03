import java.util.ArrayList;

public class Member extends Account {
    public ArrayList<Question> questions;

    public Member(String name, String password, String email, String phone, String memid, boolean isModerator, boolean isAdmin, boolean acc_blocked, int reputation) {
        super(name, password, email, phone, memid, isModerator, isAdmin, acc_blocked, reputation);
        this.questions = new ArrayList<Question>();
    }

    public Member(String name, String password, String email, String phone) {
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

    public void incReputation(){
        this.reputation = this.reputation + 1;
        if(!this.isAdmin){
            if(!this.checkAdmin()){
                this.checkModerator();
            }
        }
    }

    public void decReputation(){
        this.reputation = this.reputation - 1;
        if(!this.isAdmin){
            if(!this.checkAdmin()){
                this.checkModerator();
            }
        }
    }

    public boolean blockMember(Member member){
        if(this.isAdmin && member.status == Member.accStatus.ACTIVE){
            member.status = Member.accStatus.BLOCKED;
            return true;
        }
        return false;
    }

    public boolean unblockMember(Member member){
        if(this.isAdmin && member.status == Member.accStatus.BLOCKED){
            member.status = Member.accStatus.ACTIVE;
            return true;
        }
        return false;
    }

    public boolean closeQuestion(Question ques){
        if(this.isModerator && ques.status == QuestionStatus.Open){
            ques.status = QuestionStatus.Closed;
            return true;
        }
        return false;
    }

    public boolean openQuestion(Question ques){
        if(this.isModerator && ques.status == QuestionStatus.Closed){
            ques.status = QuestionStatus.Open;
            return true;
        }
        return false;
    }
}
