package controllers;

import models.Assessment;
import models.Member;
import java.util.List;

import java.text.DecimalFormat;


public class Utility {

    private static DecimalFormat df = new DecimalFormat("0.00");
    //old code I had until discussing this with Eamonn.
    //public static void calculateBMI()
    //{
    //    Member member = Accounts.getLoggedInMember();
    //    double bmi = (member.initialWeight)/((member.height/100)*(member.height/100));
   //     member.bmi= Float.parseFloat(df.format(bmi));
    public static void calculateBMI(Member member, Assessment assessment) {
        if (assessment != null) {
            double bmi = (assessment.weight) / ((member.height / 100) * (member.height / 100));
            member.bmi = Float.parseFloat(df.format(bmi));
        }
        else
        {
            double bmi = (member.initialWeight) / ((member.height / 100) * (member.height / 100));
            member.bmi = Float.parseFloat(df.format(bmi));
        }
    }
    public static void bmiCategory(Member member)
    {
        //Member member = Accounts.getLoggedInMember();
        if (member.bmi <16)
            member.BMICategory="SEVERLY UNDERWEIGHT";
        else if (member.bmi >=16 && member.bmi<18.5)
            member.BMICategory="UNDERWEIGHT";
        else if (member.bmi >=18.5 && member.bmi<25)
            member.BMICategory="NORMAL";
        else if (member.bmi >=25 && member.bmi<30)
            member.BMICategory="OVERWEIGHT";
        else if (member.bmi >=30 && member.bmi<35)
            member.BMICategory="MODERATELY OBESE";
        else if (member.bmi >35)
            member.BMICategory="SEVERILY OBESE";
    }
    public static void isIdealBodyWeight(Assessment assessment, Member member)
    {
        //Member member = Accounts.getLoggedInMember();
        //add code to take in the most current weight

        double heightDifference=0;
        double overHeight = 152.4;
        double inch = 2.54;
        double perInchIncrease=2.3;
        double weightForOverHeight; //assume female

        if (member.gender.equals("Male")) {
            weightForOverHeight = 50;
        }
        else weightForOverHeight=45.5;

        if (member.height > 152.4)
            heightDifference = member.height - 152.4;
        else
            heightDifference=0;
        member.idealWeight=((heightDifference/inch)*perInchIncrease)+weightForOverHeight;
        if (assessment!=null)
           {
            if (member.idealWeight > assessment.weight)
                member.idealBodyWeight = true;
            else
                member.idealBodyWeight = false;
           }
        else
        {
            if (member.idealWeight > member.initialWeight)
                member.idealBodyWeight = true;
            else
                member.idealBodyWeight = false;
        }

        }


}
