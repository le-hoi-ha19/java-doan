package com.example.fashion.services.impl;
// package com.example.fashion.services.Impl;

// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.example.fashion.models.Cart;
// import com.example.fashion.models.CartItem;
// import com.example.fashion.models.Product;
// import com.example.fashion.repository.CartItemRepository;
// import com.example.fashion.repository.CartRepository;
// import com.example.fashion.services.CartService;

// public class CartServiceImpl implements CartService{

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private CartItemRepository cartItemRepository;

//     @Override
//     public Cart addItemToCart(Product product, Integer Quantity) {
//         Set<CartItem> cartItems = cart.getCartItems();
//         CartItem cartItem = findCartItem(cartItems, product.getProductID());
//         if (cartItems == null) {
//             cartItems = new HashSet<>();
//             if (cartItem == null) {
//                 cartItem = new CartItem();
//                 cartItem.setProduct(product);
//                 cartItem.setTotalPrice(quantity * product.getCostPrice());
//                 cartItem.setQuantity(quantity);
//                 cartItem.setCart(cart);
//                 cartItems.add(cartItem);
//                 itemRepository.save(cartItem);
//             }
//         } else {
//             if (cartItem == null) {
//                 cartItem = new CartItem();
//                 cartItem.setProduct(product);
//                 cartItem.setTotalPrice(quantity * product.getCostPrice());
//                 cartItem.setQuantity(quantity);
//                 cartItem.setCart(cart);
//                 cartItems.add(cartItem);
//                 itemRepository.save(cartItem);
//             } else {
//                 cartItem.setQuantity(cartItem.getQuantity() + quantity);
//                 cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * product.getCostPrice()));
//                 itemRepository.save(cartItem);
//             }
//         }
//         cart.setCartItem(cartItems);

//         int totalItems = totalItems(cart.getCartItem());
//         double totalPrice = totalPrice(cart.getCartItem());

//         cart.setTotalPrices(totalPrice);
//         cart.setTotalItems(totalItems);
//         cart.setCustomer(customer);

//         return cartRepository.save(cart);
//     }

//     @Override
//     public Cart updateItemInCart(Product product, Integer Quantity) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'updateItemInCart'");
//     }

//     @Override
//     public Cart deleteItemFromCart(Product product) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'deleteItemFromCart'");
//     }


    
// }
