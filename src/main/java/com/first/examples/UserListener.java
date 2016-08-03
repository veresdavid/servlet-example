package com.first.examples;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.UUID;

/**
 * Created by ui2016 on 2016.08.03..
 */

@WebListener("/user")
public class UserListener implements HttpSessionAttributeListener {

  @Override
  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    if (httpSessionBindingEvent.getName().equals("userObject")) {

      String uuid = UUID.randomUUID().toString();

      System.out.println(uuid);

      TokenStorage.add(httpSessionBindingEvent.getSession().getId(), uuid);

    }

  }

  @Override
  public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    if (httpSessionBindingEvent.getName().equals("userObject")) {

      String sessionId = httpSessionBindingEvent.getSession().getId();

      TokenStorage.remove(sessionId);

    }

  }

  @Override
  public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

  }

}
