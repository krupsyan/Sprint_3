package courier;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Courier {
    private String login;
    private String password;
    private String firstName;

    @Step("Generate a new courier with all fields random")
    public static Courier getRandom() {
        String login = RandomStringUtils.randomAlphanumeric(12);
        String password = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new Courier(login, password, firstName);
    }

    @Step("Generate a new courier with random password and firstName")
    public static Courier getRandomPasswordFirstname(Courier courier) {
        String login = courier.getLogin();
        String password = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new Courier(login, password, firstName);
    }

    @Step("Generate a new courier without login with all fields random")
    public static Courier getRandomNoLogin() {
        String password = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new Courier(password, firstName);
    }

    public Courier(String password, String firstName) {
        this.firstName = firstName;
        this.password = password;
    }
}
