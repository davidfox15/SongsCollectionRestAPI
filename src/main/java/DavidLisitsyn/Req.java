package DavidLisitsyn;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Req extends Thread {
    String url;

     Req(String urls) {
         this.url = urls;
     }

    @Override
    public void run() {
        try {
            URL server = new URL("http://130.193.53.242:8080/api/parse?url=" + this.url);
            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("accept", "application/json");
            // This line makes the request
            InputStream responseStream = connection.getInputStream();
            System.out.println(responseStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Connection doc = Jsoup.connect(url);
            System.out.println(doc.request());
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
