package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;

public class CourseRecommendationDto implements IEntityDto<Course>{
    private String name;
    private String code;
    private int credits;
    private int universityId;
    private int compatibilityScore;

    public CourseRecommendationDto(Course course, int compatibilityScore) {
        this.name = course.getName();
        this.code = course.getCode();
        this.credits = course.getCredits();
        this.universityId = course.getUniversity().getId();
        this.compatibilityScore = compatibilityScore;
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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
