package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public static Courier getRandom() {
        String login = RandomStringUtils.randomAlphanumeric(12);
        String password = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new Courier(login, password, firstName);
    }

    public static Courier getRandomPasswordFirstname(Courier courier) {
        String login = courier.getLogin();
        String password = RandomStringUtils.randomAlphanumeric(12);
        String firstName = RandomStringUtils.randomAlphanumeric(12);
        return new Courier(login, password, firstName);
    }

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
