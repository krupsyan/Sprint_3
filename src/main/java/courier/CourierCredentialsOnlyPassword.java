package courier;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierCredentialsOnlyPassword {
    private String password;

    public CourierCredentialsOnlyPassword(Courier courier) {
        this.password = courier.getPassword();
    }

    @Step("Take password for login from courier")
    public static CourierCredentialsOnlyPassword from(Courier courier) {
        return new CourierCredentialsOnlyPassword(courier);
    }
}
