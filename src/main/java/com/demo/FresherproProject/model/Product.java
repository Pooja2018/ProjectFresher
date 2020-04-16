package com.demo.FresherproProject.model;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = -1000119078147252957L;

    @Id
    @Column(name = "code", length = 20, nullable = false)
    private Long code;

    @Column(name = "product_code", length = 11, nullable = false)
    private String productCode;

    @Column(name = "product_name", length = 255, nullable = false)
    private String name;

    @Column(name = "product_price", nullable = false)
    private double price;

    @Column(name = "product_gst", nullable = false)
    private double gst;


    public Product() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }


}