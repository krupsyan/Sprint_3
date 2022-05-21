package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListTest {
    private OrderClient orderClient;
    private int track;

    List<Object> orders = new ArrayList<>();

    @Before
    public void setup() {
        orderClient = new OrderClient();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
    }

    @Test
    @DisplayName("Check that a list of orders is submitted if requested")
    public void OrderListSuccess() {

        Order order = Order.getRandom();
        track = orderClient.create(order);

        orders = orderClient.list();

        assertTrue(orders.size() > 0);
    }
}
