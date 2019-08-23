package cn.jun.mysql.vo;


import java.util.Date;

public class User {

    private String id;
    private String deptno;
    private String comm;
    private String salary;
    private String name;
    private String sex;
    private String age;
    private Date birth;
    private String description;

    public User(){

    }

    public User(String name, String age, String sex, Date birth, String description) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
        this.description = description;
    }

    public User(String id, String name, String age, String sex, Date birth, String description) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birth = birth;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", birth='" + birth + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
