package air.foi.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiwi on 24.7.2016..
 */
public class Passenger extends Model {

    @Column(name = "user_id")
    public String user_id;

    @Column(name = "travel_id")
    public String travel_id;

    public Passenger(){
        super();
    }

    public Passenger(String user_id, String travel_id) {
        super();
        this.travel_id = travel_id;
        this.user_id = user_id;
    }

    public static String addPassenger(String user_id, String travel_id){
        List<Passenger> existsCheck=new Select().from(Passenger.class).where("travel_id = '" + travel_id + "' AND user_id = '" + user_id + "'").execute();
        if(!existsCheck.isEmpty()){
            return "Passenger already booked!";
        }
        List<Passenger> passengers=new Select().from(Passenger.class).where("travel_id = '" + travel_id + "'").execute();
        if (!passengers.isEmpty()){
            if(passengers.size()>=4){
                return "No room left!";
            }else{
                Passenger pass=new Passenger(user_id, travel_id);
                pass.save();
                return "Passenger booked!";
            }
        }
        else{
            Passenger pass=new Passenger(user_id, travel_id);
            pass.save();
            return "Passenger booked!";
        }
    }

    public static String removePassenger(String user_id, String travel_id){
        List<Passenger> existsCheck=new Select().from(Passenger.class).where("travel_id = '" + travel_id + "' AND user_id = '" + user_id + "'").execute();
        if(!existsCheck.isEmpty()){
            new Delete().from(Passenger.class).where("travel_id = '" + travel_id + "' AND user_id = '" + user_id + "'").execute();
            return "Passenger reservation removed!";
        }else{
            return "Passenger doesn't have reservation";
        }
    }

    public static List<String> getTravelPassengers(String travel_id){
        List<Passenger> passengers=new Select().from(Passenger.class).where("travel_id = '" + travel_id + "'").execute();
        List<String> pass_names=new ArrayList<>();
        for (Passenger passer: passengers) {
            User user=User.load(User.class, Long.parseLong(passer.user_id));
            pass_names.add(user.username);
        }
        return pass_names;
    }

    public static float[] getStatTravelData(String userid){
        float[] floatHelper = {0,0};
        List<Passenger> passengers=new Select().from(Passenger.class).where("user_id = '" + userid + "'").execute();
        for (Passenger passng : passengers) {
            floatHelper[0]++;
            Travel travel = new Select().from(Travel.class).where("Id == ?", passng.travel_id).executeSingle();
            floatHelper[1] += travel.distance;
        }
        return floatHelper;
    }
}