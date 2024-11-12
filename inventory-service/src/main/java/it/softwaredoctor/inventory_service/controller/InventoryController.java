package it.softwaredoctor.inventory_service.controller;

import it.softwaredoctor.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<Boolean> getInventoryIfQuantityIsOk(@RequestParam ("skuCode") String skuCode, @RequestParam ("quantity") Integer quantity) {
        Boolean isInStock = inventoryService.isInStock(skuCode, quantity);
        if (isInStock == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(isInStock);
    }

//    @GetMapping("/")
//    public ResponseEntity<Boolean> getInventoryIfQuantityIsOk(@RequestParam String skuCode, @RequestParam Integer quantity) {
//        Boolean isInStock = inventoryService.isInStock(skuCode, quantity);
//        return isInStock == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok(isInStock);
//    }

}
