package cz.cvut.fit.bietjv.exchange.persistence.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "students")
public class Student {
    @Id
    private int id;
    private String name;
    private String surname;
    private int universityId;

    public Student() { }

    public Student(int id, String name, String surname, int universityId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.universityId = universityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }
}
