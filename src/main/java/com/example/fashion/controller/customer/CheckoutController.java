package com.example.fashion.controller.customer;

import java.util.List;
import java.util.Set;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.example.fashion.services.OrderService;
import com.example.fashion.services.ProductService;
import com.example.fashion.services.UserService;
import com.example.fashion.services.NotificationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CheckoutController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService itemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/checkout")
	public String index(Model model, Principal principal, HttpSession session) {
		if (principal == null) {
			return "redirect:/login";
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
		return "checkout/index";
	}

	@PostMapping("/order")
	public String addOrder(@RequestParam("ProductID") Long ProductID,
			Model model, Principal principal, HttpServletRequest request, HttpSession session) {
		if (principal == null) {
			return "redirect:/login";
		}
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Set<Cart> carts = user.getCarts();

		if (!carts.isEmpty()) {
			Cart cart = carts.iterator().next();
			try {
				if (this.orderService.create(cart)) {
					notificationService.createNotification(user.getId(), "Đặt hàng thành công", 
						"Đơn hàng của bạn đã được tạo thành công! Mã đơn hàng sẽ được gửi qua email.");
					session.setAttribute("successMessage", "✅ Đặt hàng thành công! Cảm ơn bạn đã mua hàng.");
					itemService.delete(ProductID, user);
					cartService.delete(cart.getCartID());
				} else {
					session.setAttribute("errorMessage", "❌ Đặt hàng thất bại! Vui lòng thử lại.");
				}
			} catch (RuntimeException e) {
				session.setAttribute("errorMessage", "❌ " + e.getMessage());
				return "redirect:/checkout";
			}
		} else {
			session.setAttribute("errorMessage", "❌ Giỏ hàng trống!");
		}

		return "redirect:/";
	}

}
