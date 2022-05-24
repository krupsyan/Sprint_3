package order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderClient orderClient;
    private int track;
    private final String [] color;

    public CreateOrderTest(String [] color) {
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
    }

    @Test
    @DisplayName("Check that a new order can be created")
    public void createOrderSuccess() {
        Order order = Order.getRandom(color);
        track = orderClient.createOrder(order)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("track");

        assertTrue(track  > 0 );
    }
}
