import java.time.LocalTime;
import java.util.ArrayList;

enum QuestionStatus{
  Open, Closed, OnHold, Delete
}

enum QuestionClosingRemark{
  Duplicate, OffTopic, TooBroad, NotConstructive, NotRealQuestion, OpinionBased
}

interface Search{
  void search(String query);
}

class Question{
  String title;
  String description;
  int viewCount;
  int voteCount;
  int flagCount = 0;
  LocalTime createTime;
  QuestionClosingRemark closingRemark;
  QuestionStatus status;
  ArrayList<Comment> comments;
  ArrayList<Answer> answers;

  Question(String title, String description){
    this.title = title;
    this.description = description;
    this.createTime = LocalTime.now();
    this.viewCount = 0;
    this.flagCount = 0;
    this.voteCount = 0;
    this.status = QuestionStatus.Open;
    comments = new ArrayList<Comment>();
    answers = new ArrayList<Answer>();
  }

  boolean close(QuestionClosingRemark remark){
    this.closingRemark = remark;
    this.status = QuestionStatus.Closed;
    return true;
  }

  void incrementVoteCount(){
    this.voteCount+=1;
  }

  void incrementViewCount(){
    this.viewCount+=1;
  }

  void addComment(Comment comment){
    this.comments.add(comment);
  }

  void addAnswer(Answer answer){
    this.answers.add(answer);
  }

}

class Comment{
  String text;
  LocalTime creationTime;
  int flagCount;
  int voteCount;

  Comment(String text){
    this.text = text;
    this.creationTime = LocalTime.now();
    this.flagCount = 0;
    this.voteCount = 0;
  }

  void incrementVoteCount(){
    this.voteCount+=1;
  }

  void incrementFlagCount(){
    this.flagCount+=1;
  }
}

class Answer{
  String answer_text;
  boolean accepted;
  int voteCount = 0;
  int flagCount = 0;
  LocalTime createTime;

  Answer(String answer_text){
    this.answer_text = answer_text;
    this.accepted = false;
    this.voteCount = 0;
    this.flagCount = 0;
    this.createTime = LocalTime.now();
  }

  void incrementVoteCount(){
    this.voteCount+=1;
  }


  void incrementFlagCount(){
    this.flagCount+=1;
  }
}