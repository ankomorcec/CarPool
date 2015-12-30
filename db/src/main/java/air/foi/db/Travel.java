package air.foi.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Travel")
public class Travel extends Model {

    @Column(name = "start_point")
    public String startPoint;

    @Column(name = "end_point")
    public String endPoint;

    @Column(name = "start_time")
    public String startTime;

    public Travel(){
        super();
    }

    public Travel(String startPoint, String endPoint, String startTime) {
        super();
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
    }
}
