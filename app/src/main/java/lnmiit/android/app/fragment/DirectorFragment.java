package lnmiit.android.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lnmiit.android.app.R;

/**
 * Created by dexter on 2/11/16.
 */

public class DirectorFragment extends Fragment {

    private ImageView imageDirector ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_director, container, false) ;
        String url = getActivity().getResources().getString(R.string.director_image_link);
        imageDirector = (ImageView)view.findViewById(R.id.iv_director);
        try{
            Glide.with(getActivity()).load(url).error( R.drawable.ic_person).placeholder(R.drawable.ic_person).into(imageDirector);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

}
