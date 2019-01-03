package com.unava.dia.dotapedia.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import io.reactivex.Single;

public class HTMLDownloader {
    public static Single<String> ObservableDownloadHtml(final String htmlToDownload){
        Single<String> onlineObservable = Single.create(sub ->
        sub.onSuccess(downloadHtml(htmlToDownload)));

        return onlineObservable;
    }

    public static String downloadHtml(String url){
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B)")
                    .get();

            return doc.outerHtml();
        }
        catch (IOException ex) {
            return null;
        }
    }
}
