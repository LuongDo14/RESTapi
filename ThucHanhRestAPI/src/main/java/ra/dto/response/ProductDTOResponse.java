package ra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ra.model.Categories;

@Data
@Builder
public class ProductDTOResponse {
    private String productId;
    private String productName;
    private float price;
    private String title;
    private String description;
    private long catalogId;
    private String catalogName;
    private boolean productStatus;


    public ProductDTOResponse(String productId, String productName, float price, String title, String description, long catalogId, String catalogName, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.description = description;
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.productStatus = productStatus;
    }
}
