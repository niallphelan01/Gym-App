package controllers;

import models.Assessment;
import play.Logger;
import models.Member;
import play.db.jpa.GenericModel;
import play.mvc.Controller;
import models.Member;
import play.*;
import play.mvc.*;

import java.util.List;


public class listAssessmentCtrl extends Controller{
    public static void index(Long memberid)
    {

        Member member = Member.findById(memberid);
        List<Assessment> assessmentlist = member.assessmentlist;

        Assessment assessment = null;
        if ((assessmentlist.size()-1)!=-1)
        {
            assessment= assessmentlist.get(assessmentlist.size()-1); //get the last Arraylist value and pass it into the BMI calculator
        }
        Utility.calculateBMI(member, assessment);
        Utility.bmiCategory(member);
        Utility.isIdealBodyWeight(assessment, member);
        Logger.info ("Start loading the assessments for the user= " + memberid);
        Logger.info ("Member id = " + memberid);
        render("listAssessments.html",member, assessmentlist,assessment);
    }

   public static void updateComments(Long id,Long memberid, String comments)
   {
//wow this was difficult as I accidentally added the form with name field rather than comments and the whole thing folded
       Assessment assessment = Assessment.findById(id);
       assessment.comments=comments;
       assessment.save();
       Logger.info ("Start loading the assessments for the user= " + memberid);
        redirect("/listAssessments/" + memberid);
    }


}
