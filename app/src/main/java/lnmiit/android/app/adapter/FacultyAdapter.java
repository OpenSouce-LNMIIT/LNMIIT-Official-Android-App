package lnmiit.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import lnmiit.android.app.R;
import lnmiit.android.app.model.FacultyDetails;


/**
 * Created by dexter on 21/8/16.
 */
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.MyViewHolder>{

    private Context mContext;
    private List<FacultyDetails> facultyList;

    public FacultyAdapter(Context mContext, List<FacultyDetails> facultyList) {
        this.mContext = mContext;
        this.facultyList = facultyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faculty_card, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, designation ;
        private ImageView thumbnail  , phoneicon , emailicon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvtitle);
            designation = (TextView) view.findViewById(R.id.tvdesignation);
            thumbnail = (ImageView) view.findViewById(R.id.ivthumbnail);
            phoneicon = (ImageView) view.findViewById(R.id.ivphone);
            emailicon = (ImageView) view.findViewById(R.id.ivemail);
        }
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final FacultyDetails faculty = facultyList.get(position);

        holder.title.setText(faculty.getName());
        holder.designation.setText(faculty.getDesignation());

        if(faculty.getPhone().length() < 10) {
            holder.phoneicon.setVisibility(View.GONE);
        }else {
            holder.phoneicon.setVisibility(View.VISIBLE);
            holder.phoneicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + faculty.getPhone()));
                    if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(intent);
                    }
                }
            });
        }

        if(faculty.getEmail().length() < 2) {
            holder.emailicon.setVisibility(View.GONE);
        }else {
            holder.emailicon.setVisibility(View.VISIBLE);
            holder.emailicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isMailClientPresent(mContext)){
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{ faculty.getEmail()});
                        mContext.startActivity(Intent.createChooser(intent, "Send Mail via"));
                    }else{
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=email&c=apps")));
                    }
                }
            });
        }

        /*
         *   loading faculty pictures
         *   making them cirular
         *   using Glide library
         */
        try{
            Glide.with(mContext).load(faculty.getUrl()).bitmapTransform(new CropCircleTransformation(mContext)).listener(requestListener).error( R.drawable.ic_person ).placeholder(R.drawable.ic_person).into(holder.thumbnail);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    /**
     * This method will return boolean value
     * depending on email packages availbale
     * on the device
     * @param context
     * @return
     */
    public boolean isMailClientPresent(Context context){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);

        if(list.size() == 0)
            return false;
        else
            return true;
    }


    private RequestListener<String, GlideDrawable> requestListener = new RequestListener<String, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            return false;
        }
    };
}