package com.first.examples;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ui2016 on 2016.08.02..
 */

@WebServlet("/user")
public class UserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    // getting the User object from session if its already exists
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("userObject");

    // if we have a user in the session, then print it in json format
    if (user != null) {

      ObjectMapper mapper = new ObjectMapper();

      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

      resp.setStatus(200);
      resp.getWriter().print(json);

    } else {

      resp.setStatus(404);
      resp.getWriter().print("Nothing");

    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    if (isValidUserData(req)) {

      saveUserInSession(req);

      resp.setStatus(200);
      resp.getWriter().println("User saved in the session!");
      resp.setHeader("token", TokenStorage.getToken(req.getSession().getId()));

    } else {

      resp.setStatus(400);
      resp.getWriter().println("Invalid data!");

    }

  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    // get the User object from the session for modification
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("userObject");

    if (user != null) {

      // modify the name if its requested
      String name = req.getParameter("name");
      if (name != null) {
        user.setName(name);
      } else {
        System.out.println("name is null");
      }

      // modifiy the birthday if its requested
      String birthday = req.getParameter("birthday");
      if (birthday != null) {
        try {
          user.setBirthday(LocalDate.parse(birthday));
        } catch (Exception e) {
        }
      } else {
        System.out.println("birthday is null");
      }

      // modify the weight if its requested
      String weight = req.getParameter("weight");
      if (weight != null) {
        try {
          user.setWeight(Double.parseDouble(weight));
        } catch (Exception e) {
        }
      } else {
        System.out.println("weight is null");
      }

      // saving the modified object to the session
      session.setAttribute("userObject", user);

      resp.setStatus(200);
      resp.getWriter().println("User modified!");
      resp.getWriter().println(user);

    } else {

      resp.setStatus(404);
      resp.getWriter().println("Theres no user to modify!");

    }

  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    HttpSession session = req.getSession();

    if (session.getAttribute("userObject") != null) {

      session.removeAttribute("userObject");
      resp.setStatus(200);
      resp.getWriter().println("User deleted!");

    } else {

      resp.setStatus(400);
      resp.getWriter().println("Can't delete non existing user!");

    }

  }

  public boolean isValidUserData(HttpServletRequest req) {

    String name = req.getParameter("name");
    String birthday = req.getParameter("birthday");
    String weight = req.getParameter("weight");

    // checking the name
    if (name == null) {

      return false;

    }

    // checking the birthday
    if (birthday == null) {

      return false;

    } else {
      try {

        LocalDate localDate = LocalDate.parse(birthday);

      } catch (Exception e) {

        return false;

      }
    }

    // checking weight
    if (weight == null) {

      return false;

    } else {

      Double parsedWeight;

      try {
        parsedWeight = Double.parseDouble(weight);
      } catch (Exception e) {
        return false;
      }

      if (parsedWeight < 2 || parsedWeight > 500) {
        return false;
      }

    }

    return true;
  }

  public void saveUserInSession(HttpServletRequest req) {

    String name = req.getParameter("name");
    LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
    Double weight = Double.parseDouble(req.getParameter("weight"));

    User user = new User(name, birthday, weight);

    HttpSession session = req.getSession();
    session.setAttribute("userObject", user);

  }

}
