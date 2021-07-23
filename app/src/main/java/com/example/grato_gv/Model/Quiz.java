package com.example.grato_gv.Model;

public class Quiz {
    private String quiz_name;
    private String max_time;
    private Integer no_question;
    private String deadline;

    public Quiz(String quiz_name, String max_time, Integer no_question, String deadline) {
        this.quiz_name = quiz_name;
        this.max_time = max_time;
        this.no_question = no_question;
        this.deadline = deadline;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getMax_time() {
        return max_time;
    }

    public void setMax_time(String max_time) {
        this.max_time = max_time;
    }

    public Integer getNo_question() {
        return no_question;
    }

    public void setNo_question(Integer no_question) {
        this.no_question = no_question;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
