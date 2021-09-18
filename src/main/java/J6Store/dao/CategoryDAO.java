package J6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import J6Store.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{

}
