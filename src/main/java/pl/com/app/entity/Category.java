package pl.com.app.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "category")
public class Category extends Common{

    @Expose
    @NotEmpty(message = "Must be not empty.")
    @Column(name = "acronym")
    private String acronym;

    @Expose
    @NotEmpty(message = "Must be not empty.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Mus be not empty.")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category parent;

    @Transient
    private String parentId;

    @Expose
    @Transient
    private int countOfVisit;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public int getCountOfVisit() {
        return countOfVisit;
    }

    public void setCountOfVisit(int countOfVisit) {
        this.countOfVisit = countOfVisit;
    }
}
