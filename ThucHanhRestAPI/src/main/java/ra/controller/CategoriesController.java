package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.dto.request.CategoriesDTORequest;
import ra.dto.response.CategoriesDTOResponse;
import ra.dto.response.ComboBoxRP;
import ra.dto.response.Message;
import ra.service.CategoriesService;

import java.util.List;

@RestController
@RequestMapping("ecommerce/api/v1/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<CategoriesDTOResponse>> find(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "catalogName") String orderBy
    ) {
        List<CategoriesDTOResponse> listCategories = categoriesService.find(direction, orderBy, page, size);
        return new ResponseEntity<>(listCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        CategoriesDTOResponse categoriesDTOResponse = categoriesService.findById(id);
        if (categoriesDTOResponse == null) {
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoriesDTOResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/combobox")
    public List<ComboBoxRP> findByStatus() {
        return categoriesService.findByStatus();
    }
    @PostMapping
    public ResponseEntity<CategoriesDTOResponse> save(@RequestBody CategoriesDTORequest categoriesDTORequest) {
        CategoriesDTOResponse categoriesDTOResponse = categoriesService.save(categoriesDTORequest);
        return new ResponseEntity<>(categoriesDTOResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoriesDTORequest categoriesDTORequest, @PathVariable int id) {
        CategoriesDTOResponse categories = categoriesService.update(categoriesDTORequest, id);
        if (categories == null) {
            //id không tồn tại --> không cập nhật
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        } else {
            //id tồn tại --> cập nhật
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStatus(@RequestBody CategoriesDTORequest categoriesDTORequest, @PathVariable int id) {
        CategoriesDTOResponse categories = categoriesService.update(categoriesDTORequest, id);
        if (categories == null) {
            //id không tồn tại --> không cập nhật
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        } else {
            //id tồn tại --> cập nhật
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }



}
