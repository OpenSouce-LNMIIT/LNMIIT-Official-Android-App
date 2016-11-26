package lnmiit.android.app.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lnmiit.android.app.R;
import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.adapter.EmergencyAdapter;
import lnmiit.android.app.model.EmergencyDetails;

/**
 * Created by Chanpreet on 25-09-2016.
 */
public class EmergencyFragment extends Fragment {

    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private String name[];
    private String phone[];
    private ArrayList<EmergencyDetails> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x;
        x = inflater.inflate(R.layout.fragment_emergency, container, false);
        recyclerView = (RecyclerView) x.findViewById(R.id.emergency_recycler);
        tabLayout = ((MainActivity) getActivity()).getTabLayout();
        tabLayout.removeAllTabs();
        name = getResources().getStringArray(R.array.emergency_name);
        phone = getResources().getStringArray(R.array.emergency_phone);
        adapter = new EmergencyAdapter(getContext(), list);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        loadAdministrationDetails();
        return x;
    }

    /**
     * This method is used to store AdministrationFragment
     * details in an Arraylist
     */
    private void loadAdministrationDetails() {
        for (int i = 0; i < name.length; i++) {
            list.add(new EmergencyDetails(name[i], phone[i]));
        }
        adapter.notifyDataSetChanged();
    }
}