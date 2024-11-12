/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-11-07 14:30:37
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-11-07 15:48:12
 * @FilePath: src/main/java/it/softwaredoctor/inventory_service/service/InventoryService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.inventory_service.service;

import it.softwaredoctor.inventory_service.model.Inventory;
import it.softwaredoctor.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);

    }

//    public void updateInventory(String skuCode, Integer quantity) {
//        inventoryRepository.findBySkuCode(skuCode)
//                .ifPresent(inventory -> {
//                    inventory.setQuantity(inventory.getQuantity() - quantity);
//                    inventoryRepository.save(inventory);
//                });
//    }
//
//    public void addInventory(String skuCode, Integer quantity) {
//        inventoryRepository.findBySkuCode(skuCode)
//                .ifPresentOrElse(inventory -> {
//                    inventory.setQuantity(inventory.getQuantity() + quantity);
//                    inventoryRepository.save(inventory);
//                }, () -> {
//                    Inventory inventory = new Inventory();
//                    inventory.setSkuCode(skuCode);
//                    inventory.setQuantity(quantity);
//                    inventoryRepository.save(inventory);
//                });
//    }
//
//    public void deleteInventory(String skuCode) {
//        inventoryRepository.findBySkuCode(skuCode)
//                .ifPresent(inventoryRepository::delete);
//    }
//    
//    // trova la quantità superiore a 5 con codice che va da 5 a 10
//    public List<Inventory> findInventoryByQuantityGreaterThanAndSkuCodeBetween(Integer quantity, String skuCodeStart, String skuCodeEnd) {
//        return inventoryRepository.findByQuantityGreaterThanAndSkuCodeBetween(quantity, skuCodeStart, skuCodeEnd);
//    }

}