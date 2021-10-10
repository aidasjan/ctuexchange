package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;

public class CourseRecommendationDto implements IEntityDto<Course>{
    private String name;
    private String code;
    private int universityId;
    private int compatibilityScore;

    public CourseRecommendationDto(Course course, int compatibilityScore) {
        this.name = course.getName();
        this.code = course.getCode();
        this.universityId = course.getUniversity().getId();
        this.compatibilityScore = compatibilityScore;
    }

    public Course map() {
        return new Course(this.code, this.name);
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

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(int compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }
}
