package com.unava.dia.dotapedia.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unava.dia.dotapedia.R;
import com.unava.dia.dotapedia.data.HTMLDownloader;
import com.unava.dia.dotapedia.data.HTMLParser;
import com.unava.dia.dotapedia.data.model.UpdateArticle;
import android.util.Log;
import com.unava.dia.dotapedia.ui.adapters.ArticlesAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Articles extends AppCompatActivity {

    ArrayList<UpdateArticle> cards = new ArrayList<>();

    HTMLParser htmlParser;
    String siteUrl = "http://www.dota2.com/news/updates/?l=russian";
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        currentPage = 1;

       // new download().execute();
        fillRecyclerView();
    }

    public void fillRecyclerView(){
        HTMLDownloader.ObservableDownloadHtml(siteUrl + String.valueOf(currentPage))
                .flatMap(string -> HTMLParser.getObservableHtmlParser(string))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> showArticles(result), error -> Log.e("Main", "Error: " + error.toString()));
    }

    private void showArticles(List<UpdateArticle> result){
        if (result != null) {
            cards.addAll(result);
            //view.showArticles(mDataList);
            setAdapter();
        }
    }

    public void setAdapter() {
        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_articles);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //ADAPTER
        ArticlesAdapter adapter = new ArticlesAdapter(getApplicationContext(), cards, this);
        rv.setAdapter(adapter);
    }



    /*

    public void setAdapter() {
        //SET UP RECYCLERVIEW
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_articles);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //ADAPTER
        ArticlesAdapter adapter = new ArticlesAdapter(getApplicationContext(), cards, this);
        rv.setAdapter(adapter);
    }

    public class download extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("http://www.dota2.com/news/updates/?l=russian")
                        .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B)")
                        .get();
                // СПОСОБ 1
                //Element content = doc.getElementById("mainLoop");
                //Elements elements = content.getElementsByTag("div").first().children();

                // СПОСОБ 2
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


                //title = doc.html();
                //titleStr = doc.text();
                //followRedirects(true)
                //      .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B)")
                //    .timeout(100000)
                //  .get();
            }
            catch (IOException ex) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // тут заполняем
            setAdapter();
        }
    }
    */
}
