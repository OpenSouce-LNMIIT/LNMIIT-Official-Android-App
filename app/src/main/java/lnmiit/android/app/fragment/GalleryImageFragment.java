package lnmiit.android.app.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.activity.MainActivity;
import lnmiit.android.app.adapter.GalleryAdapter;
import lnmiit.android.app.model.ImageDetail;

/**
 * Created by dexter on 15/12/16.
 */
public class GalleryImageFragment extends Fragment{

    private static final int galleryImageIds[] = {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21,R.drawable.a22,R.drawable.a23} ;
    private static final String galleryImageTitle[] = {"Gate" , "Wall" , "Central Plaza" , "Library" , " Academic Block" , "Boy's Hostel","Girl's Hostel" , "Mess" , "Canteen" , "OAT" , "Guest House" , "Placement Office" , "Temple","Medical Unit","ATM","Jogging Track","SAC","Gymnasium","Basketball Court","Volleyball Court","Badminton Court","Football field","Faculty Quaters"};
    private GalleryAdapter galleryAdapter ;
    private RecyclerView recyclerView ;
    ArrayList<ImageDetail> imageDetails ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_gallery);

        imageDetails = new ArrayList<>();
        for(int i = 0 ; i < galleryImageTitle.length ; i++)
            imageDetails.add(new ImageDetail(galleryImageIds[i],galleryImageTitle[i]));

        galleryAdapter = new GalleryAdapter(getActivity(),imageDetails);

        ((MainActivity)getActivity()).getTabLayout().setVisibility(View.GONE);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
        } else {
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        recyclerView.setAdapter(galleryAdapter);

        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", imageDetails);
                bundle.putInt("position", position);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                SlideImageFragment newFragment = SlideImageFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view ;
    }

}
