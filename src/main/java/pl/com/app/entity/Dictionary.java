package pl.com.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "dictionary")
public class Dictionary extends Common{

    @Column(name = "dic_name")
    private String name;

    @Column(name = "dic_value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "parent_dic_id")
    private Dictionary parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Dictionary getParent() {
        return parent;
    }

    public void setParent(Dictionary parent) {
        this.parent = parent;
    }
}
