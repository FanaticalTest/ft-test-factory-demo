package com.fanaticaltest.test_factory_demo.lib;


import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.specification.ProxySpecification.host;

public class RestApi {
  private static final Logger logger = LoggerFactory.getLogger(RestApi.class);
  private static Property prop = new Property();
  private static int api_proxy_required = Integer.parseInt(prop.read("api_proxy_required"));
  private static String api_proxy_url = prop.read("api_proxy_url");
  private static int api_proxy_port = Integer.parseInt(prop.read("api_proxy_port"));
  private static String api_proxy_user = prop.read("api_proxy_user");
  private static String api_proxy_pass = prop.read("api_proxy_pass");

  public static void setProxy() {
    if (api_proxy_required == 1) {
      RestAssured.proxy(host(api_proxy_url).withPort(api_proxy_port).withAuth(api_proxy_user, api_proxy_pass));
      logger.info("REST call - Proxy is required");
    } else {
      logger.info("REST call - Proxy not required");
    }
  }
}
