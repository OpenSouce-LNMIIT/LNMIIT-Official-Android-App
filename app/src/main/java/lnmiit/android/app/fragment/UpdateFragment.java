package lnmiit.android.app.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.utilities.DatabaseHandler;
import lnmiit.android.app.utilities.RecyclerTouchListener;
import lnmiit.android.app.activity.WebActivity;
import lnmiit.android.app.adapter.UpdateAdapter;
import lnmiit.android.app.model.UpdateDetail;
import lnmiit.android.app.service.CrawDataService;

/**
 * Created by dexter on 23/9/16.
 */
public class UpdateFragment extends Fragment {

    private RecyclerView recyclerView;
    protected List<UpdateDetail> updateList;
    private UpdateAdapter updateAdapter;
    private UpdateReceiver updateReceiver;
    private TextView emptyText ;
    private DatabaseHandler db ;

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
        emptyText = (TextView) view.findViewById(R.id.empty_view);

        updateList = new ArrayList<>();
        updateAdapter = new UpdateAdapter(getActivity(), updateList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(updateAdapter);

        if(updateList.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.GONE);
        }

        db  = new DatabaseHandler(getContext());

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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        IntentFilter broadcastFilter = new IntentFilter(UpdateReceiver.INTENT_ACTION);

        updateReceiver = new UpdateReceiver();

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(updateReceiver,broadcastFilter);

        Intent intent = new Intent(getContext(),CrawDataService.class);
        intent.setAction(UpdateReceiver.INTENT_ACTION);
        getContext().startService(intent);

        updateList.clear();
        List<UpdateDetail> h = db.getAllUpdates();
        for(int i = 0 ; i < h.size() ; i++){
            updateList.add(h.get(i));
        }

        updateAdapter.notifyDataSetChanged();

        if(updateList.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.unregisterReceiver(updateReceiver);
    }

    public class UpdateReceiver extends BroadcastReceiver {
        public static final String INTENT_ACTION = "lnmiit.android.app.service.UpdateReceiver.update";

        @Override
        public void onReceive(Context context, Intent intent) {


            ArrayList<? extends Parcelable> list =  intent.getParcelableArrayListExtra(CrawDataService.DATA_UPDATE);
            int size = db.getAllUpdates().size();
            for(int i = 0 ; i < list.size() ; i++){
                if(!db.hasObjectInUpdate(((UpdateDetail) list.get(i)).getTitle())) {
                    updateList.add((UpdateDetail) list.get(i));
                    db.addItemtoUpdate((UpdateDetail) list.get(i));
                }
            }
            if(size == 0){
                updateAdapter = new UpdateAdapter(context,updateList);
                recyclerView.setAdapter(updateAdapter);

                if(updateList.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    emptyText.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyText.setVisibility(View.GONE);
                }
            }
        }
    };
}