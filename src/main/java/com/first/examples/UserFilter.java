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

    String sessionId = req.getSession().getId();
    String token = req.getHeader("token");
    String storageToken = TokenStorage.getToken(sessionId);

    resp.setCharacterEncoding("UTF-8");

    if (req.getMethod() == "POST" || (token!=null && storageToken!=null && token.equals(storageToken))) {

      filterChain.doFilter(servletRequest, servletResponse);

      resp.setStatus(200);
      resp.getWriter().println("niceuuuuuuuuu");

    }else{

      resp.setStatus(400);
      resp.getWriter().println("fakk it!!");

    }

  }

  @Override
  public void destroy() {

  }

}
