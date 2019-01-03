package com.unava.dia.dotapedia.data;

import com.unava.dia.dotapedia.data.model.UpdateArticle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class HTMLParser {
    public static Single<List<UpdateArticle>> getObservableHtmlParser(String html){
        Single<List<UpdateArticle>> onlineObservable = Single.create(sub ->
        sub.onSuccess(parseHtml(html)));

        return onlineObservable;
    }

    public static List<UpdateArticle> parseHtml(String html) {
        List<UpdateArticle> cards = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(html);

            Elements elements = doc.select("div#mainLoop").first().children();

            for(int i = 0; i < elements.size(); i++) {
                Element el = elements.get(i);

                UpdateArticle card = new UpdateArticle();

                // DATE
                card.setDateStr(el.select("div.entry-meta").text());

                //DESCRIPTION
                card.setDescriptionStr(el.select("div.entry-content").html());

                // TITLE
                card.setTitleStr(el.select("h2.entry-title").text());

                // URL TO FULL ARTICLE
                card.setUrlToFullStr(el.select("h2.entry-title").select("a").attr("href"));

                if(card.getTitleStr().equals("")) {}
                else
                    cards.add(card);
            }
        }
        catch (Throwable t) {
            return null;
        }

        return cards;
    }
}
