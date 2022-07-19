package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    private int idProduct;
    private String nameProduct;
    private int price;
    private String img;
    @ManyToOne
    private Category category;
    private boolean status;

    public Product(int idProduct, String nameProduct, int price, String img, Category category, boolean status) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.img = img;
        this.category = category;
        this.status = status;
    }

    public Product() {

    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
