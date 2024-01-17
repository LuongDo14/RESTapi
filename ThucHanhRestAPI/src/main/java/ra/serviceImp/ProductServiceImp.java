package ra.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.dto.request.ProductDTORequest;
import ra.dto.request.ProductDTORequest;
import ra.dto.response.ProductDTOResponse;
import ra.dto.response.ComboBoxRP;
import ra.dto.response.ProductDTOResponse;
import ra.mapper.ProductMapper;
import ra.mapper.ProductMapper;
import ra.model.Product;
import ra.repository.ProductRepository;
import ra.repository.ProductRepository;
import ra.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productMapperRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTOResponse> findAll() {
        List<Product> listProduct = productMapperRepository.findAll();
        return listProduct.stream()
                .map(product -> productMapper.mapperEntityToResponses(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTOResponse findById(int id) {
        //id không tồn tại --> catalog = null
        Optional<Product> optProduct = productMapperRepository.findById(id);
        return optProduct.map(product -> productMapper.mapperEntityToResponse(product)).orElse(null);
    }

    @Override
    public ProductDTOResponse save(ProductDTORequest productMapperDTORequest) {
        Product product =
                productMapperRepository.save(productMapper.mapperRequestToEntity(productMapperDTORequest));
        return productMapper.mapperEntityToResponse(product);
    }

    @Override
    public ProductDTOResponse update(ProductDTORequest productMapperDTORequest, int id) {
        //1. Kiểm tra id có tồn tại không
        boolean checkExist = productMapperRepository.existsById(id);
        //2. Tồn tại thì thực hiện cập nhật
        if (checkExist) {
            Product product = productMapperRepository.save(productMapper.mapperRequestToEntity(productMapperDTORequest));
            return productMapper.mapperEntityToResponse(product);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        boolean checkExist = productMapperRepository.existsById(id);
        if (checkExist) {
            productMapperRepository.deleteById(id);
        }
        return checkExist;
    }

    @Override
    public List<ProductDTOResponse> find(String direction, String orderBy, int page, int size) {
        Pageable pageable;
        if (direction.equals("ASC")) {
            pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
        }
        List<Product> listProduct = productMapperRepository.findAll(pageable).getContent();
        return listProduct.stream().map(product -> productMapper.mapperEntityToResponses(product))
                .collect(Collectors.toList());
    }
}
