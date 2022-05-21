package courier;

import root.RestAssuredClient;


public class CourierClient extends RestAssuredClient {

    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String COURIER = ROOT + "/{courierId}";

    public boolean create(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    public String createSame(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(409)
                .and()
                .extract()
                .path("message");
    }

    public String createNoLogin(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public int login(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    public String loginNoPassword(CourierCredentialsNoPassword creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public String loginIncorrectPassword(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");
    }

    public void delete(int courierId) {
        reqSpec
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
