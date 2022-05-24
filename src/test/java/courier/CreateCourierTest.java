package courier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

public class CreateCourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @After
    public void delete() {
        courierClient.deleteCourier(courierId);
    };

    @Test
    @DisplayName("Check that a new courier can be created")
    public void createCourierSuccess() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("id");

        assertTrue(created);
    }

    @Test
    @DisplayName("Check that the same courier cannot be created again")
    public void createSameCourierErrorShort() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");

        Courier sameCourier = Courier.getRandomPasswordFirstname(courier);
        String sameLoginError = courierClient.createCourier(sameCourier)
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .extract()
                .path("message");

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("id");

        assertEquals("Этот логин уже используется. Попробуйте другой.", sameLoginError);
    }

    @Test
    @DisplayName("Check that a courier cannot be created with no login")
    public void createCourierNoLoginError() {
        Courier courier = Courier.getRandomNoLogin();
        String noLoginError = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals("Недостаточно данных для создания учетной записи", noLoginError);
    }

    @Test
    @DisplayName("Check that a courier cannot be created with no password")
    public void createCourierNoPasswordError() {
        CourierNoPassword courier = CourierNoPassword.getRandomNoPassword();
        String noPasswordError = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals("Недостаточно данных для создания учетной записи", noPasswordError);
    }
}
