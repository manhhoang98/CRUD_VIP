package codegym.config.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;

import javax.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Size(min = 1, max = 25, message = "max 25 min 1")
    private String name;
    private String img;
    private Boolean status;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(int id, String name, String img, Boolean status, Category category) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.status = status;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
