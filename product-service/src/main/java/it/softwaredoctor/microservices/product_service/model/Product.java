/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-10-22 14:12:26
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-10-22 14:15:11
 * @FilePath: src/main/java/it/softwaredoctor/microservices/product_service/model/Product.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.microservices.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
    @Id
    private String id; 
    private String name;
    private String description;
    private BigDecimal price;
}
