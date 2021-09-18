package J6Store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import J6Store.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

	@Query("SELECT p from Product p where p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	List<Product> findByNameContaining(String name);
	
	Page<Product> findBynameContaining(String name, Pageable pageable);

}
