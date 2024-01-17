package ra.service;

import ra.dto.request.CategoriesDTORequest;
import ra.dto.response.CategoriesDTOResponse;
import ra.dto.response.ComboBoxRP;

import java.util.List;

public interface CategoriesService {
    List<CategoriesDTOResponse> findAll();

    CategoriesDTOResponse findById(int id);

    CategoriesDTOResponse save(CategoriesDTORequest categoriesDTORequest);

    CategoriesDTOResponse update(CategoriesDTORequest categoriesDTORequest, int id);

    boolean delete(int id);

    List<CategoriesDTOResponse> find(String direction, String orderBy, int page, int size);
    List<ComboBoxRP> findByStatus();

}