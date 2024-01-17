package ra.mapper;

import org.springframework.stereotype.Component;
import ra.dto.request.ProductDTORequest;
import ra.dto.response.ComboBoxRP;
import ra.dto.response.ProductDTOResponse;
import ra.model.Categories;
import ra.model.Product;

@Component
public class ProductMapper implements GenericMapper<Product, ProductDTORequest, ProductDTOResponse> {

    @Override
    public Product mapperRequestToEntity(ProductDTORequest productDTORequest) {
        return Product.builder()
                .productId(productDTORequest.getProductId())
                .productName(productDTORequest.getProductName())
                .price(productDTORequest.getPrice())
                .title(productDTORequest.getTitle())
                .description(productDTORequest.getDescription())
                .catalog(productDTORequest.getCatalogId())
                .catalog(productDTORequest.getCatalogName())
                .productStatus(productDTORequest.isProductStatus())
                .build();

    }

    @Override
    public ProductDTOResponse mapperEntityToResponse(Product product) {
        return null;
    }


    @Override
    public ProductDTOResponse mapperEntityToResponses(Product product) {
        return ProductDTOResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .title(product.getTitle())
                .description(product.getDescription())
                .catalogId(product.getCatalog().getCatalogId())
                .catalogName(product.getCatalog().getCatalogName())
                .productStatus(product.isProductStatus())
                .build();
    }

    @Override
    public ComboBoxRP comboBoxRP(Categories categories) {
        return null;
    }
}
