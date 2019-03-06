package pl.com.app.entity;

public class Dictionary extends Common{

    private String name;

    private String value;

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
