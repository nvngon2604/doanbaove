package J6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import J6Store.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
	
	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='True'")
	long orderDetailSuccess();
	
	@Query(nativeQuery = true, value = "select count(status) from OrderDetails where status='False'")
	long orderDetailFail();
	
	@Query(nativeQuery = true, value = "select SUM(price) from OrderDetails")
	long totalRevenue();
	
	@Query(nativeQuery = true, value = "select count(quantity) from orderDetails")
	long totalQuantity();
}
