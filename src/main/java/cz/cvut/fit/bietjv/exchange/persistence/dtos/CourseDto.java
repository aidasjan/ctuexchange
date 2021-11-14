package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;
import cz.cvut.fit.bietjv.exchange.persistence.entities.University;

public class CourseDto implements IEntityDto<Course>{
    private String name;
    private String code;
    private int credits;
    private int universityId;

    public CourseDto(String name, String code, int credits, int universityId) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.universityId = universityId;
    }

    public Course map() {
        return new Course(this.code, this.name, this.credits);
    }

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

    public int getUniversityId() {
        return universityId;
    }
}
