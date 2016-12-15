package lnmiit.android.app.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.model.ImageDetail;

/**
 * Created by dexter on 15/12/16.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

       private ArrayList<ImageDetail> imageDetails ;
        private Context mContext;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView thumbnail;
            private TextView title ;

            public MyViewHolder(View view) {
                super(view);
                thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
                title = (TextView) view.findViewById(R.id.titleImage);
            }
        }


        public GalleryAdapter(Context context, ArrayList<ImageDetail> imageDetails) {
            mContext = context;
            this.imageDetails = imageDetails ;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ImageDetail image = imageDetails.get(position);

            Glide.with(mContext).load(image.getId())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.thumbnail);
            holder.title.setText(image.getTitle());
        }

        @Override
        public int getItemCount() {
            return imageDetails.size();
        }

        public interface ClickListener {
            void onClick(View view, int position);

            void onLongClick(View view, int position);
        }

        public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

            private GestureDetector gestureDetector;
            private GalleryAdapter.ClickListener clickListener;

            public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final GalleryAdapter.ClickListener clickListener) {
                this.clickListener = clickListener;
                gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                        }
                    }
                });
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                    clickListener.onClick(child, rv.getChildPosition(child));
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        }
}
