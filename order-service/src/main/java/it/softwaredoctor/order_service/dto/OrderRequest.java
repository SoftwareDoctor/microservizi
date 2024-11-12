/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-11-06 16:07:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-11-06 16:09:08
 * @FilePath: src/main/java/it/softwaredoctor/order_service/dto/OrderRequest.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long id, String orderNumber, 
        String skuCode, 
        BigDecimal price, 
        Integer quantity) {
}
