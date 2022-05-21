package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreateTest {
    private OrderClient orderClient;
    private int track;
    private final String [] color;

    public CreateTest(String [] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3}")
    public static Object[][] setColor() {
        return new Object[][]{
                //Color
                {new String [] {"BLACK"}},
                {new String [] {"GREY"}},
                {new String [] {""}},
                {new String [] {"BLACK, GREY"}},
        };
    }

    @Before
    public void setup() {
        orderClient = new OrderClient();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
    }

    @Test
    @DisplayName("Check that a new order can be created")
    public void createOrderSuccess() {
        Order order = Order.getRandom(color);
        track = orderClient.create(order);

        assertTrue(track  > 0 );
    }
}
