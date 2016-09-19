package sri.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimestampHelper {

    public static Timestamp getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		Timestamp currentTimestamp = new Timestamp(now.getTime());
		return currentTimestamp;
    }
    
}
