package lnmiit.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import lnmiit.android.app.R;
import lnmiit.android.app.adapter.StudentCouncilFestAdapter;
import lnmiit.android.app.model.CouncilFestDetail;

/**
 * Created by dexter on 2/11/16.
 */
public class VisionFragment extends Fragment {
    private int i=0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String message[] ;
    private String header[] ;

    private ArrayList<CouncilFestDetail> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vision, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_vision);

        message = getResources().getStringArray(R.array.vision_array_message);
        header = getResources().getStringArray(R.array.vision_array_header);

        list = new ArrayList<>();
        adapter = new StudentCouncilFestAdapter(getActivity().getApplicationContext(),list);
        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        loadCouncilDetails();
        return view;
    }

    /**
     * This method is used to store Council
     * details in an Arraylist
     */
    private void loadCouncilDetails() {
        for (int i = 0; i < header.length; i++) {
            list.add(new CouncilFestDetail(header[i],message[i]));
        }
        adapter.notifyDataSetChanged();
    }

}
