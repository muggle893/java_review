package book;

public class Book {
    private String name;
    private String author;
    private float price;
    private String type;
    private boolean isBorrowed;

    public Book() {
    }

    @Override
    public String toString() {
        String s = "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'';
        if (this.isBorrowed) {
            s += ", 已被借出";
        } else {
            s += ", 未被借出";
        }
        s += "}";
        return s;
    }

    public Book(String name, String author, float price, String type) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.isBorrowed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
