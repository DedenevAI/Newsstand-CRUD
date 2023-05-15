package com.alexproject.model;

public class Magazine extends Publication {
    private String publishingHouse;

    public Magazine(String name, String publishingHouse) {
        super(name, PublicationType.MAGAZINE);
        this.publishingHouse = publishingHouse;
    }

    public Magazine(Long id, String name, String publishingHouse) {
        super(id, name, PublicationType.MAGAZINE);
        this.publishingHouse = publishingHouse;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "publishingHouse='" + publishingHouse + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
