package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Member> member = Member.findAll();
    render("admin.html", member);
  }
}
