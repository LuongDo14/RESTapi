package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.Categories;
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
