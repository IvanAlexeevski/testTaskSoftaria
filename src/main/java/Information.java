import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Information {
    private List<String> appearedWebsite = new ArrayList<>();
    private List<String> disappearedWebsite = new ArrayList<>();
    private List<String> changedWebsite = new ArrayList<>();

    /**
     * метод возвращает массив удаленных страниц
     *
     * @param websiteYesterday - состояние сайтов на вчера
     * @param websiteToday     - состояние сайтов на сегодня
     */
    public List<String> getDisappearedWebsite(HashMap<String, Document> websiteYesterday,
                                              HashMap<String, Document> websiteToday) {
        boolean disappeared = true;
        for (String urlYesterday : websiteYesterday.keySet()
        ) {
            disappeared = true;
            for (String urlToday : websiteToday.keySet()
            ) {
                if (urlYesterday.equals(urlToday)) {
                    disappeared = false;
                }
            }
            if (disappeared) {
                disappearedWebsite.add(urlYesterday);
            }
        }
        return disappearedWebsite;
    }

    /**
     * метод возвращает массив появившихся страниц
     *
     * @param websiteYesterday - состояние сайтов на вчера
     * @param websiteToday     - состояние сайтов на сегодня
     */
    public List<String> getAppearedWebsite(HashMap<String, Document> websiteYesterday,
                                           HashMap<String, Document> websiteToday) {
        boolean appeared = true;
        for (String urlToday : websiteToday.keySet()
        ) {
            appeared = true;
            for (String urlYesterday : websiteYesterday.keySet()
            ) {
                if (urlToday.equals(urlYesterday)) {
                    appeared = false;
                }
            }
            if (appeared) {
                appearedWebsite.add(urlToday);
            }
        }
        return appearedWebsite;
    }

    /**
     * метод возвращает массив измененных страниц
     *
     * @param websiteYesterday - состояние сайтов на вчера
     * @param websiteToday     - состояние сайтов на сегодня
     */
    public List<String> getChangedWebsite(HashMap<String, Document> websiteYesterday,
                                          HashMap<String, Document> websiteToday) {
        boolean changed = false;
        for (String urlYesterday : websiteYesterday.keySet()
        ) {
            for (String urlToday : websiteToday.keySet()
            ) {
                if (urlYesterday.equals(urlToday)) {
                    changed = websiteYesterday.get(urlYesterday).equals(websiteToday.get(urlToday));
                    if (!changed) {
                        changedWebsite.add(urlYesterday);
                    }
                }
            }
        }
        return changedWebsite;
    }
}
