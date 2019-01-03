package com.unava.dia.dotapedia.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.HTMLDownloader;
import com.unava.dia.dotapedia.data.model.UpdateArticle;

import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Article extends AppCompatActivity {
    @BindView(R.id.webWiew) WebView wv;

    private String articleUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        wv.getSettings().setJavaScriptEnabled(true);

        if(savedInstanceState != null) {
            articleUrl = savedInstanceState.getString("URL_TO_FULL_ARTICLE");
        }
        else {
            articleUrl = getIntent().getExtras().getString("URL_TO_FULL_ARTICLE");
        }

        HTMLDownloader.ObservableDownloadHtml(articleUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(string -> showArticle(string), error -> Log.e("Main", "Error: "));

    }

    @Override
    protected void onSaveInstanceState(Bundle ourState) {
        ourState.putString("URL_TO_FULL_ARTICLE", articleUrl);
        super.onSaveInstanceState(ourState);
    }


    private void showArticle(String html){
        if(html != null){
            html = filterHtml(html);
            wv.loadDataWithBaseURL("", html, "text/html","UTF-8", "");
        }
    }

    private String filterHtml(String html){
        Document document = Jsoup.parse(html);
        document.select("div#navBarBGRepeat.DotaFont").remove();
        document.select("div#primary").remove();
        document.select("div#footer").remove();
        return document.html();
    }
}
