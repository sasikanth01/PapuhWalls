package com.alexcruz.papuhwalls;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mikepenz.materialdrawer.Drawer;


/**
 * Created by mazda on 9/11/15.
 */
public class SettingsActivity extends PreferenceActivity {
        Toolbar toolbar;

        private Drawer result = null;

        Preferences Preferences;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerTheme;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerNavBarTheme;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerNormalIcon;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerSelectedIcon;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerPrimaryText;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerSecondaryText;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerAccent;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABapply;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABsave;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABcrop;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABedit;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABshare;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABpressed;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerFABbackground;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerTimeContextHeaders;
        private SharedPreferences.OnSharedPreferenceChangeListener mListenerPolyTimeContextHeaders;

        @SuppressWarnings("deprecation")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                this.Preferences = new Preferences(getApplicationContext());

                super.onCreate(savedInstanceState);
                addPreferencesFromResource(R.xml.settings);

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
                        toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.app_bar, root, false);
                        root.addView(toolbar, 0);

                        toolbar.setTitle(getResources().getString(R.string.settings));
                } else {
                        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
                        ListView content = (ListView) root.getChildAt(0);

                        root.removeAllViews();

                        toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.app_bar, root, false);

                        int height;
                        TypedValue tv = new TypedValue();
                        if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
                        } else {
                                height = toolbar.getHeight();
                        }

                        content.setPadding(0, height, 0, 0);

                        root.addView(content);
                        root.addView(toolbar);
                        toolbar.setTitle(getResources().getString(R.string.settings));

                }

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        toolbar.setBackgroundColor(Preferences.Theme());
                        getWindow().getDecorView().setBackgroundColor(Preferences.Background());
                        getWindow().setStatusBarColor(Preferences.Theme());
                        if (Preferences.getNavigationTint()) {
                                getWindow().setNavigationBarColor(Preferences.NavBarTheme());
                        }
                }

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(tint(Preferences.Theme(), 0.8));
                }

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                finish();
                        }
                });

                mListenerTheme = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String id) {
                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                toolbar.setBackgroundColor(Preferences.Theme());
                                getWindow().getDecorView().setBackgroundColor(Preferences.Background());
                                getWindow().setStatusBarColor(Preferences.Theme());
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerNavBarTheme = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String id) {
                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        if (Preferences.getNavigationTint()) {
                                                getWindow().setNavigationBarColor(Preferences.NavBarTheme());
                                        }
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerPrimaryText = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_primary_text))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerSecondaryText = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_secondary_text))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerAccent = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_accent))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABapply = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_apply))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABsave = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_save))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABcrop = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_crop))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABedit = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_edit))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABshare = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_share))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABpressed = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_pressed))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerFABbackground = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_fab_background))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerNormalIcon = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_drawer_normal_icon))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerSelectedIcon = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.theme_drawer_selected_icon))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerTimeContextHeaders = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.time_context_headers))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
                mListenerPolyTimeContextHeaders = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                                if (!key.equals(getString(R.string.poly_time_context_headers))) {
                                        return;
                                }
                                finish();
                                final Intent intent = IntentCompat.makeMainActivity(new ComponentName(
                                        SettingsActivity.this, MainActivity.class));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                };
        }

        public static int tint (int color, double factor) {
                int a = Color.alpha(color);
                int r = Color.red(color);
                int g = Color.green( color );
                int b = Color.blue(color);

                return Color.argb(a, Math.max( (int)(r * factor), 0 ), Math.max( (int)(g * factor), 0 ), Math.max( (int)(b * factor), 0 ) );
        }

        @Override
        public void onResume() {
                super.onResume();
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerTheme);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerNavBarTheme);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerPrimaryText);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerSecondaryText);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerAccent);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABapply);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABsave);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABcrop);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABedit);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABshare);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABpressed);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerFABbackground);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerNormalIcon);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerSelectedIcon);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerTimeContextHeaders);
                getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(mListenerPolyTimeContextHeaders);
        }

        @Override
        public void onPause() {
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerTheme);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerNavBarTheme);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerPrimaryText);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerSecondaryText);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerAccent);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABapply);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABsave);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABcrop);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABedit);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABshare);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABpressed);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerFABbackground);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerNormalIcon);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerSelectedIcon);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerTimeContextHeaders);
                getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(mListenerPolyTimeContextHeaders);
                super.onPause();
        }
}
