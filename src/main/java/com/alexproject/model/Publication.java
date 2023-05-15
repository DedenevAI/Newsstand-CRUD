package com.alexproject.model;

import java.util.Objects;

public abstract class Publication {

    protected Long id;
    protected String name;
    protected PublicationType type;

    public Publication(String name, PublicationType type) {
        this.name = name;
        this.type = type;
    }

    public Publication(Long id, String name, PublicationType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicationType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
