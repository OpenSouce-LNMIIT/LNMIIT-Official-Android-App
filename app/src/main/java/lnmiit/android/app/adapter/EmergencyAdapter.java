package lnmiit.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import lnmiit.android.app.R;
import lnmiit.android.app.model.EmergencyDetails;

/**
 * Created by Chanpreet on 25-09-2016.
 */

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<EmergencyDetails> list = new ArrayList<>();
    String color[] = {"#EF5350", "#AB47BC", "#9575CD", "#FF5722", "#78909C", "#FFCA28", "#69F0AE"};

    public EmergencyAdapter(Context context1, ArrayList<EmergencyDetails> list) {
        context = context1;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_emergency, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Random r = new Random();
        holder.name.setText(list.get(position).getEmergencyName());
        holder.phone_text.setText(list.get(position).getPhone());
        holder.text.setText(list.get(position).getEmergencyName().charAt(0) + "");
        GradientDrawable backgroundGradient = (GradientDrawable) holder.letterIcon.getBackground();
        backgroundGradient.setColor(Color.parseColor(color[position]));
        final String contact = list.get(position).getPhone();
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + contact));
                context.startActivity(phoneIntent

                );
            }
        });
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone_text;
        private Button phone;
        private ImageView letterIcon;
        private TextView text;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.emergency_tv1);
            phone_text = (TextView) view.findViewById(R.id.emergency_tv2);
            phone = (Button) view.findViewById(R.id.emergency_phone);
            letterIcon = (ImageView) view.findViewById(R.id.emergency_lettericon);
            text = (TextView) view.findViewById(R.id.emergency_letter);
        }

    }
}
