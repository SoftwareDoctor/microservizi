/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-11-06 11:10:42
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-11-12 12:28:16
 * @FilePath: src/main/java/it/softwaredoctor/order_service/model/Order.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String orderNumber; 
    
    private String skuCode;
    
    private BigDecimal price;
    
    private Integer quantity; 
    
}
