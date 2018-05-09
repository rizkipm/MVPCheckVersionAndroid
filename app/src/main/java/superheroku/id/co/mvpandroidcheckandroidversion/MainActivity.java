package superheroku.id.co.mvpandroidcheckandroidversion;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import superheroku.id.co.mvpandroidcheckandroidversion.model.Data;
import superheroku.id.co.mvpandroidcheckandroidversion.views.maincheck.MainCheckPresenter;
import superheroku.id.co.mvpandroidcheckandroidversion.views.maincheck.MainCheckView;

public class MainActivity extends AppCompatActivity implements MainCheckView {
    MainCheckPresenter presenter;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttachView();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
//        initToolbar();
        addButtonListener();
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

//    @Override
//    public void onShowFragment(final Data data) {
//        // Get Data
//        final Bundle bundle = new Bundle();
//        bundle.putString("data", data.getText());
//
//        // Show Fragment with Data
//        final String tag = ResultFragment.class.getSimpleName();
//        final Fragment fragment = ResultFragment.newInstance();
//        fragment.setArguments(bundle);
//
//        // Begin Fragment Transaction
//        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fl_main, fragment, tag);
//        fragmentTransaction.commit();
//    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    private void initPresenter() {
        presenter = new MainCheckPresenter();
    }

//    private void initToolbar() {
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
//        toolbar.setTitle(getTitle());
//        setSupportActionBar(toolbar);
//    }

    private void addButtonListener() {
        final Button button = (Button) findViewById(R.id.btnCheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                button.setVisibility(View.GONE);

                getVersionInfo();
//                presenter.showData();
            }
        });
    }

    @Override
    public void onShowData(Data data) {


    }

    @Override
    public void checkAppVer() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int currentAppVersionCode = getCurrentVersi();
        int oldAppVersion = prefs.getInt("app_version", 0);
        if (oldAppVersion < currentAppVersionCode) {
            try {
                if (oldAppVersion > 0)
                    Toast.makeText(this, String.format("App updated from version %d", oldAppVersion), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, String.format("App started for the first time", oldAppVersion), Toast.LENGTH_SHORT).show();
            } finally {
                SharedPreferences.Editor preferencesEditor = prefs.edit();
                preferencesEditor.putInt("app_version", currentAppVersionCode);
                preferencesEditor.commit();
            }
        }

    }

    @Override
    public int getCurrentVersi() {
        int versionCode = -1;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public void getVersionInfo() {
        String versionName = "";
        int versionCode = -1;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView textViewVersionInfo = (TextView) findViewById(R.id.txtHasilCheck);
        textViewVersionInfo.setText(String.format("Version name = %s \nVersion code = %d", versionName, versionCode));

        //Use the following code to get the version info from the Gradle build file.
//        versionName = BuildConfig.VERSION_NAME;
//        versionCode = BuildConfig.VERSION_CODE;
//        textViewVersionInfo.setText(textViewVersionInfo.getText() +
//                String.format("\nGradle build config version name = %s \nGradle build config version code = %d", versionName, versionCode));
    }
}
