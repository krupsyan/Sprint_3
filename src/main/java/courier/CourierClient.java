package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import root.RestAssuredClient;

public class CourierClient extends RestAssuredClient {

    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String COURIER = ROOT + "/{courierId}";

    @Step("Create a new courier")
    public ValidatableResponse createCourier(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Create a new courier without password")
    public ValidatableResponse createCourier(CourierNoPassword courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Login as a courier")
    public ValidatableResponse login(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    @Step("Login as a courier without submitting login")
    public ValidatableResponse login(CourierCredentialsOnlyPassword creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }

    @Step("Delete a courier")
    public void deleteCourier(int courierId) {
        reqSpec
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all();
    }
}
