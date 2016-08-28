package air.foi.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Travel")
public class Travel extends Model {

    @Column(name = "start_point")
    public String startPoint;

    @Column(name = "end_point")
    public String endPoint;

    @Column(name = "start_time")
    public String startTime;

    @Column(name = "user")
    public String user;

    @Column(name = "start_lat")
    public Double start_lat;

    @Column(name = "start_long")
    public Double start_long;

    @Column(name = "end_lat")
    public Double end_lat;

    @Column(name = "end_long")
    public Double end_long;

    @Column(name = "distance")
    public Float distance;

    public Travel(){
        super();
    }

    public Travel(String startPoint, String endPoint, String startTime, String user, Double start_lat, Double start_long,
                  Double end_lat, Double end_long, Float distance) {
        super();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.user = user;
        this.start_lat = start_lat;
        this.start_long = start_long;
        this.end_lat = end_lat;
        this.end_long = end_long;
        this.distance = distance;
    }

    public static List<Travel> getAll() {
        return new Select()
                .from(Travel.class)
                .orderBy("start_point ASC")
                .execute();
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getUser() {
        return user;
    }
    public Double getStart_lat() {
        return start_lat;
    }
    public Double getStart_long() {
        return start_long;
    }
    public Double getEnd_lat() {
        return end_lat;
    }
    public Double getEnd_long() {
        return end_long;
    }
    public Float getDistance() { return distance; }
}
