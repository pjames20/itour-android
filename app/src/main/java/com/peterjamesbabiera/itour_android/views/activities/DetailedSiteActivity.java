package com.peterjamesbabiera.itour_android.views.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.peterjamesbabiera.itour_android.R;
import com.peterjamesbabiera.itour_android.data.Attraction;
import com.peterjamesbabiera.itour_android.views.fragments.site.SiteFragment;

/**
 * Created by peter on 2/28/18.
 */

public class DetailedSiteActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView mInfoImage;
    private TextView mInfo;

    private Attraction site;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_site);

        site = getIntent().getParcelableExtra(SiteFragment.SITE);

        mInfoImage = findViewById(R.id.viewImage);
        mInfo = findViewById(R.id.tv_info);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() == null) {
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(site.getName());
        }

        setDetails();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setDetails() {
        if (site == null) {
        } else {
            mInfoImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), site.getInfoImageId()));
            mInfo.setText(site.getInfo());
        }
    }
}
