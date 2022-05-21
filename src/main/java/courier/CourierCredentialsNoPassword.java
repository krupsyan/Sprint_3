package courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierCredentialsNoPassword {
    private String password;

    public CourierCredentialsNoPassword(Courier courier) {
        this.password = courier.getPassword();
    }

    public static CourierCredentialsNoPassword from(Courier courier) {
        return new CourierCredentialsNoPassword(courier);
    }
}
