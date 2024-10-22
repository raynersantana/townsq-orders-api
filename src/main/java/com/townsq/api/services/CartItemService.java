package com.townsq.api.services;

import com.townsq.api.domain.cart.CartItemDTO;
import com.townsq.api.domain.cart.CartItems;
import com.townsq.api.domain.order.Order;
import com.townsq.api.domain.order.OrderStatus;
import com.townsq.api.domain.product.Product;
import com.townsq.api.domain.user.User;
import com.townsq.api.repositories.CartItemsRepository;
import com.townsq.api.repositories.OrderRepository;
import com.townsq.api.repositories.ProductRepository;
import com.townsq.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> addProductToCart(CartItemDTO cartItemDTO) {
        Order pendingOrder = orderRepository.findByUserIdAndOrderStatus(cartItemDTO.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByUserIdAndProductIdAndOrderId(
                cartItemDTO.getUserId(),
                cartItemDTO.getProductId(),
                pendingOrder.getId());
        if(optionalCartItems.isPresent()) {
            CartItemDTO productAlreadyExistInCart = new CartItemDTO();
            productAlreadyExistInCart.setProductId(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(productAlreadyExistInCart);
        }else {
            Optional<Product> optionalProduct = productRepository.findById(cartItemDTO.getProductId());
            Optional<User> optionalUser = userRepository.findById(cartItemDTO.getUserId());
            if(optionalProduct.isPresent() && optionalUser.isPresent()) {
                Product product = optionalProduct.get();
                CartItems cartItem = new CartItems();
                cartItem.setProduct(product);
                cartItem.setUser(optionalUser.get());
                cartItem.setQuantity(1L);
                cartItem.setOrder(pendingOrder);
                cartItem.setPrice(product.getPrice());
                CartItems updatedCart = cartItemsRepository.save(cartItem);
                pendingOrder.setTotalPrice(pendingOrder.getTotalPrice() + cartItem.getPrice());
                pendingOrder.getCartItems().add(cartItem);
                orderRepository.save(pendingOrder);

                CartItemDTO updatedCartItemDto = new CartItemDTO();
                updatedCartItemDto.setId(cartItem.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(updatedCartItemDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }
}
