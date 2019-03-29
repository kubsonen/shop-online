package pl.com.app.entity;

import com.google.gson.annotations.Expose;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends Common{

    @Expose
    @Column(name = "product_code")
    private String productCode;

    @Expose
    @NotEmpty
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "product_image",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "views")
    private Integer viewTimes;

    @Column(name = "last_view_date")
    private Date lastViewDate;

    @Transient
    private List<MultipartFile> tempFiles;

    @Transient
    private String productThumbNailId;

    @Transient
    private Integer countInBasket;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void addImage(Image image){
        if(images == null){
            images = new HashSet<>();
        }
        images.add(image);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Date getLastViewDate() {
        return lastViewDate;
    }

    public void setLastViewDate(Date lastViewDate) {
        this.lastViewDate = lastViewDate;
    }

    public List<MultipartFile> getTempFiles() {
        return tempFiles;
    }

    public void setTempFiles(List<MultipartFile> tempFiles) {
        this.tempFiles = tempFiles;
    }

    public String getProductThumbNailId() {
        return productThumbNailId;
    }

    public void setProductThumbNailId(String productThumbNailId) {
        this.productThumbNailId = productThumbNailId;
    }

    public Integer getCountInBasket() {
        return countInBasket;
    }

    public void addCountInBasket(){
        if(countInBasket == null){
            countInBasket = 1;
        } else {
            countInBasket++;
        }
    }

    public void setCountInBasket(Integer countInBasket) {
        this.countInBasket = countInBasket;
    }
}
