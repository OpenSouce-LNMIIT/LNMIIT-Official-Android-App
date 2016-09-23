package lnmiit.android.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lnmiit.android.app.R;
import lnmiit.android.app.model.UpdateDetail;

/**
 * Created by dexter on 22/9/16.
 */
public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder> {

    private Context mContext;
    private List<UpdateDetail> updateList ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title ;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }


    }


    public UpdateAdapter(Context mContext, List<UpdateDetail> updateList) {
        this.mContext = mContext;
        this.updateList = updateList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.update_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        UpdateDetail item = updateList.get(position);
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return updateList.size();
    }

}
