package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class CreateTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Check that a new courier can be created")
    public void createCourierSuccess() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);

        assertTrue(created);

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that the same courier cannot be created again")
    public void createSameCourierErrorShort() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);
        Courier sameCourier = Courier.getRandomPasswordFirstname(courier);
        String sameLoginError = courierClient.createSame(sameCourier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);

        assertEquals("Этот логин уже используется. Попробуйте другой.", sameLoginError);

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that a courier cannot be created with no login")
    public void createCourierNoLoginError() {
        Courier courier = Courier.getRandomNoLogin();
        String noLoginError = courierClient.createNoLogin(courier);

        assertEquals("Недостаточно данных для создания учетной записи", noLoginError);
    }
}
