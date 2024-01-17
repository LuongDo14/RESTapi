package ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ra.model.Categories;

import java.util.Date;
@AllArgsConstructor
@Data
public class ProductDTORequest {
    private String productId;
    private String productName;
    private float price;
    private String title;
    private String description;
    private Categories catalogId;
    private Categories catalogName;
    private boolean productStatus;
}
