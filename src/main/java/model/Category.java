package model;

public class Category {
    private int id;
    private String nameCate;

    public Category(int id, String nameCate) {
        this.id = id;
        this.nameCate = nameCate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }
}
