package lnmiit.android.app.fragment;

/**
 * Created by dexter on 4/12/16.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.adapter.FacultyAdapter;
import lnmiit.android.app.adapter.PlacementAdapter;
import lnmiit.android.app.model.ContactDetails;
import lnmiit.android.app.model.FacultyDetails;


public class ContactFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FacultyDetails> contactList;
    private String imageUrl[], contact_name[], contact_title[], contact_email[];
    private Context applicationContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        imageUrl = getResources().getStringArray(R.array.cont_url);
        contact_name = getResources().getStringArray(R.array.cont_name);
        contact_title = getResources().getStringArray(R.array.cont_title);
        contact_email = getResources().getStringArray(R.array.cont_email);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        contactList = new ArrayList<>();
        adapter = new FacultyAdapter(getContext(), contactList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(mLayoutManager);
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        loadContactDetails();

        return view;
    }

    /**
     * this method is used to  store the faculty
     * details in an arraylist
     */
    private void loadContactDetails() {
        ContactDetails a = null;
        for (int i = 0; i < imageUrl.length; i++) {
            contactList.add(new FacultyDetails(contact_name[i], contact_title[i], imageUrl[i], contact_email[i]));
        }
        adapter.notifyDataSetChanged();
    }

    public Context getApplicationContext() {

        return applicationContext;
    }
}