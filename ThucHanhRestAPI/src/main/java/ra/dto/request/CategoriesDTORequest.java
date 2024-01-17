package ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@AllArgsConstructor
@Data

public class CategoriesDTORequest {
    private int catalogId;
    private String catalogName;
    private int priority;
    private String description;
    private boolean catalogStatus;
}
