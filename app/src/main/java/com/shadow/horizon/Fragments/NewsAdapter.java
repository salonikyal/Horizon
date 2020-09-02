package com.shadow.horizon.Fragments;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shadow.horizon.R;

import java.util.ArrayList;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    @SuppressWarnings("unused")

    //private ArrayList<BinDetails> binlist;
    private ArrayList<news> newslist;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        Html.ImageGetter im;
        ImageButton img;
        TextView au;
        TextView ti;
        TextView des;
        TextView pub;
        Context context;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageButton) itemView.findViewById(R.id.im);
            ti = (TextView) itemView.findViewById(R.id.title);
            au = (TextView) itemView.findViewById(R.id.au);
            cardView = (CardView) itemView.findViewById(R.id.card_viewBin);
            des = (TextView) itemView.findViewById(R.id.desc);
            pub = (TextView) itemView.findViewById(R.id.publish);

            context = itemView.getContext();


        }

    }

    public NewsAdapter(ArrayList<news> newslist) {
        this.newslist = newslist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ArrayList<String> tempbincode = new ArrayList<>();


        //System.out.println(locationlist1.size() + " CHECKING SIZE " + binlist.size());

        final news info = newslist.get(position);

       //String capitalize_binname = info.getBinName().substring(0, 1).toUpperCase() + info.getBinName().substring(1);
        holder.au.setText(info.getAuthor());
        holder.ti.setText(info.getTitl());
        holder.des.setText(info.getDes());
        holder.pub.setText(info.getPublish());
        //holder.img.setImageResource(info.getUrlImage());




        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implement onClick






            }
        });


    }

    public int dpToPixel(float dp, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public int getItemCount() {
        return newslist.size();
    }


}





