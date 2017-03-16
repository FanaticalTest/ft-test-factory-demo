package com.fanaticaltest.test_factory_demo.lib;

import java.io.BufferedReader;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;


public class LogTest {

  private static Property prop = new Property();
  private static int api_proxy_required = Integer.parseInt(prop.read("api_proxy_required"));
  private static String api_proxy_url = prop.read("api_proxy_url");
  private static int api_proxy_port = Integer.parseInt(prop.read("api_proxy_port"));
  private static String api_proxy_user = prop.read("api_proxy_user");
  private static String api_proxy_pass = prop.read("api_proxy_pass");

  public static String send(String request) throws IOException{
    URL url = new URL(request);
    URLConnection uc = url.openConnection();

    if (api_proxy_required == 1){
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(api_proxy_url, api_proxy_port));

      Authenticator authenticator = new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
          return (new PasswordAuthentication(api_proxy_user,
              api_proxy_pass.toCharArray()));
        }
      };
      Authenticator.setDefault(authenticator);

      uc = url.openConnection(proxy);
    }

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

    if (api_proxy_required == 1){
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(api_proxy_url, api_proxy_port));

      Authenticator authenticator = new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
          return (new PasswordAuthentication(api_proxy_user,
              api_proxy_pass.toCharArray()));
        }
      };
      Authenticator.setDefault(authenticator);

      uc = url.openConnection(proxy);
    }

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
