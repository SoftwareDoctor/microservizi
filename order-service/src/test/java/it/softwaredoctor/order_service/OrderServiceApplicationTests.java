package it.softwaredoctor.order_service;

import io.restassured.RestAssured;
import it.softwaredoctor.order_service.stubs.InventoryClientStubs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                    "skuCode": "iphone_15",
                    "price": 100.0,
                    "quantity": 1
                }
                """;

        InventoryClientStubs.stubInventoryCall("iphone_15", "1");

		String responseBodyString = given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/v1/order")
				.then()
				.statusCode(HttpStatus.CREATED.value())
				.extract()
				.body()
				.asString();
		assertThat(responseBodyString).isEqualTo("Order placed successfully");
	}




}
