package com.example.grato_gv.Model;

public class Question {
    private String sub_id;
    private Integer semester_id;
    private String quiz_name;
    private Integer question_id;
    private String content;

    public Question(String sub_id, Integer semester_id, String quiz_name, Integer question_id, String content) {
        this.sub_id = sub_id;
        this.semester_id = semester_id;
        this.quiz_name = quiz_name;
        this.question_id = question_id;
        this.content = content;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public Integer getSemester_id() {
        return semester_id;
    }

    public void setSemester_id(Integer semester_id) {
        this.semester_id = semester_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Question{" +
                "sub_id='" + sub_id + '\'' +
                ", semester_id=" + semester_id +
                ", quiz_name='" + quiz_name + '\'' +
                ", question_id=" + question_id +
                ", content='" + content + '\'' +
                '}';
    }
}
