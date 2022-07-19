package model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Category {
    @Id
    private int id;
    private String nameCate;

    public Category(int id, String nameCate) {
        this.id = id;
        this.nameCate = nameCate;
    }

    public Category() {

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
