package cz.cvut.fit.bietjv.exchange.persistence.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "courses")
public class Course {
    @Id
    private int id;
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_tags",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    public Course() { }

    public Course(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
