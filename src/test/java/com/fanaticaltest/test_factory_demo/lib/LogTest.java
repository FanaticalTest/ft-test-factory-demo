package com.fanaticaltest.test_factory_demo.lib;

import java.io.BufferedReader;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;


public class LogTest {

  public static String send(String request) throws IOException{
    URL url = new URL(request);
    URLConnection uc = url.openConnection();

    uc.setRequestProperty("X-Requested-With", "Curl");

    StringBuilder html = new StringBuilder();
    BufferedReader input = null;
    try {
      input = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      String htmlLine;
      while ((htmlLine = input.readLine()) != null) {
        html.append(htmlLine);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        input.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return html.toString();
  }

  public static String sendBasicAuth(String request, String username, String password) throws IOException{
    URL url = new URL(request);
    URLConnection uc = url.openConnection();

    uc.setRequestProperty("X-Requested-With", "Curl");

    String userpass = username + ":" + password;
    String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
    uc.setRequestProperty("Authorization", basicAuth);

    StringBuilder html = new StringBuilder();
    BufferedReader input = null;
    try {
      input = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      String htmlLine;
      while ((htmlLine = input.readLine()) != null) {
        html.append(htmlLine);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        input.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return html.toString();
  }

}
