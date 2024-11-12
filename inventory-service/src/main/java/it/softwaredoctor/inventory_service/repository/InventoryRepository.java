/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-11-07 14:30:00
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-11-07 14:42:02
 * @FilePath: src/main/java/it/softwaredoctor/inventory_service/repository/InventoryRepository.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.inventory_service.repository;

import it.softwaredoctor.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity); 
}
