package com.first.examples;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by ui2016 on 2016.08.03..
 */
public class UserListener implements HttpSessionAttributeListener {

  @Override
  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

    if (httpSessionBindingEvent.getName().equals("userObject")) {

    }

  }

  @Override
  public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    if (httpSessionBindingEvent.getName().equals("userObject")) {

    }

  }

  @Override
  public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

  }

}
