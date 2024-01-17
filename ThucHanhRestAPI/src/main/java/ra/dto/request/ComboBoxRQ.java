package ra.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ComboBoxRQ {
    private int catalogId;
    private String catalogName;
}
