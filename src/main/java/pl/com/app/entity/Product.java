package pl.com.app.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends Common{

    @Column(name = "product_code")
    private String productCode;

    @NotEmpty
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "product_image",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private List<MultipartFile> tempFiles;

    @Transient
    private String productThumbNailId;

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
}
