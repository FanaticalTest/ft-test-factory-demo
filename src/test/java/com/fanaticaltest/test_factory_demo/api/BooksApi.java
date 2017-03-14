package com.fanaticaltest.test_factory_demo.api;


import com.fanaticaltest.test_factory_demo.lib.Property;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fanaticaltest.test_factory_demo.lib.RestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class BooksApi extends RestApi{

  private final Logger logger = LoggerFactory.getLogger(BooksApi.class);
  private Property prop = new Property();
  private String ft_demo_website_url = prop.read("ft_demo_website_url");

  public void createTable() {

    setProxy();

    logger.info("REST call - Books - create table.");

    given().
        get(ft_demo_website_url + "api/createTable.php").
    then().
        contentType(ContentType.JSON).
        assertThat().body("is_table_dropped", hasItems(true)).
        assertThat().body("is_table_created", hasItems(true)).
        assertThat().body("is_test_data_inserted", hasItems(true)).
        statusCode(200).
        log().all();
  }

  public void addBookByPost(String title, String author, String edition) {

    setProxy();

    logger.info("REST call - Books - add book by post - title:{}, author:{}, edition:{}.", title, author, edition);

    given().
        queryParam("title", title).
        queryParam("author", author).
        queryParam("edition", edition).
    when().
        post(ft_demo_website_url + "api/insertBook.php");
  }

  public void addBookByGet(String title, String author, String edition) {

    setProxy();

    logger.info("REST call - Books - add book by get - title:{}, author:{}, edition:{}.", title, author, edition);

    given().
        queryParam("title", title).
        queryParam("author", author).
        queryParam("edition", edition).
    when().
        post(ft_demo_website_url + "api/insertBook.php");
  }

  public void checkBookInList(String title, String author, String edition) {

    setProxy();

    logger.info("REST call - Books - check book in list - title:{}, author:{}, edition:{}.", title, author, edition);

    given().
        get(ft_demo_website_url + "api/listBooks.php").
    then().
        contentType(ContentType.JSON).
        assertThat().body("title", hasItems(title)).
        assertThat().body("author", hasItems(author)).
        assertThat().body("edition", hasItems(edition)).
        statusCode(200).
        log().all();
  }
}
