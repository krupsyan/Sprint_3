package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import root.RestAssuredClient;

public class OrderClient extends RestAssuredClient {

    private final String ROOT = "/orders";

    @Step("Create a new order")
    public ValidatableResponse createOrder(Order order) {
        return reqSpec
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("List all orders")
    public ValidatableResponse listOrders() {
        return reqSpec
                .when()
                .get(ROOT)
                .then().log().all();

    }
}
