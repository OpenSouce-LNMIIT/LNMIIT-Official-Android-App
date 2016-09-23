package lnmiit.android.app.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import lnmiit.android.app.R;
import lnmiit.android.app.RecyclerTouchListener;
import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.activity.WebActivity;
import lnmiit.android.app.adapter.UpdateAdapter;
import lnmiit.android.app.model.UpdateDetail;

/**
 * Created by dexter on 23/9/16.
 */
public class UpdateFragment extends Fragment {private RecyclerView recyclerView;
    protected ArrayList<UpdateDetail> updateList;
    private UpdateAdapter updateAdapter;
    private ProgressBar progressBar ;
    public UpdateFragment(){

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
        progressBar = (ProgressBar) view.findViewById(R.id.progressbarupdate);
        updateList = new ArrayList<>();
        updateAdapter = new UpdateAdapter(getActivity(), updateList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(updateAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                UpdateDetail updateItem = updateList.get(position);
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url_news",updateItem.getUrl());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
        return view;
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
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
            progressBar.setVisibility(View.GONE);

        }
    }
}