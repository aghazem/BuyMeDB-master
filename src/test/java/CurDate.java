import java.text.SimpleDateFormat;

public class CurDate {
    public static String curDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        return formatter.format(System.currentTimeMillis());
    }
}
