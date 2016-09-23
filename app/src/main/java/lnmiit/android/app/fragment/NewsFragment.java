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
 * Created by dexter on 23/9/16.
 */
public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    protected ArrayList<UpdateDetail> updateList;
    private UpdateAdapter updateAdapter;
    private Context mContext ;

    public NewsFragment(){

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

        Log.e("NewsFragment","Hello");
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
                Elements content = doc.select("div.upcoming_events").select("div.upcoming_events_matter");
                for (Element contentItem : content) {
                    Elements anchortag = contentItem.getElementsByTag("a");
                    for(Element item : anchortag) {
                        String title = item.text();
                        String href = item.attr("href");
                        if (!(href.contains("http") || href.contains("https"))) {
                            href = "http://www.lnmiit.ac.in/" + href;
                        }
                        System.out.println("Title : " + title + "\nHref : " + href);
                        updateList.add(new UpdateDetail(href, title));
                    }
                    break;
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