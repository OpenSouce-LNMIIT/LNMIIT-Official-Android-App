package lnmiit.android.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import lnmiit.android.app.fragment.NewsFragment;
import lnmiit.android.app.fragment.UpdateFragment;
import lnmiit.android.app.model.UpdateDetail;


public class CrawDataService extends IntentService {

    public static final String DATA_UPDATE = "update_data";
    public static final String DATA_NEWS = "news_data";
    private static final String ACTION_UPDATE = "lnmiit.android.app.service.UpdateReceiver.update";
    private static final String ACTION_NEWS = "lnmiit.android.app.service.NewsReceiver.news";

    public CrawDataService() {
        super("CrawDataService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {
                handleActionUpdate();
            } else if (ACTION_NEWS.equals(action)) {
                handleActionNews();
            }
        }
    }

    /**
     * Handle action Up date in the provided background thread
     */
    private void handleActionUpdate() {
        System.out.println("Update");
        Document doc = null;
        ArrayList<UpdateDetail> updateDetailArrayList = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.lnmiit.ac.in").get();
            Elements newsHeadlines = doc.getElementsByClass("news_events_matter");
            // get all links in page
            Elements links = newsHeadlines.select("a[href]");
            for (Element link : links) {
                // get the value from the href attribute
                String href = link.attr("href");
                String title = link.text();
                if (!(href.contains("http") || href.contains("https"))) {
                    href = "http://www.lnmiit.ac.in/" + href;
                }
                updateDetailArrayList.add(new UpdateDetail(href, title));

            }

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(UpdateFragment.UpdateReceiver.INTENT_ACTION);
            broadcastIntent.putParcelableArrayListExtra(DATA_UPDATE, (ArrayList<? extends Parcelable>) updateDetailArrayList);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(broadcastIntent);

        } catch (IOException e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }

    /**
     * Handle action News in the provided background thread
     */
    private void handleActionNews() {
        ArrayList<UpdateDetail> updateDetailArrayList = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.lnmiit.ac.in").get();
            Elements newsHeadlines = doc.getElementsByClass("upcoming_events_matter");
            // get all links in page
            Elements links = newsHeadlines.select("a[href]");
            for (Element link : links) {
                // get the value from the href attribute
                String href = link.attr("href");
                String title = link.text();
                if (!(href.contains("http") || href.contains("https"))) {
                    href = "http://www.lnmiit.ac.in/" + href;
                }
                updateDetailArrayList.add(new UpdateDetail(href, title));

            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(NewsFragment.NewsReceiver.INTENT_ACTION);
            broadcastIntent.putParcelableArrayListExtra(DATA_NEWS, (ArrayList<? extends Parcelable>) updateDetailArrayList);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(broadcastIntent);
        } catch (IOException e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
