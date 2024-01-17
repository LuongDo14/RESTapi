package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.ProductDTORequest;
import ra.dto.response.CategoriesDTOResponse;
import ra.dto.response.Message;
import ra.dto.response.ProductDTOResponse;
import ra.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTOResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "productName") String orderBy
    ) {
        List<ProductDTOResponse> productDTOResponseList = productService.find(direction, orderBy, page, size);
        return new ResponseEntity<>(productDTOResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable int id) {
        ProductDTOResponse product = productService.findById(id);
        if (product == null) {
            //id không đúng --> trả về đối tượng message
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        } else {
            //id đúng --> trả về đối tượng CategoriesDTOResponse
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTOResponse> save(@RequestBody ProductDTORequest productDTORequest) {
        ProductDTOResponse productDTOResponse = productService.save(productDTORequest);
        return  new ResponseEntity<>(productDTOResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDTORequest productDTORequest, @PathVariable int id) {
        ProductDTOResponse productDTOResponse = productService.update(productDTORequest, id);
        if (productDTOResponse == null) {
            return new ResponseEntity<>(new Message("Id not fount"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productDTOResponse, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable int id) {
        boolean result = productService.delete(id);
        if (result) {
            return new ResponseEntity<>(new Message("Delete succesful"), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        }
    }
}
