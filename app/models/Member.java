package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model
{
  public String firstname;
  public String lastname;
  public String gender;
  public String email;
  public String password;
  public String address;
  public float initialWeight;
  public float height;
  public float bmi;
  public String BMICategory;
  public Boolean idealBodyWeight;
  public double idealWeight;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessmentlist = new ArrayList<Assessment>();

  public Member(String firstname, String lastname, String gender, String email, String password, String address, float initialWeight, float height)
  {
    this.firstname = firstname;
    this.lastname = lastname;
    this.gender = gender;
    this.email = email;
    this.password = password;
    this.address = address;
    this.initialWeight=initialWeight;
    this.height=height;

  }

  public static Member findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}
