package com.example.grato_gv.Model;

public class ExamReview {
    private String student_id;
    private String student_name;
    private String exam_code_id;
    private Double score;

    public ExamReview(String student_id, String student_name, String exam_code_id, Double score) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.exam_code_id = exam_code_id;
        this.score = score;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getExam_code_id() {
        return exam_code_id;
    }

    public void setExam_code_id(String exam_code_id) {
        this.exam_code_id = exam_code_id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
