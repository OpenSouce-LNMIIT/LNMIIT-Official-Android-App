package lnmiit.android.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
import lnmiit.android.app.model.AdministrationDetail;

/**
 * Created by Chanpreet on 25-09-2016.
 */
public class AdministrationAdapter extends RecyclerView.Adapter<AdministrationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AdministrationDetail> list = new ArrayList<>();
    String color[] = {"#EF5350", "#AB47BC", "#9575CD", "#FF5722", "#78909C", "#FFCA28", "#69F0AE"};

    public AdministrationAdapter(Context context1, ArrayList<AdministrationDetail> list) {
        context = context1;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_administration, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Random r = new Random();
        holder.adminName.setText(list.get(position).getAdminName());
        holder.adminDesignation.setText(list.get(position).getDesignation());
        holder.adminEmail.setText(list.get(position).getMail());
        holder.text.setText(list.get(position).getAdminName().charAt(0) + "");
        GradientDrawable backgroundGradient = (GradientDrawable) holder.letterIcon.getBackground();
        backgroundGradient.setColor(Color.parseColor(color[position]));
        final String mail = list.get(position).getMail();
        holder.adminEmailbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("plain/text");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
                context.startActivity(mailIntent);
            }
        });
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView adminName;
        private TextView adminDesignation;
        private TextView adminEmail;
        private Button adminEmailbt;
        private ImageView letterIcon;
        private TextView text;

        ViewHolder(View view) {
            super(view);
            adminName = (TextView) view.findViewById(R.id.admin_name);
            adminDesignation = (TextView) view.findViewById(R.id.admin_designation);
            adminEmail = (TextView) view.findViewById(R.id.admin_email);
            adminEmailbt = (Button) view.findViewById(R.id.admin_email_button);
            letterIcon = (ImageView) view.findViewById(R.id.admin_lettericon);
            text = (TextView) view.findViewById(R.id.admin_text);
        }

    }
}