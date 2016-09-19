package sri.util;

import java.sql.Time;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeAdapter extends XmlAdapter<Date, Time> {

    public Date marshal(Time v) {
        return new Date(v.getTime());
    }
    public Time unmarshal(Date v) {
        return new Time(v.getTime());
    }

}
