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

    if (user != null) {

      ObjectMapper mapper = new ObjectMapper();

      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

      resp.setStatus(200);
      resp.getWriter().print(json);

    } else {

      resp.setStatus(404);
      resp.getWriter().print("Nothing");

    }

    System.out.println(TokenStorage.getSize());

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    // getting the posted data
    String name = req.getParameter("name");
    String birthday = req.getParameter("birthday");
    String weight = req.getParameter("weight");

    // validating the posted data
    boolean validPost = true;

    // checking the name
    if (name == null || name == "") {
      validPost = false;
    }

    // checking the birthday
    if (birthday == null) {
      validPost = false;
    }

    // checking the weight
    if (weight == null || Double.parseDouble(weight) <= 0) {
      validPost = false;
    }

    if (validPost) {

      // creating new User object
      User user = new User(name, LocalDate.parse(birthday), Double.parseDouble(weight));

      // saving the object in the session
      HttpSession session = req.getSession();
      session.setAttribute("userObject", user);

      resp.setStatus(200);
      resp.getWriter().println("User saved in the session!");
      resp.getWriter().println(user);

    } else {

      resp.setStatus(400);
      resp.getWriter().println("GTFO");

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
        user.setBirthday(LocalDate.parse(birthday));
      } else {
        System.out.println("birthday is null");
      }

      // modify the weight if its requested
      String weight = req.getParameter("weight");
      if (weight != null) {
        user.setWeight(Double.parseDouble(weight));
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

    System.out.println(session.getAttribute("userObject"));

    if (session.getAttribute("userObject") != null) {

      session.removeAttribute("userObject");
      resp.setStatus(200);
      resp.getWriter().println("User deleted!");

    } else {

      resp.setStatus(400);
      resp.getWriter().println("Can't delete non existing user!");

    }

  }

}
