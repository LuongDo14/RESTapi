package ra.mapper;

import ra.dto.response.ComboBoxRP;
import ra.dto.response.ProductDTOResponse;
import ra.model.Categories;
import ra.model.Product;

public interface GenericMapper<E, R, S> {
    //1. Convert request to entity
    E mapperRequestToEntity(R r);

    //2. Convert entity to response
    S mapperEntityToResponse(E e);



    ProductDTOResponse mapperEntityToResponses(Product product);

    ComboBoxRP comboBoxRP (Categories categories);
}