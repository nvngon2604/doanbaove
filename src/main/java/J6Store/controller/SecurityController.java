package J6Store.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import J6Store.dto.AccountDto;
import J6Store.entity.Account;
import J6Store.service.AccountService;
import J6Store.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	UserService service;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Vui lòng đăng nhập để tiếp tục</b>");
		return "security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Đăng nhập thành công</b>");
		return "redirect:/home/index";
	}
	
	@Autowired
	UserService userService;
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/security/login/success";
	}
	
	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Tên đăng nhập hoặc mật khẩu không đúng !</b>");
		return "security/login";
	}
	
	@RequestMapping("/security/unauthorized")
	public String unauthorized(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Không có quyền truy cập</b>");
		return "security/404";
	}
	
	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Đăng xuất thành công</b>");
		return "security/login";
	}
	
	@RequestMapping("/security/register/form")
	public String registerForm(Model model) {
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Đăng ký tài khoản để có thể mua hàng</b>");
		return "security/register";
	}
	
	@RequestMapping("/security/register/update")
	public String registerUpdate(Model model) {
		
		return "security/profile";
	}
	
	@PostMapping("/security/register/addOrUpdate")
	public String register(Model model, @ModelAttribute("account") AccountDto dto,
			@RequestParam("password") String password, @RequestParam("repassword") String repassword) {
		if(!password.equals(repassword)) {
			model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Mật khẩu không trùng khớp !</b>");
			System.out.println("Mật khẩu không trùng khớp !");
			return "security/register";
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Lưu tài khoản thành công !</b>");
		accountService.save(entity);
		System.out.println("ok");
		
		
		return "redirect:/security/login/form";
	}
	
	@PostMapping("/security/register/Update")
	public String update(Model model, @ModelAttribute("account") AccountDto dto,
			@RequestParam("password") String password, @RequestParam("repassword") String repassword) {
		if(!password.equals(repassword)) {
			model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Mật khẩu không trùng khớp !</b>");
			System.out.println("Mật khẩu không trùng khớp !");
			return "security/register";
		}
		
		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		model.addAttribute("message", "<b><i class=\"fas fa-info-circle\"></i> Lưu tài khoản thành công !</b>");
		accountService.save(entity);
		System.out.println("ok");
		
		
		return "redirect:/security/login/form";
	}
	
	@GetMapping()
	public String getAllAccount(Model model) {
		model.addAttribute("allAccount", accountService.count()).toString();
		
		return "/home/index";
	}
}
