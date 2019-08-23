package cn.jun.word.excel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Person {

    private Date date;
    private String name;
    private String no;
    private Map<String,String> project;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, String> getProject() {
        return project;
    }

    public void setProject(Map<String, String> project) {
        this.project = project;
    }
}
