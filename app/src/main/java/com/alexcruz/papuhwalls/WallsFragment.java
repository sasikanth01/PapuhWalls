package com.alexcruz.papuhwalls;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.mrengineer13.snackbar.SnackBar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;


public class WallsFragment extends ActionBarActivity {

    private Toolbar toolbar;
    public String wall;
    private String saveWallLocation, picName, dialogContent;
    private File destWallFile;
    private View fabBg;
    private Activity context;
    Preferences Preferences;

    private static final int ACTIVITY_SHARE = 13452;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.Preferences = new Preferences(getApplicationContext());
        setContentView(R.layout.wallpaper_activity);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_ab_detailed_wallpaper);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        fabBg = findViewById(R.id.fabBg);
        fabBg.setVisibility(View.GONE);

        saveWallLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getResources().getString(R.string.walls_save_location);
        picName = context.getResources().getString(R.string.walls_prefix_name);

        dialogContent = getResources().getString(R.string.download_done) + " " + saveWallLocation;

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);

        if (isFirstRun) {

            File folder = new File(saveWallLocation);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isfirstrun", false).commit();

        }

        setFullScreen();

        scan(this, "external");

        fabBg = findViewById(R.id.fabBg);

        ImageView image = (ImageView) findViewById(R.id.bigwall);
        wall = getIntent().getStringExtra("wall");
        destWallFile = new File(saveWallLocation + "/" + picName + convertWallName(wall) + ".png");

        final Point displySize = getDisplaySize(getWindowManager().getDefaultDisplay());
        final int size = (int) Math.ceil(Math.sqrt(displySize.x * displySize.y));
        Picasso.with(this)
                .load(wall)
                .resize(size, size)
                .centerCrop()
                .into(image);

        final FloatingActionsMenu wallsFab = (FloatingActionsMenu) findViewById(R.id.wall_actions);
        wallsFab.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                fabBg.setVisibility(fabBg.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                fabBg.setBackgroundColor(Preferences.FABbackground());
            }

            @Override
            public void onMenuCollapsed() {
                fabBg.setVisibility(fabBg.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        final FloatingActionButton setWall = (FloatingActionButton) findViewById(R.id.setwall);
        setWall.setColorNormal(Preferences.FABapply());
        setWall.setColorPressed(Preferences.FABpressed());
        setWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadBitmap(context, wall, destWallFile, new Callback<Pair<Uri, Bitmap>>() {
                    @Override
                    public void callback(Pair<Uri, Bitmap> object) {
                        try {
                            WallpaperManager wm = WallpaperManager.getInstance(context);
                            wm.setBitmap(object.second);

                            new SnackBar.Builder(context)
                                    .withMessageId(R.string.set_as_wall_done)
                                    .withActionMessageId(R.string.ok)
                                    .withStyle(SnackBar.Style.ALERT)
                                    .withDuration(SnackBar.SHORT_SNACK)
                                    .show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).execute();

            }
        });

        final FloatingActionButton saveWall = (FloatingActionButton) findViewById(R.id.savewall);
        saveWall.setColorNormal(Preferences.FABsave());
        saveWall.setColorPressed(Preferences.FABpressed());
        saveWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadBitmap(context, wall, destWallFile, new Callback<Pair<Uri, Bitmap>>() {
                    @Override
                    public void callback(Pair<Uri, Bitmap> object) {

                        MaterialDialog dialog = new MaterialDialog.Builder(context)
                                .title(getString(R.string.done))
                                .content(dialogContent)
                                .dismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        setFullScreen();
                                    }
                                })
                                .build();

                        dialog.setActionButton(DialogAction.NEGATIVE, R.string.close);
                        dialog.show();

                    }
                }).execute();

            }
        });

        final FloatingActionButton cropWall = (FloatingActionButton) findViewById(R.id.cropwall);
        cropWall.setColorNormal(Preferences.FABcrop());
        cropWall.setColorPressed(Preferences.FABpressed());
        cropWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadBitmap(context, wall, destWallFile, new Callback<Pair<Uri, Bitmap>>() {
                    @Override
                    public void callback(Pair<Uri, Bitmap> object) {
                        Intent setWall = new Intent(Intent.ACTION_ATTACH_DATA);
                        setWall.setDataAndType(object.first, "image/*");
                        setWall.putExtra("png", "image/*");
                        startActivityForResult(Intent.createChooser(setWall, getString(R.string.set_as)), 1);
                    }
                }).execute();

            }
        });

        final FloatingActionButton editWall = (FloatingActionButton) findViewById(R.id.editwall);
        editWall.setColorNormal(Preferences.FABedit());
        editWall.setColorPressed(Preferences.FABpressed());
        editWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadBitmap(context, wall, destWallFile, new Callback<Pair<Uri, Bitmap>>() {
                    @Override
                    public void callback(Pair<Uri, Bitmap> object) {
                        Intent editWall = new Intent(Intent.ACTION_EDIT);
                        editWall.setDataAndType(object.first, "image/*");
                        editWall.putExtra("png", "image/*");
                        startActivityForResult(Intent.createChooser(editWall, getString(R.string.edit_wall)), 1);
                    }
                }).execute();

            }
        });

        final FloatingActionButton shareWall = (FloatingActionButton) findViewById(R.id.sharewall);
        shareWall.setColorNormal(Preferences.FABshare());
        shareWall.setColorPressed(Preferences.FABpressed());
        shareWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DownloadBitmap(context, wall, destWallFile, new Callback<Pair<Uri, Bitmap>>() {
                    @Override
                    public void callback(Pair<Uri, Bitmap> object) {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_STREAM, object.first);
                        WallsFragment.this.startActivityForResult(Intent.createChooser(shareIntent, "Share Via"), ACTIVITY_SHARE);
                    }
                }).execute();

            }
        });
    }

    private void setFullScreen() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


    }

    public Point getDisplaySize(Display display) {
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            size = new Point(width, height);
        }

        return size;
    }

    private String convertWallName(String link) {
        return (link
                .replaceAll("png", "")
                .replaceAll("jpg", "")
                .replaceAll("jpeg", "")
                .replaceAll("bmp", "")
                .replaceAll("[^a-zA-Z0-9\\p{Z}]", "")
                .replaceFirst("^[0-9]+(?!$)", "")
                .replaceAll("\\p{Z}", "_"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setFullScreen();
    }

    private static void scan(Context context, String volume) {
        Bundle args = new Bundle();
        args.putString("volume", volume);
        context.startService(
                new Intent().
                        setComponent(new ComponentName("com.android.providers.media", "com.android.providers.media.MediaScannerService")).
                        putExtras(args));
    }

    private static class DownloadBitmap extends AsyncTask<Void, Void, Pair<Uri, Bitmap>> {

        private WeakReference<Activity> activity;
        private String url;
        private File dest;
        private Callback<Pair<Uri, Bitmap>> callback;

        public DownloadBitmap(Activity activity, String url, File dest, Callback<Pair<Uri, Bitmap>> callback) {
            this.url = url;
            this.dest = dest;
            this.activity = new WeakReference<>(activity);
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            new SnackBar.Builder(activity.get())
                    .withMessageId(R.string.wallpaper_downloading_wait)
                    .withActionMessageId(R.string.ok)
                    .withStyle(SnackBar.Style.ALERT)
                    .withDuration(SnackBar.SHORT_SNACK)
                    .show();
        }

        @Override
        protected Pair<Uri, Bitmap> doInBackground(Void... params) {

            Bitmap bitmap = null;
            Uri uri = null;

            try {

                bitmap = Picasso.with(activity.get()).load(url).get();

                dest.getParentFile().mkdirs();
                if (dest.exists()) {
                    dest.delete();
                }
                FileOutputStream out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.close();

                uri = Uri.fromFile(dest);

                scan(activity.get(), "external");

            } catch (Exception e) {
                e.printStackTrace();
            }


            return new Pair<>(uri, bitmap);
        }

        @Override
        protected void onPostExecute(Pair<Uri, Bitmap> uriBitmapPair) {
            callback.callback(uriBitmapPair);
        }
    }

}
