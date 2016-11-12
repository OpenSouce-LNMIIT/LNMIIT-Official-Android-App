package lnmiit.android.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import lnmiit.android.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademicsUG extends Fragment {
    TextView ece, cse, mme, cce, me;
    Button cseb, eceb, mmeb, cceb, meb, bt_cse,bt_ece,bt_cce,bt_mme,bt_me;
    ScrollView scroll;

    public AcademicsUG() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmnt_academics_ug, container, false);
        scroll = (ScrollView) view.findViewById(R.id.scroll1);
        ece = (TextView) view.findViewById(R.id.about_ece);
        cce = (TextView) view.findViewById(R.id.about_cce);
        cse = (TextView) view.findViewById(R.id.about_cse);
        mme = (TextView) view.findViewById(R.id.about_mech);
        me = (TextView) view.findViewById(R.id.about_me);
        cseb = (Button) view.findViewById(R.id.b_cse);
        eceb = (Button) view.findViewById(R.id.b_ece);
        cceb = (Button) view.findViewById(R.id.b_cce);
        mmeb = (Button) view.findViewById(R.id.b_mme);
        meb = (Button) view.findViewById(R.id.b_me);
        bt_cse = (Button) view.findViewById(R.id.btn_cse);
        bt_ece = (Button) view.findViewById(R.id.btn_ece);
        bt_cce = (Button) view.findViewById(R.id.btn_cce);
        bt_mme = (Button) view.findViewById(R.id.btn_mme);
        bt_me = (Button) view.findViewById(R.id.btn_me);


        cce.setVisibility(View.GONE);
        ece.setVisibility(View.GONE);
        cse.setVisibility(View.GONE);
        mme.setVisibility(View.GONE);
        me.setVisibility(View.GONE);


        mmeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mme.setVisibility((mme.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        bt_mme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mme.setVisibility((mme.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        cceb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cce.setVisibility((cce.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        bt_cce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cce.setVisibility((cce.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        cseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cse.setVisibility((cse.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });

        bt_cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cse.setVisibility((cse.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });



        meb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0,scroll.getBottom());
                    }
                });

                me.setVisibility((me.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        bt_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0,scroll.getBottom());
                    }
                });

                me.setVisibility((me.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        eceb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ece.setVisibility((ece.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });

        bt_ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ece.setVisibility((ece.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

            }
        });
        return view;
    }

}
