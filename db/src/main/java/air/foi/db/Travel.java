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

    public Travel(){
        super();
    }

    public Travel(String startPoint, String endPoint, String startTime, String user) {
        super();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.user = user;
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
}
