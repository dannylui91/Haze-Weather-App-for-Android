package nyc.c4q.dannylui.weatheralpha.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dannylui on 10/26/16.
 */

public class ConvertUnixTs {

    public static String toHourMinute(long unixSeconds){
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); // the format of your date
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

    public static String toDayOfWeek(long unixSeconds){
        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("E"); // the format of your date
        //sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

}
