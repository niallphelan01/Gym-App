package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer extends Model {

    public String firstname;
    public String lastname;
    public String email;
    public String password;

    //@OneToOne(cascade = CascadeType.ALL)
    //public List<Assessment> assessmentlist = new ArrayList<Assessment>();

    public Trainer(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname=lastname;
        this.email = email;
        this.password = password;
    }
        public boolean checkPassword(String password)
        {
            return this.password.equals(password);
        }


    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }
}
