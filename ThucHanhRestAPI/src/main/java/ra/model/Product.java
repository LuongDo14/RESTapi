package ra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @Column(name = "product_id",columnDefinition = "varchar(5)")
    private String productId;
    @Column(name = "product_name",columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String productName;
    @Column(name = "product_price",columnDefinition = "float",nullable = false)
    private float price;
    @Column(name = "product_title",columnDefinition = "varchar(200)",nullable = false)
    private String title;
    @Column(name = "product_description",columnDefinition = "text",nullable = false)
    private String description;
    @Column(name = "created")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date created;
    @Column(name = "updated")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    private Date updated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id",referencedColumnName = "catalog_id")
    private Categories catalog;
    @Column(name = "product_status",columnDefinition = "bit")
    private boolean productStatus = false;
}
