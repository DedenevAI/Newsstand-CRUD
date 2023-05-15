package com.alexproject.model;

public class Newspaper extends Publication{
    private Integer issueNumber;

    public Newspaper(String name, Integer issueNumber) {
        super(name, PublicationType.NEWSPAPER);
        this.issueNumber = issueNumber;
    }

    public Newspaper(Long id, String name, Integer issueNumber) {
        super(id, name, PublicationType.NEWSPAPER);
        this.issueNumber = issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "issueNumber=" + issueNumber +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
