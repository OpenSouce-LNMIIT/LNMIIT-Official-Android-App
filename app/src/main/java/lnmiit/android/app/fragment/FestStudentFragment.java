package lnmiit.android.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import lnmiit.android.app.R;
import lnmiit.android.app.adapter.StudentCouncilFestAdapter;
import lnmiit.android.app.model.CouncilFestDetail;

/**
 * Created by Chanpreet on 16-09-2016.
 */
public class FestStudentFragment extends Fragment {

    private String fest[];
    private String festDes[];
    private ArrayList<CouncilFestDetail> list = new ArrayList<>();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fest_student, container, false);
        fest = getResources().getStringArray(R.array.fest_name);
        festDes = getResources().getStringArray(R.array.fest_des);
        recycler = (RecyclerView) view.findViewById(R.id.recycle_fest);
        adapter = new StudentCouncilFestAdapter(getActivity().getApplicationContext(),list);
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(adapter);
        loadFestDetails();
        return view;
    }
    /**
     * This method is used to store Fest
     * details in an Arraylist
     */
    private void loadFestDetails() {
        for (int i = 0; i < fest.length; i++) {
            list.add(new CouncilFestDetail(fest[i],festDes[i]));
        }
        adapter.notifyDataSetChanged();
    }
}
