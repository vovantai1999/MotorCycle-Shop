package model;

public class Product {
    private String id;
    private String img;
    private String title;
    private String quantity;
    private String price;

    public Product() {
    }

    public Product(String img, String title, String quantity, String price) {
        this.img = img;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String id, String img, String title, String quantity, String price) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
