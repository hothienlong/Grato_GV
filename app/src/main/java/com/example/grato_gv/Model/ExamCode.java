package com.example.grato_gv.Model;

public class ExamCode {
    private String exam_code_id;
    private String key_answer;

    public ExamCode(String exam_code_id, String key_answer) {
        this.exam_code_id = exam_code_id;
        this.key_answer = key_answer;
    }

    public String getExam_code_id() {
        return exam_code_id;
    }

    public void setExam_code_id(String exam_code_id) {
        this.exam_code_id = exam_code_id;
    }

    public String getKey_answer() {
        return key_answer;
    }

    public void setKey_answer(String key_answer) {
        this.key_answer = key_answer;
    }
}
