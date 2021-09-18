package J6Store.controller;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import J6Store.entity.Product;
import J6Store.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		}else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product/detail";
	}
	
	@GetMapping("product/list/search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name) {
		List<Product> list = null;
		
		if(org.springframework.util.StringUtils.hasText(name)) {
			list = productService.findByNameContaining(name);
		}else {
			model.addAttribute("message", "Hiện không có sản phẩm nào bạn cần tìm kiếm !");
			list = productService.findAll();
		}
		
		model.addAttribute("items", list);
		
		
		return "product/list";
	}
	
	@GetMapping("view/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = (Pageable) PageRequest.of(p.orElse(0), 12);
		Page<Product> page = productService.findAllPro((org.springframework.data.domain.Pageable) pageable);
		
		model.addAttribute("items", page);
				
		return "product/list";
	}
	
	
	
	
}
