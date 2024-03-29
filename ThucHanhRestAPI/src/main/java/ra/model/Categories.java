package ra.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private int catalogId;
    @Column(name = "catalog_name",columnDefinition = "varchar(100)", nullable = false,unique = true)
    private String catalogName;
    @Column(name = "priority")
    private int priority;
    @Column(columnDefinition = "text")
    private String descriptions;
    @Column(name = "catalog_created")
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    @CreationTimestamp
    private Date created;
    @Column(name = "catalog_status")
    private boolean catalogStatus = false;
    @OneToMany(mappedBy = "catalog",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> listProduct = new ArrayList<>();
}
