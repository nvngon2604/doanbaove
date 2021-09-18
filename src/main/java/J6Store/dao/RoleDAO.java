package J6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import J6Store.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> {

}
