package cz.cvut.fit.bietjv.exchange.persistence.dtos;

import cz.cvut.fit.bietjv.exchange.persistence.entities.Tag;

public class TagDto implements IEntityDto<Tag> {
    private String name;

    @Override
    public Tag map() {
        return new Tag(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
