package bobert.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServiceClient {
  private static Logger logger = LoggerFactory.getLogger(WebServiceClient.class);

  private int status;
  private String responseBody;
  private static volatile boolean isDone = false;

  /**
   * read https://www.mediawiki.org/w/api.php?action=help&modules=query
   * and  https://www.mediawiki.org/wiki/Extension:TextExtracts
   */
  public void requestRandomWikipediaArticle() {
    makeHTTPRequest("https://en.wikipedia.org/w/api.php" +
        "?format=json&action=query" +
        "&generator=random&grnnamespace=0&grnlimit=1" +
        "&prop=extracts&explaintext=true&exintro=true");
  }

  public void makeHTTPRequest(String url) {
    HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
    Net.HttpRequest httpRequest = requestBuilder
        .newRequest()
        .method("GET")
        .url(url)
        .header("Accept", "*/*")
        .header("AcceptEncoding", "gzip, deflate, br")
        .header("User-Agent", "curl/7.43.0")
        .timeout(5000)
        .build();

    Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener()
    {

      @Override
      public void handleHttpResponse(Net.HttpResponse httpResponse) {
        status = httpResponse.getStatus().getStatusCode();
        responseBody = httpResponse.getResultAsString();
        if (status/100 != 2) {
          logger.error("Query failed with status code: {}", status);
          logger.error(responseBody);
          logger.error(httpResponse.toString());
        }
      }

      @Override
      public void failed(Throwable throwable) {
        logger.warn("http request failed");
      }

      @Override
      public void cancelled() {
        logger.warn("http request cancelled");
      }
    });
  }

  public int getStatus() {
    return status;
  }

  public String getResponseBody() {
    return responseBody;
  }
}
