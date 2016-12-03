package lnmiit.android.app.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import lnmiit.android.app.R;
import lnmiit.android.app.adapter.FacultyAdapter;
import lnmiit.android.app.model.FacultyDetails;

/**
 * Created by Chanpreet on 16-09-2016.
 */
public class GymkhanaStudentFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<FacultyDetails> list = new ArrayList<>();
    private String name[];
    private String desig[];
    private String imageLink[];
    private String email[];
    private String phone[];
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gymkhana_student, container, false);
        name = getResources().getStringArray(R.array.gymkhana_name);
        desig = getResources().getStringArray(R.array.gymkhana_designation);
        imageLink = getResources().getStringArray(R.array.gymkhana_links);
        email = getResources().getStringArray(R.array.gymkhana_email);
        phone = getResources().getStringArray(R.array.gymkhana_phone);

        list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_gymkhana);
        adapter = new FacultyAdapter(getContext(), list);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        recyclerView.setAdapter(adapter);
        loadGymkhanaDetails();
        return view;
    }

    /**
     * This method is used to store Gymkhana
     * details in an Arraylist
     */
    private void loadGymkhanaDetails() {
        for (int i = 0; i < name.length; i++) {
            list.add(new FacultyDetails(name[i], desig[i], imageLink[i], email[i], phone[i]));
        }
        adapter.notifyDataSetChanged();
    }
}
