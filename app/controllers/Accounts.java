package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
  public static void signup()
  {
    render("signup.html");
  }

  public static void login()
  {
    render("login.html");
  }

  public static void register(String firstname, String lastname, String gender, String email, String password, String address,float height,float initialWeight)
  {
    Logger.info("Registering new user " + email);
    Member member = new Member(firstname, lastname, gender, email, password, address, height, initialWeight);
    member.save();
    redirect("/");
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);
    Member member = Member.findByEmail(email);
    Trainer trainer = Trainer.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect ("/dashboard");
    }
    else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
      Logger.info("Authentication successful for Trainer");
      session.put("logged_in_Trainerid", trainer.id);
      redirect("/admin");
    }
      else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout()
  {
    session.clear();
    redirect ("/");
  }

  public static Member getLoggedInMember()
  {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
  public static void accountdetails()
  {

    Logger.info("Attempting to get details");
    Member member = null;
    String memberId = session.get("logged_in_Memberid");
    member = Member.findById(Long.parseLong(memberId));

    render("accountdetails.html", member);
  }
  public static void updateAccountDetails(String firstname, String lastname, String gender, String email, String password, String address,float height,float initialWeight)
  {
    Member member;
    String memberId = session.get("logged_in_Memberid");
    member = Member.findById(Long.parseLong(memberId));
    member.firstname=firstname;
    member.lastname=lastname;
    member.gender=gender;
    member.email=email;
    member.password=password;
    member.address=address;
    member.height=height;
    member.initialWeight=initialWeight;
    member.save();
    //member.delete();
    //member=new Member(name, gender, email, password, address, height, initialWeight);
    //member.save();
    render("dashboard.html", member);
  }
}