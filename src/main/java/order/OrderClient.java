package order;

import root.RestAssuredClient;
import java.util.List;

public class OrderClient extends RestAssuredClient {

    private final String ROOT = "/orders";
    private final String ACCEPT = ROOT + "/accept" + "/{id}?courierId={courierId}";

    public int create(Order order) {
        return reqSpec
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }

    public List<Object> list() {
        return reqSpec
                .when()
                .get(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath().getList("orders");

    }
}
