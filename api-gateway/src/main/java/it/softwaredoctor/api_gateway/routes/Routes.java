package it.softwaredoctor.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("product_service")
                .GET(RequestPredicates.path("/api/v1/product"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("order_service")
                .POST(RequestPredicates.path("/api/v1/order"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return route("inventory_service")
                .GET(RequestPredicates.path("/api/v1/inventory"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service not available. please try again later"))
                .build();
    }
}

/*
creo una routefunction che andrà a gestire le richieste HTTP e restituisce un serverresponse.
il punto di partenza di condifigurazione del routing viene definitio con GatewayRouterFunctions.route("/api/product-service")
poi si definisce la rotta con RequestPredicates.path("/api/product") e si specifica l'url di destinazione con HandlerFunctions.http("http://localhost:8080")
quando una richiesta soddisfa il predicato /api/product  viene indirizzata all'url http://localhost:8080
la classe HandlerFunctions gestisce l instradamento delle richieste HTTP al servizio di destinazione.
quindi qualsiasi richiesta /api/product verrà instradata al server in ascolto su http://localhost:8080
.build(): Costruisce e restituisce il RouterFunction completo, che Spring Cloud Gateway utilizza per configurare la logica di routing

 Quando usare GatewayRouterFunctions:
Quando vuoi una configurazione di routing funzionale e dichiarativa in un'applicazione basata su Spring WebFlux o Spring Cloud Gateway.
Quando hai bisogno di un routing asincrono e reattivo che sia più scalabile, ad esempio in un'architettura di microservizi dove le richieste vengono smistate tra più servizi.
Quando desideri definire rotte in modo conciso e dichiarativo piuttosto che usare il classico approccio imperativo con annotazioni nei controller.
 */