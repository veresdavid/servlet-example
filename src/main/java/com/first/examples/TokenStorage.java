package com.first.examples;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ui2016 on 2016.08.03..
 */
public class TokenStorage {

  private static ConcurrentHashMap<String, String> map;

  static {
    map = new ConcurrentHashMap<String, String>();
  }

  public static void add(String sessionId, String token) {
    map.put(sessionId, token);
  }

  public static void remove(String sessionId) {
    map.remove(sessionId);
  }

  public static String getToken(String sessionId){

    return map.get(sessionId);

  }

  public static int getSize(){
    return map.size();
  }

}
