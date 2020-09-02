package com.shadow.horizon.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadow.horizon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private ArrayList<news> newsList = new ArrayList<>();

    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;


    JSONParser jsonParser = new JSONParser();

    private static final String News_URL = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=d5790a6e12524330984a36b7755b3627";

    private static final String stat = "status";
    private static final String articles = "articles";
    private static final String author = "author";
    private static final String title = "title";
    private static final String des = "description";
    private static final String publish = "publishedAt";
    private static final String url = "url";
    private static final String urlToImage = "urlToImage";


    private static String status;
    private static String ne_author;
    private static String ne_title;
    private static String ne_des;
    private static String ne_pub;
    private static String ne_url;
    private static String ne_urlImage;


    Context context;

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;



    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        new news_info().execute();

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mAdapter = new NewsAdapter(newsList);
        //recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }


    /*  @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.fragment_home);


          new news_info().execute();

          recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
          mAdapter = new NewsAdapter(newsList);
          //recyclerView.setAdapter(mAdapter);

          RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
          recyclerView.setLayoutManager(mLayoutManager);
          recyclerView.setItemAnimator(new DefaultItemAnimator());


      } */
    private class news_info extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            //  if (new CheckNetwork(Home.this).isNetworkAvailable()) {

            Log.d("request!", "starting");
            try {
                JSONObject json1 = jsonParser.getJSONFromUrl(News_URL);
                status=json1.getString(stat);
                System.out.println("jkbsxkKMGNJJNFNJFNFNJDFNJNJFDDNJTHJRNJHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHasbx"+status);




            } catch (JSONException e) {
                e.printStackTrace();
            }


                try {
                JSONObject json1 = jsonParser.getJSONFromUrl(News_URL);
                status=json1.getString(stat);
                System.out.println("jkbsxkKMGNJJNFNJFNFNJDFNJNJFDDNJTHJRNJHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHasbx"+status);
                JSONArray art = json1.getJSONArray(articles);
                for (int i = 0; i < art.length(); i++) {

                    JSONObject c1 = art.getJSONObject(i);


                    ne_author = c1.getString(author);
                    ne_des = c1.getString(des);
                    ne_title = c1.getString(title);
                    ne_url = c1.getString(url);
                    ne_urlImage = c1.getString(urlToImage);
                    ne_pub = c1.getString(publish);


                    System.out.println("Bin name"+ne_author+ne_des+ne_title);

                    news news = new news();

                    news.setAuthor(ne_author);
                    news.setDes(ne_des);
                    news.setPublish(ne_pub);
                    news.setTitl(ne_title);
                    news.setUrl(ne_url);
                    news.setUrlImage(ne_urlImage);
                    newsList.add(news);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

           /* }else{
                Home.this.runOnUiThread(new Runnable() {
                    public void run() {
                        // Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(Home.this);
                        builder.setCancelable(false);
                        builder.setTitle("Error");
                        builder.setMessage("No internet connection.");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                                finish();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }*/
            return null;
        }

        protected void onPostExecute(String message) {

            /*if (dialog.isShowing()) {
                dialog.dismiss();
            }*/

            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }
    public class CheckNetwork {
        private Context context;

        public CheckNetwork(Context context) {
            this.context = context;
        }

        public boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

}
