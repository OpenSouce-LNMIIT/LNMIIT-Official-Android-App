package lnmiit.android.app.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.adapter.FacultyAdapter;
import lnmiit.android.app.model.FacultyDetails;


/**
 * Created by dexter on 24/8/16.
 */
public class HSSFacultyFragment extends Fragment {

    private RecyclerView recyclerView;
    private FacultyAdapter adapter;
    private List<FacultyDetails> facultyList;
    private String imageUrl[], faculty_name[], faculty_designation[], faculty_email[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facultybranch, container, false);

        imageUrl = getResources().getStringArray(R.array.hss_faculty_url);
        faculty_name = getResources().getStringArray(R.array.hss_faculty_name);
        faculty_designation = getResources().getStringArray(R.array.hss_faculty_designation);
        faculty_email = getResources().getStringArray(R.array.hss_faculty_email);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        facultyList = new ArrayList<>();
        adapter = new FacultyAdapter(getContext(), facultyList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        loadFacultyDetails();

        return view;
    }

    /**
     * this method is used to  store the faculty
     * details in an arraylist
     */
    private void loadFacultyDetails() {
        FacultyDetails a = null;
        for (int i = 0; i < imageUrl.length; i++) {
            facultyList.add(new FacultyDetails(faculty_name[i], faculty_designation[i], imageUrl[i], faculty_email[i]));
        }
        adapter.notifyDataSetChanged();
    }
}
