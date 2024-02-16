package com.example.fashion.controller.customer;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fashion.models.Brand;
import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Category;
import com.example.fashion.models.Order;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.services.BrandService;
import com.example.fashion.services.CartItemService;
import com.example.fashion.services.CartService;
import com.example.fashion.services.CategoryService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService itemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@GetMapping("/cart")
	public String index(Model model, Principal principal, HttpSession session) {
		if (principal == null) {
			return "redirect:/admin/login";
		}
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Set<Cart> cart = user.getCarts();
		List<CartItem> lstItems = itemService.findByUser(user.getId());
		model.addAttribute("lstItems", lstItems);
		model.addAttribute("listViewsCart", cart);
		if (cart.isEmpty()) {
			model.addAttribute("check", "Không có sản phẩm trong giỏ hàng");
		}
		List<Product> listViewsProducts = this.productService.getAll();
		model.addAttribute("listViewsProducts", listViewsProducts);
		List<Category> categories = this.categoryService.getAll();
		model.addAttribute("categories", categories);
		List<Brand> listBra = this.brandService.getAll();
		model.addAttribute("listBra", listBra);
		return "cart/index";
	}

	@PostMapping("/addcart")
	public String addItemToCart(@RequestParam("ProductID") Long ProductID,
			@RequestParam("Quantity") Integer Quantity,
			Model model,
			Principal principal,
			HttpServletRequest request) {
		if (principal == null) {
			return "redirect:/admin/login";
		}
		String username = principal.getName();
		User user = userService.findByUsername(username);
		if (this.itemService.create(ProductID, Quantity, user)) {
			return "redirect:" + request.getHeader("Referer");
		} else {
			return "redirect:" + request.getHeader("Referer");
		}
	}

	@RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
	public String updateCart(@RequestParam("ProductID") Long ProductID,
			@RequestParam("Quantity") Integer Quantity,
			Model model,
			Principal principal,
			HttpServletRequest request) {
		if (principal == null) {
			return "redirect:/admin/login";
		} else {
			String username = principal.getName();
			User user = userService.findByUsername(username);

			// Kiểm tra xem Quantity có lớn hơn 0 không
			if (Quantity > 0) {
				// Nếu Quantity lớn hơn 0, cập nhật giỏ hàng
				boolean updateSuccessful = this.itemService.update(ProductID, Quantity, user);

				if (updateSuccessful) {
					// Sau khi cập nhật thành công, chuyển hướng người dùng đến trang trước đó
					return "redirect:" + request.getHeader("Referer");
				} else {
					// Xử lý nếu cập nhật không thành công, có thể hiển thị thông báo lỗi
					// hoặc chuyển hướng đến trang khác
				}
			}

			// Nếu Quantity không hợp lệ, chuyển hướng người dùng đến trang trước đó
			return "redirect:" + request.getHeader("Referer");
		}
	}

	@RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
	public String deleteCart(@RequestParam("ProductID") Long ProductID,
			Model model,
			Principal principal,
			HttpServletRequest request) {
		if (principal == null) {
			return "redirect:/admin/login";
		} else {
			String username = principal.getName();
			User user = userService.findByUsername(username);

			// Thực hiện xóa CartItem và kiểm tra xem có cần xóa Cart không
			boolean cartDeleted = this.itemService.delete(ProductID, user);

			if (cartDeleted) {
				// Nếu cart đã bị xóa, chuyển hướng về trang chính
				return "redirect:" + request.getHeader("Referer");
			} else {
				// Nếu không cần xóa cart, chuyển hướng về trang trước đó
				return "redirect:" + request.getHeader("Referer");
			}
		}
	}

}
