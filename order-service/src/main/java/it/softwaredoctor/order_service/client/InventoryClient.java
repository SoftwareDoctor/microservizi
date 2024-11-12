package it.softwaredoctor.order_service.client;

//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "${inventory.url}")
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    //    @GetMapping("/api/v1/inventory")
    @GetExchange("/api/v1/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
//    @TimeLimiter(name = "inventory")
    boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);

    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
       log.info("Fallback method called for skuCode: {} and quantity: {}", skuCode, quantity);
        return false;
    }

}

/*
https://docs.spring.io/spring-cloud-openfeign/reference/
Come annunciato nel post del blog sulla versione Spring Cloud 2022.0.0 , stiamo ora trattando il progetto Spring Cloud OpenFeign come feature-complete. Aggiungeremo solo correzioni di bug e forse uniremo alcune piccole PR di funzionalità della community. Suggeriamo invece di migrare a Spring Interface Clients .

Spring interface client

L'interfaccia si comporta da client per un servizio esterno.
Specifico i dettagli HTTP tramite annotazioni
Approccio dichiarativo anzichè imperativo

 */