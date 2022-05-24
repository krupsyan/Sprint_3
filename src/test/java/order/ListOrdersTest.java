package order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

public class ListOrdersTest {
    private OrderClient orderClient;
    private int track;

    List<Object> orders = new ArrayList<>();

    @Before
    public void setup() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check that a list of orders is submitted if requested")
    public void OrderListSuccess() {

        Order order = Order.getRandom();
        track = orderClient.createOrder(order)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("track");

        orders = orderClient.listOrders()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .jsonPath().getList("orders");

        assertTrue(orders.size() > 0);
    }
}
