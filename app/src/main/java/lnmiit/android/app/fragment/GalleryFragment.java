package lnmiit.android.app.fragment;

/**
 * Created by dexter on 4/12/16.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import lnmiit.android.app.R;
import lnmiit.android.app.activity.GalleryImage;
import lnmiit.android.app.activity.MainActivity;

public class GalleryFragment extends Fragment {

    // Some items to add to the GRID
                /*private static final String[] CONTENT = new String[] { "Gate", "Wall", "Central Plaza", "Library",
             "Academic Block", "Boy's Hostel", "Girl's Hostel", "Mess", "Canteen", "OAT", "Guest House",
             "Placement Office", "Temple", "Medical Unit", "ATM", "Jogging Track", "SAC", "Gymnasium",
             "Basketball court", "Volleyball court", "Badminton court", "Football Field"};*/
    public static final int[] ICONS = new int[]{R.drawable.a1, R.drawable.a2,
            R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
            R.drawable.a11, R.drawable.a12,
            R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16,
            R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20,
            R.drawable.a21, R.drawable.a22,
    };
    private TabLayout tabLayout;
    private GridView photoGrid;
    private int mPhotoSize, mPhotoSpacing;
    private ImageAdapter imageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.gallery, null);

        tabLayout = ((MainActivity) getActivity()).getTabLayout();
        tabLayout.removeAllTabs();

        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.gallery);

        // get the photo size and spacing
        mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);

        // initialize image adapter

        imageAdapter = new ImageAdapter();

        photoGrid = (GridView) rootView.findViewById(R.id.albumGrid);

        //start sent image to full screen

        /**
         * On Click event for Single Gridview Item
         * */
        photoGrid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity(), GalleryImage.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        //end sent image to full screen

        // set image adapter to the GridView
        photoGrid.setAdapter(imageAdapter);

        // get the view tree observer of the grid and set the height and numcols dynamically
        photoGrid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imageAdapter.getNumColumns() == 0) {
                    final int numColumns = (int) Math.floor(photoGrid.getWidth() / (mPhotoSize + mPhotoSpacing));
                    if (numColumns > 0) {
                        final int columnWidth = (photoGrid.getWidth() / numColumns) - mPhotoSpacing;
                        imageAdapter.setNumColumns(numColumns);
                        imageAdapter.setItemHeight(columnWidth);

                    }
                }
            }
        });
        return rootView;

    }

    // ///////// ImageAdapter class /////////////////
    public class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private int mItemHeight = 0;
        private int mNumColumns = 0;
        private RelativeLayout.LayoutParams mImageViewLayoutParams;

        public ImageAdapter() {
            mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mImageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
        }

        public int getCount() {
            return ICONS.length;
        }

        public int getNumColumns() {
            return mNumColumns;
        }

        // set numcols
        public void setNumColumns(int numColumns) {
            mNumColumns = numColumns;
        }

        // set photo item height
        public void setItemHeight(int height) {
            if (height == mItemHeight) {
                return;
            }
            mItemHeight = height;
            mImageViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, mItemHeight);
            notifyDataSetChanged();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {

            if (view == null)
                view = mInflater.inflate(R.layout.gallery_item, null);

            ImageView cover = (ImageView) view.findViewById(R.id.cover);
            //TextView title = (TextView) view.findViewById(R.id.title);

            cover.setLayoutParams(mImageViewLayoutParams);

            // Check the height matches our calculated column width
            if (cover.getLayoutParams().height != mItemHeight) {
                cover.setLayoutParams(mImageViewLayoutParams);
            }

            cover.setImageResource(ICONS[position % ICONS.length]);
            //title.setText(CONTENT[position % CONTENT.length]);

            return view;
        }
    }

}
