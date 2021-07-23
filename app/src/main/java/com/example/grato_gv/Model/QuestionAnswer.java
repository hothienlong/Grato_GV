package com.example.grato_gv.Model;

public class QuestionAnswer {
    private Integer question_id;
    private String question_content;
    private char answer_id;
    private String answer_content;
    private Integer right_answer;

    public QuestionAnswer(Integer question_id, String question_content, char answer_id, String answer_content, Integer right_answer) {
        this.question_id = question_id;
        this.question_content = question_content;
        this.answer_id = answer_id;
        this.answer_content = answer_content;
        this.right_answer = right_answer;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public char getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(char answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public Integer getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(Integer right_answer) {
        this.right_answer = right_answer;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "question_id=" + question_id +
                ", question_content='" + question_content + '\'' +
                ", answer_id=" + answer_id +
                ", answer_content='" + answer_content + '\'' +
                ", right_answer=" + right_answer +
                '}';
    }
}
