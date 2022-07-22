package DavidLisitsyn;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Parse {

    public static String parseAuthor(String url) {
        StringBuilder str = new StringBuilder("");
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();

            // Выбор элементов
            Element DOC = doc.select("table.items").get(0);
            Elements listRows = DOC.select("tr");

            for (Element element : listRows) {

                String songUrl = "https:" + element.select("a.g-link").attr("href");
                //System.out.println("[+] " + songUrl);
                //Req req = new Req(songUrl);
                //req.start();
                request(songUrl);
            }

        } catch (HttpStatusException e) {
            System.out.println("Page not found");
        } catch (NullPointerException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "OK";
    }

    private static String br2nl(String html) {
        if (html == null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");

        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    public static void request(String url) {
        try {
            URL server = new URL("http://130.193.53.242:8080/api/parse?url=" + url);
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
