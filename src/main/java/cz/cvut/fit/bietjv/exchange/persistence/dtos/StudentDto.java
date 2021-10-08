package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Student;

public class StudentDto implements IEntityDto<Student>{
    private String name;
    private String surname;
    private int universityId;

    public Student map() {
        return new Student(this.name, this.surname);
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

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getUniversityId() {
        return universityId;
    }
}
