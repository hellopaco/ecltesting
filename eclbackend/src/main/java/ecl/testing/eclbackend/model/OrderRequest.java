package ecl.testing.eclbackend.model;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private int quantity;
}

