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
public class CouncilStudentFragment extends Fragment {

    private String council[];
    private String des[];
    private int i=0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CouncilFestDetail> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_council_student, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_council);

        council = getResources().getStringArray(R.array.council);
        des = getResources().getStringArray(R.array.description);

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
        for (int i = 0; i < council.length; i++) {
            list.add(new CouncilFestDetail(council[i],des[i]));
        }
        adapter.notifyDataSetChanged();
    }

}
