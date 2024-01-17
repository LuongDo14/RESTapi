package ra.service;


import ra.dto.request.ProductDTORequest;
import ra.dto.response.ProductDTOResponse;
import ra.dto.response.ComboBoxRP;

import java.util.List;

public interface ProductService {
    List<ProductDTOResponse> findAll();

    ProductDTOResponse findById(int id);

    ProductDTOResponse save(ProductDTORequest productDTORequest);

    ProductDTOResponse update(ProductDTORequest productDTORequest, int id);

    boolean delete(int id);

    List<ProductDTOResponse> find(String direction, String orderBy, int page, int size);

}
