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
 * Created by Chanpreet on 16-09-2016.
 */
public class StudentFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x;
        x = inflater.inflate(R.layout.fragment_student, container, false);
        viewPager = (ViewPager) x.findViewById(R.id.student_viewpager);
        tabLayout = ((MainActivity)getActivity()).getTabLayout();
        tabLayout.removeAllTabs();
        StudentAdapter studentadapter = new StudentAdapter(getChildFragmentManager());
        studentadapter.addFragment(new CouncilStudentFragment(), "Councils");
        studentadapter.addFragment(new FestStudentFragment(), "Fest");
        studentadapter.addFragment(new GymkhanaStudentFragment(), "Gymkhana");
        viewPager.setAdapter(studentadapter);
        tabLayout.setupWithViewPager(viewPager);
        return x;
    }

}
