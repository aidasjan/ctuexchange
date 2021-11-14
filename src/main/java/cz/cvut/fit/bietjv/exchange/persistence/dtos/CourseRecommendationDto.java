package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Course;

public class CourseRecommendationDto implements IEntityDto<Course>{
    private Course course;
    private int compatibilityScore;

    public CourseRecommendationDto(Course course, int compatibilityScore) {
        this.course = course;
        this.compatibilityScore = compatibilityScore;
    }

    public Course map() {
        return course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(int compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }
}
