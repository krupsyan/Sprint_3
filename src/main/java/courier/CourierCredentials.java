package courier;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }

    @Step("Take credentials for login from courier")
    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier);
    }
}
