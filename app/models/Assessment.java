package models;

import play.db.jpa.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Assessment extends Model
{

 public String dT;
  public float weight;
  public float chest;
  public float thigh;
  public float upperArm;
  public float waist;
  public float hips;
  public String comments;


  public Assessment(String dT, float weight, float chest, float thigh, float upperArm, float waist, float hips, String comments)
  {

    this.dT= dT;
    this.weight= weight;
    this.chest= chest;
    this.thigh=thigh;
    this.upperArm=upperArm;
    this.waist=waist;
    this.hips=hips;
    this.comments=comments;
  }
}
