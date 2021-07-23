package com.example.grato_gv.Model;

import java.util.ArrayList;

public class Exam {
    private String exam_name;
    private Integer no_question;

    public Exam(String exam_name, Integer no_question) {
        this.exam_name = exam_name;
        this.no_question = no_question;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public Integer getNo_question() {
        return no_question;
    }

    public void setNo_question(Integer no_question) {
        this.no_question = no_question;
    }
}
