package com.alexcruz.papuhwalls.Walls;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alexcruz.papuhwalls.JSONParser;
import com.alexcruz.papuhwalls.R;
import com.alexcruz.papuhwalls.WallsFragment;
import com.alexcruz.papuhwalls.WallsGridAdapter;
import com.github.mrengineer13.snackbar.SnackBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbsWalls extends Fragment {

    public static String NAME = "name";
    public static String WALL = "wall";
    public static String AUTHOR = "author";
    JSONObject jsonobject;
    JSONArray jsonarray;
    GridView mGridView;
    WallsGridAdapter mGridAdapter;
    ArrayList<HashMap<String, String>> arraylist;
    private ViewGroup root;
    private Context context;
    public static int numColumns;

    public abstract int getTitleId();

    public abstract int getUrlId();

    public abstract String getJsonArrayName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        ActionBar toolbar = ((ActionBarActivity) context).getSupportActionBar();
        toolbar.setTitle(getTitleId());

        root = (ViewGroup) inflater.inflate(R.layout.wallpapers, null);

        new DownloadJSON().execute();
        return root;
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            arraylist = new ArrayList<>();
            JSONObject json = JSONParser.getJSONfromURL(getResources().getString(getUrlId()));
            if (json != null) try {
                JSONArray jsonarray = json.getJSONArray(getJsonArrayName());

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<>();
                    json = jsonarray.getJSONObject(i);
                    map.put("name", json.getString("name"));
                    map.put("author", json.getString("author"));
                    map.put("wall", json.getString("url"));
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                new SnackBar.Builder(getActivity().getApplicationContext(), root)
                        .withMessageId(R.string.json_error_toast)
                        .withActionMessageId(R.string.ok)
                        .withStyle(SnackBar.Style.ALERT)
                        .withDuration(SnackBar.MED_SNACK)
                        .show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            mGridView = (GridView) root.findViewById(R.id.gridView);
            mGridView.setNumColumns(numColumns);
            mGridAdapter = new WallsGridAdapter(context, arraylist, numColumns);
            mGridView.setAdapter(mGridAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> data = arraylist.get(position);
                    String wallurl = data.get((WALL));
                    Intent intent = new Intent(context, WallsFragment.class);
                    intent.putExtra("wall", wallurl);
                    context.startActivity(intent);
                }
            });
        }
    }

}
