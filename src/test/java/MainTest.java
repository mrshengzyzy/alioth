import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainTest {
    public static void main(String[] args) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTime = "2021-04-07 14:43:42";

        LocalDateTime formatted = LocalDateTime.parse(dateTime, formatter);

        String date = formatted.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date);

        String time = formatted.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(time);

    }
}
