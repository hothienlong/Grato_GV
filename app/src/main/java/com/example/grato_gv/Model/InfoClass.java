package com.example.grato_gv.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoClass {
    @SerializedName("class_id")
    @Expose
    private String classId;
    private Integer count;

    public InfoClass(String classId, Integer count){
        this.classId = classId;
        this.count = count;
    }

    public void setClassName(String className){
        this.classId = className;
    }
    public String getClassName(){
        return this.classId;
    }

    public void setNowStudent(Integer nowStudent){
        this.count = nowStudent;
    }
    public Integer getNowStudent(){
        return this.count;
    }

    @Override
    public String toString() {
        return "InfoClass{" +
                "classId='" + classId + '\'' +
                ", count=" + count +
                '}';
    }
}
