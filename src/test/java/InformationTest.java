import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InformationTest {
    @Test
    public void getInformation() throws IOException {
        String eclipse = "http://eclipse.org";
        String googleSearch = "https://www.google.co.in/search";
        String yandex = "https://yandex.ru/";
        String oracle = "https://oracle.com/";
        Document eclipseHtml = Jsoup.connect(eclipse).get();
        Document googleSearchHtml = Jsoup.connect(googleSearch).get();
        Document yandexHtml = Jsoup.connect(yandex).get();
        Document oracleHtml = Jsoup.connect(oracle).get();
        // запишем значения для сайтов на вчера
        HashMap<String, Document> websiteYesterday = new HashMap<>();
        websiteYesterday.put(eclipse, eclipseHtml);
        websiteYesterday.put(googleSearch, googleSearchHtml);
        websiteYesterday.put(yandex, yandexHtml);
        // запишем значения для сайтов на сегодня
        HashMap<String, Document> websiteToday = new HashMap<>();
        websiteToday.put(eclipse, eclipseHtml);// эклипс не меняем
        //гугл не добавляем
        websiteToday.put(yandex, googleSearchHtml);// яндекс меняем на гугл
        websiteToday.put(oracle, oracleHtml);// оракл добавляем

        Information information = new Information();
        //проверим правильность удаления страницы
        assertEquals(information.getDisappearedWebsite(websiteYesterday, websiteToday).get(0), googleSearch);
        //проверим правильность появления новой страницы
        assertEquals(information.getAppearedWebsite(websiteYesterday, websiteToday).get(0), oracle);
        //проверим правильность изменения состояния страницы
        assertEquals(information.getChangedWebsite(websiteYesterday, websiteToday).get(0), yandex);
    }
}