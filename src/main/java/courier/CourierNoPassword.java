package courier;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierNoPassword {
    private String login;
    private String firstName;

    @Step("Generate a new courier without password with all fields random")
    public static CourierNoPassword getRandomNoPassword() {
        String login = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new CourierNoPassword(login, firstName);
    }
}
