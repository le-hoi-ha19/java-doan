package com.example.fashion.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.example.fashion.models.Cart;
import com.example.fashion.models.CartItem;
import com.example.fashion.models.Order;
import com.example.fashion.models.OrderDetail;
import com.example.fashion.models.Product;
import com.example.fashion.models.User;
import com.example.fashion.repository.CartItemRepository;
import com.example.fashion.repository.CartRepository;
import com.example.fashion.repository.OrderDetailRepository;
import com.example.fashion.repository.OrderRepository;
import com.example.fashion.repository.UserRepository;
import com.example.fashion.services.NotificationService;
import com.example.fashion.services.OrderService;
import com.example.fashion.services.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAllOrderByIdDesc();
    }

    @Override
    public Boolean create(Cart cart) {
        try {
            // Tạo mới Order cho User từ Cart
            Order order = new Order();
            order.setUser(cart.getUser());
            order.setOrderStatus("Chờ xử lý");
            order.setOrderDate(LocalDate.now());
            LocalDate deliveryDate = LocalDate.now().plusDays(7);
            order.setDeliveryDate(deliveryDate);
            order.setShippingFee((double) 15000);
            order.setTotalsPrice(cart.getTotalsPrice());
            this.orderRepository.save(order);

            // Lặp qua từng mục trong giỏ hàng và lưu vào OrderDetail
            for (CartItem cartItem : cart.getCartItems()) {
                Product product = cartItem.getProducts();
                
                if (product.getQuantity() < cartItem.getQuantity()) {
                    throw new RuntimeException("Sản phẩm " + product.getProductName() + " không đủ số lượng trong kho!");
                }
                
                product.setQuantity(product.getQuantity() - cartItem.getQuantity());
                this.productService.update(product);
                
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                double itemPrice = cartItem.getTotalsPrice() / cartItem.getQuantity();
                orderDetail.setPrice(itemPrice);
                orderDetail.setProduct(product);
                orderDetail.setQuantity(cartItem.getQuantity());
                orderDetail.setTotalPrice(cartItem.getTotalsPrice());
                this.orderDetailRepository.save(orderDetail);
            }

            // Notify tất cả admins về đơn hàng mới
            List<User> admins = userRepository.findAdminUsers();
            String orderTitle = "🛒 Đơn hàng mới #" + order.getOrderID();
            String orderMessage = String.format(
                "Khách hàng %s vừa đặt đơn hàng trị giá %,.0f VNĐ. Vui lòng xử lý!",
                cart.getUser().getFullname(),
                order.getTotalsPrice()
            );
            
            for (User admin : admins) {
                notificationService.createNotification(admin.getId(), orderTitle, orderMessage);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long countTotalPrice() {
        return orderRepository.countTotalPrice();
    }

    @Override
    public long countTotalOrders() {
        return orderRepository.countTotalOrders();
    }

    @Override
    public long countPendingOrders() {
        return orderRepository.countPendingOrders();
    }

    @Override
    public long countShippingOrders() {
        return orderRepository.countShippingOrders();
    }

    @Override
    public long countCompletedOrders() {
        return orderRepository.countCompletedOrders();
    }

    @Override
    public long countCancelledOrders() {
        return orderRepository.countCancelledOrders();
    }

    @Override
    public List<Order> findByUserOrderByOrderIDDesc(User user) {
        return orderRepository.findByUserOrderByOrderIDDesc(user);
    }

    @Override
    public Order findByID(Long OrderID) {
        return orderRepository.findById(OrderID).get();
    }

    @Override
    public Boolean update(Order order) {
        try {
            this.orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean cancel(Long OrderID) {
        try {
            Order order = this.orderRepository.findById(OrderID).orElse(null);
            if (order == null) {
                return false;
            }
            
            if ("Đã hủy".equals(order.getOrderStatus())) {
                return false;
            }
            
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                product.setQuantity(product.getQuantity() + orderDetail.getQuantity());
                this.productService.update(product);
            }
            
            order.setOrderStatus("Đã hủy");
            this.orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
