import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/* 
 * TAHA TANGÜLÜ
 * 16260055 
 */
public class api_baglanti {

  public static void main(String[] args) {

    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();
    try {
      URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice/BTC.json");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      //Request kısımı

      connection.setRequestMethod("GET");
      connection.setConnectTimeout(2500);
      connection.setReadTimeout(3000);

      int status = connection.getResponseCode();
      System.out.println(status);

      if (status > 299) {

        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);

        }
        reader.close();

      } else {
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
          responseContent.append(line);
        }
        reader.close();
      }
      System.out.println(responseContent.toString());

    } catch (MalformedURLException e) {
      e.printStackTrace();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //connection.disconnect();
    }
  }

}