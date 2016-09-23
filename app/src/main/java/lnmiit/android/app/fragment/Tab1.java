package lnmiit.android.app.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import lnmiit.android.app.R;
import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.adapter.UpdateAdapter;
import lnmiit.android.app.model.UpdateDetail;

/**
 * Created by dexter on 22/9/16.
 */
public class Tab1 extends Fragment {

    private RecyclerView recyclerView;
    protected ArrayList<UpdateDetail> updateList;
    private UpdateAdapter updateAdapter;
    private Context mContext ;

    public Tab1(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false) ;
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        Log.e("Hello","Tab1");
        updateList = new ArrayList<>();
        updateAdapter = new UpdateAdapter(getActivity(), updateList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(updateAdapter);

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        // Inflate the layout for this fragment
        return view;
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect("http://www.lnmiit.ac.in").get();
                Elements content = doc.select("div.news").select("div.news_events_matter").select("table tbody tr td div table tbody td marquee ul li span a");
                for (Element contentItem : content) {
                    String title = contentItem.text();
                    String href = contentItem.attr("href");
                    if (!(href.contains("http") || href.contains("https"))) {
                        href = "http://www.lnmiit.ac.in/" + href;
                    }
                    System.out.println("Title : " + title + "\nHref : " + href);
                    updateList.add(new UpdateDetail(href,title));
                }
                ((MainActivity)getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateAdapter.notifyDataSetChanged();

                    }
                });
                System.out.print("Hello");
            } catch (IOException e) {
                System.out.print(e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }
}