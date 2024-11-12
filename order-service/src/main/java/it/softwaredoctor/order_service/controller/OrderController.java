package it.softwaredoctor.order_service.controller;

import it.softwaredoctor.order_service.dto.OrderRequest;
import it.softwaredoctor.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
@RestController
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)   {
         orderService.placeOrder(orderRequest);
         return "Order placed successfully";
    }


}
