package courier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class LoginTest {

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
    @DisplayName("Check that a courier can login")
    public void loginSuccess() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");;

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("id");

        assertTrue(courierId  > 0 );
    }

    @Test
    @DisplayName("Check that a courier cannot login if no password was submitted")
    public void loginNoPasswordError() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);

        CourierCredentialsOnlyPassword incorrectCreds = CourierCredentialsOnlyPassword.from(courier);
        String noLoginError = courierClient.login(incorrectCreds)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .extract()
                .path("message");

        assertEquals("Недостаточно данных для входа", noLoginError);

        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("id");
    }

    @Test
    @DisplayName("Check that a courier cannot login if an incorrect password was submitted")
    public void loginIncorrectPasswordError() {

        Courier courier = Courier.getRandom();
        boolean created = courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");
        CourierCredentials creds = CourierCredentials.from(courier);

        Courier courierIncorrectPassword = Courier.getRandomPasswordFirstname(courier);
        CourierCredentials incorrectCreds =  CourierCredentials.from(courierIncorrectPassword);
        String incorrectPasswordError = courierClient.login(incorrectCreds)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .extract()
                .path("message");

        assertEquals("Учетная запись не найдена", incorrectPasswordError);

        courierId = courierClient.login(creds)
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("id");
    }
}
