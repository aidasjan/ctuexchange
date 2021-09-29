package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;

public class CourseDto {
    private String name;
    private String code;
    private int universityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public Course map(University university) {
        return new Course(this.code, this.name, university);
    }

    public int getUniversityId() {
        return universityId;
    }
}
