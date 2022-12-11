package com.setbitzero.e_student.models;

public class StudentModel {
   private String id;
   private String name;
   private String age;
   private String department;
   private String semester;

    public StudentModel() {
    }

    public StudentModel(String id, String name, String age, String department, String semester) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.semester = semester;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
