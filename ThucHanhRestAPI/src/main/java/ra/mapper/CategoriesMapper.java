package ra.mapper;

import org.springframework.stereotype.Component;
import ra.dto.request.CategoriesDTORequest;
import ra.dto.response.CategoriesDTOResponse;
import ra.dto.response.ComboBoxRP;
import ra.dto.response.ProductDTOResponse;
import ra.model.Categories;
import ra.model.Product;

@Component
public class CategoriesMapper implements GenericMapper<Categories, CategoriesDTORequest, CategoriesDTOResponse> {

    @Override
    public Categories mapperRequestToEntity(CategoriesDTORequest categoriesDTORequest) {
        return Categories.builder().catalogId(categoriesDTORequest.getCatalogId())
                .catalogName(categoriesDTORequest.getCatalogName())
                .priority(categoriesDTORequest.getPriority())
                .descriptions(categoriesDTORequest.getDescription())
                .catalogStatus(categoriesDTORequest.isCatalogStatus())
                .build();
    }

    @Override
    public CategoriesDTOResponse mapperEntityToResponse(Categories categories) {
        return new CategoriesDTOResponse(
                categories.getCatalogId(),
                categories.getCatalogName(),
                categories.getPriority(),
                categories.getDescriptions(),
                categories.isCatalogStatus()
        );
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
        return new ComboBoxRP(
                categories.getCatalogId(),
                categories.getCatalogName()
        );
    }
}
