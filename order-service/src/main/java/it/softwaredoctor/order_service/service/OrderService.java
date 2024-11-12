package it.softwaredoctor.order_service.service;

import it.softwaredoctor.order_service.client.InventoryClient;
import it.softwaredoctor.order_service.dto.OrderRequest;
import it.softwaredoctor.order_service.model.Order;
import it.softwaredoctor.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    

    public void placeOrder(OrderRequest orderRequest) {
       var isProductIsInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!isProductIsInStock) {
            log.error("Product {} is not in stock", orderRequest.skuCode());
            throw new RuntimeException("Product is not in stock");
        }
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        orderRepository.save(order);
    }
    
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    
    public void updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        order.setSkuCode(orderRequest.skuCode());
        orderRepository.save(order);
    }
    
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
//    public void cancelOrder(Long id) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        orderRepository.delete(order);
//    }
//    
//    public void updateOrderStatus(Long id, String status) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setStatus(status);
//        orderRepository.save(order);
//    }
//    
//    public void updateOrderPaymentStatus(Long id, String paymentStatus) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setPaymentStatus(paymentStatus);
//        orderRepository.save(order);
//    }
//    
//    public void updateOrderShippingStatus(Long id, String shippingStatus) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setShippingStatus(shippingStatus);
//        orderRepository.save(order);
//    }
//    
//    public void updateOrderDeliveryStatus(Long id, String deliveryStatus) {
//        Order order = orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        order.setDeliveryStatus(deliveryStatus);
//        orderRepository.save(order);
//    }

    
    
}
