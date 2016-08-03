package com.first.examples;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ui2016 on 2016.08.03..
 */

@WebFilter("/user")
public class UserFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    if (req.getMethod() == "POST") {

      System.out.println("POST");

      if (req.getHeader("token") == null) {
        System.out.println("NO TOKEN YET");
      } else {
        System.out.println("TOKEN DETECTED");
      }

    }

    filterChain.doFilter(servletRequest, servletResponse);

  }

  @Override
  public void destroy() {

  }

}
