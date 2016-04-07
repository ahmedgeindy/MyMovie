package com.example.titos;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.titos.moviesapp.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        gridView = (GridView) findViewById(R.id.gird_View);
        getPoster myp = new getPoster(this, gridView);
        myp.execute();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.titos/http/host/path")
        );

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.titos/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class getPoster extends AsyncTask<Void, Void, ArrayList<String>> {
        Context con1;
        GridView secondgird;
        ArrayList<String> mPoster;

        public getPoster(Context con, GridView Firistgrid) {
            con1 = con;
            secondgird = Firistgrid;
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            final String base_string = "https://api.themoviedb.org/3/movie/popular?";
            BufferedReader reader = null;
            String posterJson = "";
            URL url = null;

            String movieJsonStr = null;

            Uri uri = Uri.parse(base_string).buildUpon()
                    .appendQueryParameter("api_key", "c9bb36f94aaa138a81f9546d2e558da8").build();

            try {
                url = new URL(uri.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            try {
//               url= new URL("https://");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
            } catch (IOException e) {
                //  Toast.makeText(c1,e.getMessage(),Toast.LENGTH_SHORT).show();
            }

            try {
                InputStream streamReader = urlConnection.getInputStream();
                if (streamReader == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(streamReader));
                String line;
                while ((line = reader.readLine()) != null) {
                    posterJson += line;
                    Log.v("hi", line);
                }
                //Toast.makeText(c1,"size "+String.valueOf(posterJson.length()),Toast.LENGTH_SHORT).show();
                JSONObject jsonObject = new JSONObject(posterJson);
                JSONArray arrayJ = jsonObject.getJSONArray("results");
                mPoster = new ArrayList<String>();
                for (int i = 0; i < arrayJ.length(); i++) {
                    JSONObject mymovie = arrayJ.getJSONObject(i);
                    String s = new String("http://image.tmdb.org/t/p/w185");
                    s = s + mymovie.getString("poster_path");
                    Log.v("hello", s);
                    mPoster.add(s);

                }
                return mPoster;

            } catch (Exception e) {
                return null;
                // Toast.makeText(c1,e.getMessage(),Toast.LENGTH_SHORT).show();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("ahmed", "Error closing stream", e);

                    }
            }

        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            Gird_view_AdpterFragment myg1 = new Gird_view_AdpterFragment(con1, R.layout.image_movie_view, strings);
            secondgird.setAdapter(myg1);
        }

    }

}