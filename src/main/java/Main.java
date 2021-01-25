import exceptions.ConnectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws ConnectionException {
        //введите, пожалуйста, свои данные email, для получения на этот адрес письма
        //также в файле mail.properties в папке resources, введите email, с которого будет отправлено сообщение
        String mailFrom = "введите маил на который отправится письмо";
        String mailTo = "введите маил с которого отправится письмо";
        String password = "введите пароль";
        try {
            // подключаемся к сайтам и считываем html
            String eclipse = "http://eclipse.org";
            String googleSearch = "https://www.google.co.in/search";
            String yandex = "https://yandex.ru/";
            String oracle = "https://oracle.com/";
            Document eclipseHtml = Jsoup.connect(eclipse).get();
            Document googleSearchHtml = Jsoup.connect(googleSearch).get();
            Document yandexHtml = Jsoup.connect(yandex).get();
            Document oracleHtml = Jsoup.connect(oracle).get();
            // заполняем хэш-таблицы значениями
            HashMap<String, Document> websiteYesterday = new HashMap<>();
            websiteYesterday.put(eclipse, eclipseHtml);
            websiteYesterday.put(googleSearch, googleSearchHtml);
            websiteYesterday.put(yandex, yandexHtml);
            HashMap<String, Document> websiteToday = new HashMap<>();
            websiteToday.put(eclipse, eclipseHtml);// эклипс не меняем
            //гугл не добавляем
            websiteToday.put(yandex, googleSearchHtml);// яндекс меняем на гугл
            websiteToday.put(oracle, oracleHtml);// оракл добавляем
            Information information = new Information();
            SendMessage sendMessage = new SendMessage();
            sendMessage.postMessage(mailFrom, mailTo, password,
                    information.getDisappearedWebsite(websiteYesterday, websiteToday),
                    information.getAppearedWebsite(websiteYesterday, websiteToday),
                    information.getChangedWebsite(websiteYesterday, websiteToday));
        } catch (IOException exception) {
            throw new ConnectionException("mistake in connection by URL", exception);
        }
    }
}
