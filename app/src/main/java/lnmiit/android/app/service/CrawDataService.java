package lnmiit.android.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.fragment.NewsFragment;
import lnmiit.android.app.fragment.UpdateFragment;
import lnmiit.android.app.model.UpdateDetail;


public class CrawDataService extends IntentService {

    private static final String ACTION_UPDATE = "lnmiit.android.app.service.UpdateReceiver.update" ;
    private static final String ACTION_NEWS = "lnmiit.android.app.service.NewsReceiver.news" ;

    public static final String DATA_UPDATE = "update_data";
    public static final String DATA_NEWS = "news_data";

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
     * Handle action Up datein the provided background thread
     */
    private void handleActionUpdate() {
        System.out.println("Update");
        Document doc = null;
        ArrayList<UpdateDetail> updateDetailArrayList = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.lnmiit.ac.in").get();
            Elements content = doc.select("div.news").select("div.news_events_matter").select("table tbody tr td div table tbody td marquee ul li span a");
            for (Element contentItem : content) {
                String title = contentItem.text();
                String href = contentItem.attr("href");
                if (!(href.contains("http") || href.contains("https"))) {
                    href = "http://www.lnmiit.ac.in/" + href;
                }
//                System.out.println("Title : " + title + "\nHref : " + href);
                updateDetailArrayList.add(new UpdateDetail(href,title));
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
            Elements content = doc.select("div.upcoming_events").select("div.upcoming_events_matter");
            for (Element contentItem : content) {
                Elements anchortag = contentItem.getElementsByTag("a");
                for(Element item : anchortag) {
                    String title = item.text();
                    String href = item.attr("href");
                    if (!(href.contains("http") || href.contains("https"))) {
                        href = "http://www.lnmiit.ac.in/" + href;
                    }
                    updateDetailArrayList.add(new UpdateDetail(href,title));
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(NewsFragment.NewsReceiver.INTENT_ACTION);
                broadcastIntent.putParcelableArrayListExtra(DATA_NEWS, (ArrayList<? extends Parcelable>) updateDetailArrayList);
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
                localBroadcastManager.sendBroadcast(broadcastIntent);
                break;
            }
        } catch (IOException e) {
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
