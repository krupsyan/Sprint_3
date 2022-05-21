package order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String [] color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
    }


    public static Order getRandom(String [] color) {

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        String firstName = RandomStringUtils.randomAlphanumeric(12);
        String lastName = RandomStringUtils.randomAlphanumeric(12);
        String address = RandomStringUtils.randomAlphanumeric(12);
        String metroStation = RandomStringUtils.randomAlphanumeric(12);
        String phone = RandomStringUtils.randomAlphanumeric(12);
        int rentTime = ThreadLocalRandom.current().nextInt(12);
        String comment = RandomStringUtils.randomAlphanumeric(12);

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, date, comment, color);
    }

    public static Order getRandom() {

        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);

        String firstName = RandomStringUtils.randomAlphanumeric(12);
        String lastName = RandomStringUtils.randomAlphanumeric(12);
        String address = RandomStringUtils.randomAlphanumeric(12);
        String metroStation = RandomStringUtils.randomAlphanumeric(12);
        String phone = RandomStringUtils.randomAlphanumeric(12);
        int rentTime = ThreadLocalRandom.current().nextInt(12);
        String comment = RandomStringUtils.randomAlphanumeric(12);

        String[] arr={"", "BLACK", "GREY", "BLACK, GREY"};
        Random r=new Random();
        int index=r.nextInt(arr.length);
        String[]color = {arr[index]};

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, date, comment, color);
    }
}
