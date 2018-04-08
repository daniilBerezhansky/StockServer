package net.stock.server.model;


import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity

@Table(name = "Stock")
public class Product {
    public Product() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")

    private long id;

    @Column(name = "Product_name", nullable = false, length = 25)
    private String productName;

    @Column(name = "Amount", nullable = false, length = 50)
    private int amount;

    @Column(name = "Arrival_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArrival;

    @Column(name = "Category", nullable = false, length = 50)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", dateArrival=" + dateArrival +
                '}';
    }
}
