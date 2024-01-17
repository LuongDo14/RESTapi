package ra.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.dto.request.CategoriesDTORequest;
import ra.dto.request.ComboBoxRQ;
import ra.dto.response.CategoriesDTOResponse;
import ra.dto.response.ComboBoxRP;
import ra.mapper.CategoriesMapper;
import ra.model.Categories;
import ra.repository.CategoriesRepository;
import ra.service.CategoriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public List<CategoriesDTOResponse> findAll() {
        List<Categories> listCategories = categoriesRepository.findAll();
        return listCategories.stream()
                .map(categories -> categoriesMapper.mapperEntityToResponse(categories))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesDTOResponse findById(int id) {
        //id không tồn tại --> catalog = null
        Optional<Categories> optCatalog = categoriesRepository.findById(id);
        return optCatalog.map(categories -> categoriesMapper.mapperEntityToResponse(categories)).orElse(null);
    }
    @Override
    public List<ComboBoxRP> findByStatus() {
        List<Categories> categoriesList = categoriesRepository.findAll();

        List<Categories> filteredCategoriesList = categoriesList.stream()
                .filter(categories -> categories.isCatalogStatus())
                .collect(Collectors.toList());

        return filteredCategoriesList.stream()
                .map(categories -> categoriesMapper.comboBoxRP(categories))
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public CategoriesDTOResponse save(CategoriesDTORequest categoriesDTORequest) {
        Categories categories =
                categoriesRepository.save(categoriesMapper.mapperRequestToEntity(categoriesDTORequest));
        return categoriesMapper.mapperEntityToResponse(categories);
    }

    @Override
    public CategoriesDTOResponse update(CategoriesDTORequest categoriesDTORequest, int id) {
        //1. Kiểm tra id có tồn tại không
        boolean checkExist = categoriesRepository.existsById(id);
        //2. Tồn tại thì thực hiện cập nhật
        if (checkExist) {
            Categories categories = categoriesRepository.save(categoriesMapper.mapperRequestToEntity(categoriesDTORequest));
            return categoriesMapper.mapperEntityToResponse(categories);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        boolean checkExist = categoriesRepository.existsById(id);
        if (checkExist) {
            categoriesRepository.deleteById(id);
        }
        return checkExist;
    }

    @Override
    public List<CategoriesDTOResponse> find(String direction, String orderBy, int page, int size) {
        Pageable pageable;
        if (direction.equals("ASC")) {
            pageable = PageRequest.of(page, size, Sort.by(orderBy).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
        }
        List<Categories> listCategories = categoriesRepository.findAll(pageable).getContent();
        return listCategories.stream().map(categories -> categoriesMapper.mapperEntityToResponse(categories))
                .collect(Collectors.toList());
    }
}