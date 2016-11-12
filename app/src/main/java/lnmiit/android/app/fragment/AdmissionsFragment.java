package lnmiit.android.app.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lnmiit.android.app.R;
import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.adapter.StudentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdmissionsFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;


    public AdmissionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x;
        x = inflater.inflate(R.layout.fragment_academics, container, false);
        viewPager = (ViewPager) x.findViewById(R.id.viewPager);
        tabLayout = ((MainActivity)getActivity()).getTabLayout();
        tabLayout.removeAllTabs();
        StudentAdapter studentadapter = new StudentAdapter(getChildFragmentManager());
        studentadapter.addFragment(new AdmissionUG(),"UG");
        studentadapter.addFragment(new AdmissionPG(),"PG");
        viewPager.setAdapter(studentadapter);
        tabLayout.setupWithViewPager(viewPager);
        return x;
    }

}
