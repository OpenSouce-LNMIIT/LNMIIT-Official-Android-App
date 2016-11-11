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

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lnmiit.android.app.R;
import lnmiit.android.app.adapter.StudentCouncilFestAdapter;
import lnmiit.android.app.model.CouncilFestDetail;

/**
 * Created by dexter on 2/11/16.
 */

public class DirectorFragment extends Fragment {

    private int i=0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView imageDirector ;
    private String message[] ;
    private String header[] ;

    private ArrayList<CouncilFestDetail> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_director, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_director);

        message = getResources().getStringArray(R.array.director_message);
        header = getResources().getStringArray(R.array.director_header);

        String url = getActivity().getResources().getString(R.string.director_image_link);
        imageDirector = (ImageView)view.findViewById(R.id.iv_director);
        try{
            Glide.with(getActivity()).load(url).error( R.drawable.ic_person).placeholder(R.drawable.ic_person).into(imageDirector);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
