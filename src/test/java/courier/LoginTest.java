package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
    }

    @Test
    @DisplayName("Check that a courier can login")
    public void loginSuccess() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);

        assertTrue(courierId  > 0 );

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that a courier cannot login if no password was submitted")
    public void loginNoPasswordError() {
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);

        CourierCredentialsNoPassword incorrectCreds = CourierCredentialsNoPassword.from(courier);
        String noLoginError = courierClient.loginNoPassword(incorrectCreds);

        assertEquals("Недостаточно данных для входа", noLoginError);

        courierId = courierClient.login(creds);

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Check that a courier cannot login if an incorrect password was submitted")
    public void loginIncorrectPasswordError() {

        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);

        Courier courierIncorrectPassword = Courier.getRandomPasswordFirstname(courier);
        CourierCredentials incorrectCreds =  CourierCredentials.from(courierIncorrectPassword);
        String incorrectPasswordError = courierClient.loginIncorrectPassword(incorrectCreds);

        assertEquals("Учетная запись не найдена", incorrectPasswordError);

        courierId = courierClient.login(creds);

        courierClient.delete(courierId);
    }
}
