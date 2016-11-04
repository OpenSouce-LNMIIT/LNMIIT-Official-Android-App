package lnmiit.android.app.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import lnmiit.android.app.fragment.NewsFragment;
import lnmiit.android.app.fragment.UpdateFragment;
import lnmiit.android.app.service.CrawDataService;
import lnmiit.android.app.utilities.InternetConnection;

/**
 * Created by dexter on 11/10/16.
 */
public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Internet");
        final String action = intent.getAction();
        switch (action) {
            case ConnectivityManager.CONNECTIVITY_ACTION:
                if(InternetConnection.isNetworkAvailable(context)){

                    Intent intentNews = new Intent(context,CrawDataService.class);
                    intent.setAction(NewsFragment.NewsReceiver.INTENT_ACTION);
                    context.startService(intent);

                    Intent intentUpdate = new Intent(context,CrawDataService.class);
                    intent.setAction(UpdateFragment.UpdateReceiver.INTENT_ACTION);
                    context.startService(intent);
                }
                break;
        }
    }
}
