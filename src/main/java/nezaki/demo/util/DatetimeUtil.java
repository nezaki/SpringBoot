package nezaki.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtil {

  public static Date getCurrentDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  public static Date parseDate(String date, String format) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.parse(date);
  }

  public static Date parseDate(String date) throws ParseException {
    return parseDate(date, "yyyy-MM-dd HH:mm:ss");
  }
}
