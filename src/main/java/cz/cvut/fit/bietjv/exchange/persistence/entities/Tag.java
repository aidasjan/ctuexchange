package cz.cvut.fit.bietjv.exchange.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tags")
public class Tag {
    @Id
    private int id;
    private String name;

    public Tag() { }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
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
}
