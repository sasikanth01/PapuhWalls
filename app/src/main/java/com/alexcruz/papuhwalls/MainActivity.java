package com.alexcruz.papuhwalls;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mrengineer13.snackbar.SnackBar;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.File;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;

public class MainActivity extends ActionBarActivity  {

    private String Home;
    private String AllWalls;
    private String AndroidWalls;
    private String BwWalls;
    private String BlurWalls;
    private String CloudsWalls;
    private String MiscWalls;
    private String MustachesWalls;
    private String NexusWalls;
    private String OriginalWalls;
    private String PapuhWalls;
    private String SolidsWalls;
    private String StarsWalls;
    private String RomCategory;
    private String AicpWalls;
    private String BrokenOsWalls;
    private String CandyWalls;
    private String DuWalls;
    private String EosWalls;
    private String LiquidsmoothWalls;
    private String OmniWalls;
    private String OrionWalls;
    private String PaWalls;
    private String PacromWalls;
    private String RrWalls;
    private String SlimWalls;
    private String TeslaWalls;
    private String TwistedAOSPWalls;
    private String ValidusWalls;
    private String Settings;
    private String MuzeiSettings;
    private String AboutApp;

    private Drawer result = null;
    private AccountHeader headerResult = null;
    public int currentItem;
    private Preferences mPrefs;
    private Context context;
    private SharedPreferences prefs;

    private static final int PROFILE_SETTING = 1;
    private final static int REQUEST_READ_STORAGE_PERMISSION = 1;

    Preferences Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.Preferences = new Preferences(getApplicationContext());

        if (PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(getString(R.string.theme_dark_material_drawer_icons), false)) {
            setTheme(R.style.DarkMaterialDrawerIcons);
        } else {
            // Do absolutely NOTHING
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        context = this;
        mPrefs = new Preferences(MainActivity.this);
        CustomActivityOnCrash.install(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs.getBoolean(Preferences.IS_FIRST__RUN, true)) {
            startActivity(new Intent(this, Slides.class));
            finish();
        }

        if (Build.VERSION.SDK_INT >= 23 && PermissionChecker
                .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PermissionChecker.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_STORAGE_PERMISSION);
        } else {
            // Do absolutely NOTHING
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            toolbar.setBackgroundColor(Preferences.Theme());
            getWindow().getDecorView().setBackgroundColor(Preferences.Background());
            getWindow().setStatusBarColor(Preferences.Theme());
        }

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            if (Preferences.getNavigationTint()) {
                getWindow().setNavigationBarColor(Preferences.NavBarTheme());
            }
        }

        Home = getResources().getString(R.string.app_name);
        final String Home = getResources().getString(R.string.section_home);
        AllWalls = getResources().getString(R.string.section_all_walls);
        AndroidWalls = getResources().getString(R.string.section_android_walls);
        BwWalls = getResources().getString(R.string.section_bw_walls);
        BlurWalls = getResources().getString(R.string.section_blur_walls);
        CloudsWalls = getResources().getString(R.string.section_clouds_walls);
        MiscWalls = getResources().getString(R.string.section_misc_walls);
        MustachesWalls = getResources().getString(R.string.section_mustaches_walls);
        NexusWalls = getResources().getString(R.string.section_nexus_walls);
        OriginalWalls = getResources().getString(R.string.section_original_walls);
        PapuhWalls = getResources().getString(R.string.section_papuh_walls);
        SolidsWalls = getResources().getString(R.string.section_solids_walls);
        StarsWalls = getResources().getString(R.string.section_stars_walls);
        RomCategory = getResources().getString(R.string.section_rom_category);
        AicpWalls = getResources().getString(R.string.section_aicp_walls);
        BrokenOsWalls = getResources().getString(R.string.section_brokenos_walls);
        CandyWalls = getResources().getString(R.string.section_candy_walls);
        DuWalls = getResources().getString(R.string.section_du_walls);
        EosWalls = getResources().getString(R.string.section_eos_walls);
        LiquidsmoothWalls = getResources().getString(R.string.section_liquidsmooth_walls);
        OmniWalls = getResources().getString(R.string.section_omni_walls);
        OrionWalls = getResources().getString(R.string.section_orion_walls);
        PaWalls = getResources().getString(R.string.section_pa_walls);
        PacromWalls = getResources().getString(R.string.section_pacrom_walls);
        RrWalls = getResources().getString(R.string.section_rr_walls);
        SlimWalls = getResources().getString(R.string.section_slim_walls);
        TeslaWalls = getResources().getString(R.string.section_tesla_walls);
        TwistedAOSPWalls = getResources().getString(R.string.section_twistedaosp_walls);
        ValidusWalls = getResources().getString(R.string.section_validus_walls);
        Settings = getResources().getString(R.string.settings);
        MuzeiSettings = getResources().getString(R.string.muzei_settings);
        AboutApp = getResources().getString(R.string.section_aboutapp);

        currentItem = 1;

        final IProfile profile = new ProfileDrawerItem().withName("Alex Cruz aka Mazda").withIcon(getResources().getDrawable(R.drawable.alexcruz)).withIdentifier(1);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSelectionFirstLine(getResources().getString(R.string.app_long_name))
                .withSelectionSecondLine(getResources().getString(R.string.app_dev_name))
                .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        profile
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            if (headerResult.getProfiles() != null) {
                                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]
                                        {getResources().getString(R.string.email_address)});
                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                        getResources().getText(R.string.artsource_name));
                                emailIntent.setType("plain/text");
                                startActivity(Intent.createChooser(emailIntent, "Contact Alex"));
                            } else {
                                headerResult.addProfiles(profile);
                            }
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withHeader(R.layout.header)
                .withSavedInstance(savedInstanceState)
                .withFooterDivider(true)
                .withSliderBackgroundColor(Preferences.Drawer())
                .withStatusBarColor(tint(Preferences.Theme(), 0.8))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(Home).withIcon(R.drawable.ic_home).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(1),
                        new PrimaryDrawerItem().withName(AllWalls).withIcon(R.drawable.ic_allwalls).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(2).withBadge("1554+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(AndroidWalls).withIcon(R.drawable.ic_android).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(3).withBadge("17+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(BwWalls).withIcon(R.drawable.ic_bw).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(4).withBadge("56+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(BlurWalls).withIcon(R.drawable.ic_blur).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(5).withBadge("10+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(CloudsWalls).withIcon(R.drawable.ic_clouds).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(6).withBadge("60+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(MiscWalls).withIcon(R.drawable.ic_misc).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(7).withBadge("41+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(MustachesWalls).withIcon(R.drawable.ic_mustaches).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(8).withBadge("10+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(NexusWalls).withIcon(R.drawable.ic_nexus).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(9).withBadge("97+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(OriginalWalls).withIcon(R.drawable.ic_original).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(10).withBadge("282+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(PapuhWalls).withIcon(R.drawable.ic_papuh).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(11).withBadge("24+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(SolidsWalls).withIcon(R.drawable.ic_solids).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(12).withBadge("72+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(StarsWalls).withIcon(R.drawable.ic_stars).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(13).withBadge("11+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new SectionDrawerItem().withName(RomCategory).withTypeface(Typeface.DEFAULT_BOLD).withTextColor(Preferences.DrawerText()),
                        new PrimaryDrawerItem().withName(AicpWalls).withIcon(R.drawable.ic_aicp).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(15).withBadge("84+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(BrokenOsWalls).withIcon(R.drawable.ic_brokenos).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(16).withBadge("53+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(CandyWalls).withIcon(R.drawable.ic_candy).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(17).withBadge("80+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(DuWalls).withIcon(R.drawable.ic_dirtyunicorns).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(18).withBadge("79+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(EosWalls).withIcon(R.drawable.ic_eos).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(19).withBadge("48+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(LiquidsmoothWalls).withIcon(R.drawable.ic_liquidsmooth).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(20).withBadge("65+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(OmniWalls).withIcon(R.drawable.ic_omni).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(21).withBadge("29+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(OrionWalls).withIcon(R.drawable.ic_orion).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(22).withBadge("10+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(PaWalls).withIcon(R.drawable.ic_pa).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(23).withBadge("10+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(PacromWalls).withIcon(R.drawable.ic_pacrom).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(24).withBadge("21+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(RrWalls).withIcon(R.drawable.ic_rr).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(25).withBadge("54+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(SlimWalls).withIcon(R.drawable.ic_slim).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(26).withBadge("8+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(TeslaWalls).withIcon(R.drawable.ic_tesla).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(27).withBadge("43+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(TwistedAOSPWalls).withIcon(R.drawable.ic_twistedaosp).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(28).withBadge("43+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new PrimaryDrawerItem().withName(ValidusWalls).withIcon(R.drawable.ic_validus).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(29).withBadge("258+").withBadgeStyle(new BadgeStyle().withTextColor(Preferences.BadgeText()).withColor(Preferences.BadgeBackground())),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(Settings).withIcon(R.drawable.ic_settings).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(30),
                        new SecondaryDrawerItem().withName(MuzeiSettings).withIcon(R.drawable.ic_muzei).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(31),
                        new SecondaryDrawerItem().withName(AboutApp).withIcon(R.drawable.ic_about).withIconTintingEnabled(true).withSelectedIconColor(Preferences.SelectedIcon()).withIconColor(Preferences.NormalIcon()).withSelectedTextColor(tint(Preferences.SelectedDrawerText(), 1.0)).withSelectedColor(tint(Preferences.DrawerSelector(), 1.0)).withTextColor(Preferences.DrawerText()).withIdentifier(32)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                        boolean isMuzeiInstalled = Preferences.isAppInstalled(context, "net.nurik.roman.muzei");

                        if (drawerItem != null) {
                            switch (drawerItem.getIdentifier()) {
                                case 1:
                                    switchFragment(1, Home, "BaseActivity");
                                    break;
                                case 2:
                                    if (isConnected) {
                                        switchWalls(2, AllWalls, "AllWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 3:
                                    if (isConnected) {
                                        switchWalls(3, AndroidWalls, "AndroidWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 4:
                                    if (isConnected) {
                                        switchWalls(4, BwWalls, "BwWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 5:
                                    if (isConnected) {
                                        switchWalls(5, BlurWalls, "BlurWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 6:
                                    if (isConnected) {
                                        switchWalls(6, BwWalls, "CloudsWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 7:
                                    if (isConnected) {
                                        switchWalls(7, MiscWalls, "MiscWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 8:
                                    if (isConnected) {
                                        switchWalls(8, MustachesWalls, "MustachesWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 9:
                                    if (isConnected) {
                                        switchWalls(9, NexusWalls, "NexusWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 10:
                                    if (isConnected) {
                                        switchWalls(10, OriginalWalls, "OriginalWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 11:
                                    if (isConnected) {
                                        switchWalls(11, PapuhWalls, "PapuhWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 12:
                                    if (isConnected) {
                                        switchWalls(12, SolidsWalls, "SolidsWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 13:
                                    if (isConnected) {
                                        switchWalls(13, StarsWalls, "StarsWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 14:
                                    setTitle(R.string.section_rom_category);
                                    ;
                                    break;
                                case 15:
                                    if (isConnected) {
                                        switchWalls(15, AicpWalls, "AicpWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 16:
                                    if (isConnected) {
                                        switchWalls(16, BrokenOsWalls, "BrokenOsWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 17:
                                    if (isConnected) {
                                        switchWalls(17, CandyWalls, "CandyWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 18:
                                    if (isConnected) {
                                        switchWalls(18, DuWalls, "DuWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 19:
                                    if (isConnected) {
                                        switchWalls(19, EosWalls, "EosWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 20:
                                    if (isConnected) {
                                        switchWalls(20, LiquidsmoothWalls, "LiquidsmoothWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 21:
                                    if (isConnected) {
                                        switchWalls(21, OmniWalls, "OmniWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 22:
                                    if (isConnected) {
                                        switchWalls(22, OrionWalls, "OrionWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 23:
                                    if (isConnected) {
                                        switchWalls(23, PaWalls, "PaWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 24:
                                    if (isConnected) {
                                        switchWalls(24, PacromWalls, "PacromWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 25:
                                    if (isConnected) {
                                        switchWalls(25, RrWalls, "RrWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 26:
                                    if (isConnected) {
                                        switchWalls(26, SlimWalls, "SlimWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 27:
                                    if (isConnected) {
                                        switchWalls(27, TeslaWalls, "TeslaWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 28:
                                    if (isConnected) {
                                        switchWalls(28, TwistedAOSPWalls, "TwistedAOSPWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 29:
                                    if (isConnected) {
                                        switchWalls(29, ValidusWalls, "ValidusWalls");
                                    } else {
                                        showNotConnectedDialog();
                                    }
                                    break;
                                case 30:
                                    Intent SettingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                                    startActivityForResult(SettingsIntent, 0);
                                    break;
                                case 31:
                                    if (isMuzeiInstalled) {
                                        Intent launchMuzeiIntent = new Intent(MainActivity.this, Settings.class);
                                        startActivityForResult(launchMuzeiIntent, 0);
                                    } else {
                                        // Do absolutely NOTHING
                                    }
                                    break;
                                case 32:
                                    switchFragment(32, AboutApp, "Credits");
                                    break;
                            }
                        }

                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof SecondaryDrawerItem) {
                        }
                        return false;
                    }
                })
                .build();
        result.setSelection(1, true);
        boolean isMuzeiInstalled = Preferences.isAppInstalled(context, "net.nurik.roman.muzei");
        if (!isMuzeiInstalled) {
            result.removeItem(31);
        } else {
            // Do absolutely NOTHING
        }
        if (android.os.Build.VERSION.SDK_INT < 21) {
            result.removeItem(30);
        } else {
            // Do absolutely NOTHING
        }
    }

    public static int tint (int color, double factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a, Math.max( (int)(r * factor), 0 ), Math.max( (int)(g * factor), 0 ), Math.max( (int)(b * factor), 0 ) );
    }

    void switchWalls(int itemId, String title, String fragment) {
        currentItem = itemId;
        getSupportActionBar().setTitle(title);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, "com.alexcruz.papuhwalls.Walls." + fragment));
        tx.commit();
    }

    void switchFragment(int itemId, String title, String fragment) {
        currentItem = itemId;
        getSupportActionBar().setTitle(title);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, "com.alexcruz.papuhwalls." + fragment));
        tx.commit();
    }

    public void FloatingActionButton (final View view) {
        final String name = "Floating Action Button";
        final String url = "https://goo.gl/sGwRWj";
        final String copyright = "Copyright 2015 Dmytro Tarianyk";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialDialogs (final View view) {
        final String name = "Material Dialogs";
        final String url = "https://goo.gl/IGTokc";
        final String copyright = "Copyright (c) 2015 Aidan Michael Follestad";
        final License license = new MITLicense();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialDrawer (final View view) {
        final String name = "Material Drawer";
        final String url = "https://goo.gl/dD26uE";
        final String copyright = "Copyright 2015 Mike Penz";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Picasso (final View view) {
        final String name = "Picasso";
        final String url = "https://goo.gl/aKSVaH";
        final String copyright = "Copyright 2013 Square, Inc.";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Okhttp (final View view) {
        final String name = "Okhttp";
        final String url = "https://goo.gl/J6JvY3";
        final String copyright = "Copyright 2011 Square, Inc.";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Snackbar (final View view) {
        final String name = "Snackbar";
        final String url = "https://goo.gl/hg7GoU";
        final String copyright = "Copyright 2014 Jon Wimer";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void Crash (final View view) {
        final String name = "Custom Activity On Crash";
        final String url = "https://goo.gl/Ym1qXK";
        final String copyright = "Copyright (c) 2014-2015 Eduard Ereza";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void AppIntro (final View view) {
        final String name = "AppIntro";
        final String url = "https://goo.gl/5C5Np8";
        final String copyright = "Copyright 2015 Paolo Rotolo";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialRipple (final View view) {
        final String name = "Material Ripple Layout";
        final String url = "https://goo.gl/BRPpkJ";
        final String copyright = "Copyright 2015 Balys Valentukevicius";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void MaterialPreference (final View view) {
        final String name = "Material Preference";
        final String url = "https://goo.gl/ugkiRC";
        final String copyright = "Copyright (c) 2015 Jens Driller";
        final License license = new MITLicense();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    public void LicensesDialog (final View view) {
        final String name = "Licenses Dialog";
        final String url = "https://goo.gl/AJ0Prh";
        final String copyright = "Copyright 2013 Philip Schiffer";
        final License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);
        new LicensesDialog.Builder(this)
                .setNotices(notice)
                .build()
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else if (result != null && currentItem != 1) {
            result.setSelection(1);
        } else if (result != null) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.changelog:
                changelog();
                break;
            case R.id.notify:
                showNotify();
                break;
            case R.id.clearcache:
                clearApplicationData();
                break;
        }
        return true;
    }

    String Notify;

    private void showNotify() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title(R.string.notify)
                .customView(R.layout.notify, wrapInScrollView)
                .positiveText("SUBSCRIBE")
                .negativeText("No thanks")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Notify = getResources().getString(R.string.notify_url);
                        Intent notifyurl = new Intent(Intent.ACTION_VIEW, Uri.parse(Notify));
                        startActivity(notifyurl);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                    }
                })
                .iconRes(R.drawable.ic_notify)
                .show();
    }

    private void changelog() {
        new MaterialDialog.Builder(this)
                .title(R.string.changelog_dialog_title)
                .adapter(new Changelog(this, R.array.fullchangelog), null)
                .positiveText(R.string.ok)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                    }
                })
        .show();
    }

    private void showNotConnectedDialog() {
        new SnackBar.Builder(this)
                .withMessageId(R.string.no_conn_content)
                .withActionMessageId(R.string.ok)
                .withStyle(SnackBar.Style.ALERT)
                .withDuration(SnackBar.MED_SNACK)
                .show();
    }
}
