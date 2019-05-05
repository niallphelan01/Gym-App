package controllers;
import java.lang.*;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Dashboard extends Controller
{

  public static void index()
  {

    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentlist = member.assessmentlist;

      Assessment assessment = null;
      if ((assessmentlist.size()-1)!=-1) 
      {
        assessment= assessmentlist.get(assessmentlist.size()-1); //get the last Arraylist value and pass it into the BMI calculator
      }

    Utility.calculateBMI(member, assessment);
    Utility.bmiCategory(member);
    Utility.isIdealBodyWeight(assessment, member);
    Collections.reverse(assessmentlist); //This works as the newest list will always be on top
    //would need to implement further methods to

    render("dashboard.html", member, assessmentlist, assessment);
  }

  public static void addAssessment(float weight, float chest, float thigh, float upperArm, float waist, float hips, String comments)
  {
    Member member = Accounts.getLoggedInMember();
    DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss"); //Add the current date and time to the Assessment on the server side
    Date dateTime = new Date();
    String dT = dateFormat.format(dateTime);
    Assessment assessment = new Assessment(dT, weight,chest,thigh,upperArm, waist,hips,comments);
    member.assessmentlist.add(assessment);
    member.save();
    Logger.info("Adding Assessment" + dateTime);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id, Long assessmentid)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessmentlist.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.weight);
    redirect("/dashboard");
  }

}
