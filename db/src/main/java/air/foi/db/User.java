package air.foi.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "User")
public class User extends Model {

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    public User(){
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public static String getUserId(String username){
        List<User> user=new Select().from(User.class).where("username = '" + username + "'").execute();
        if(!user.isEmpty()){
            return user.get(0).getId().toString();
        }
        else{
            return null;
        }
    }

    public static Boolean validateUser(String username, String password){
        List<User> user=new Select().from(User.class).where("username = '" + username + "' AND password = '" + password + "'").execute();
        if (!user.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}