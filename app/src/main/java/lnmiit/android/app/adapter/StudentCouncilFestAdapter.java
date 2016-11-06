package lnmiit.android.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import lnmiit.android.app.R;
import lnmiit.android.app.model.CouncilFestDetail;

/**
 * Created by Chanpreet on 16-09-2016.
 */
public class StudentCouncilFestAdapter extends RecyclerView.Adapter<StudentCouncilFestAdapter.viewholder>{
    private ArrayList<CouncilFestDetail> list = new ArrayList<>();
    private Context context;
   public  StudentCouncilFestAdapter( Context context, ArrayList<CouncilFestDetail> list)
    {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_council_fest,parent,false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        CouncilFestDetail data = list.get(position);

        holder.councilName.setClickable(true);
       holder.councilName.setText(data.getname());
        holder.councilDescription.setText(data.getDescription());
    }

    protected class viewholder extends RecyclerView.ViewHolder{


        private TextView councilName;
        private TextView councilDescription;
        viewholder(View view)
        {
            super(view);
            councilName =(TextView) view.findViewById(R.id.council_name);
            councilDescription =(TextView)view.findViewById(R.id.council_content);
        }
    }
}
