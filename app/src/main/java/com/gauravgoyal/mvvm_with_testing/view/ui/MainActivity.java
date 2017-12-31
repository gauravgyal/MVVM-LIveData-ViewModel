package com.gauravgoyal.mvvm_with_testing.view.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gauravgoyal.mvvm_with_testing.R;
import com.gauravgoyal.mvvm_with_testing.lifecycle.SomeObserver;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName().toString();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            ArticleListFragment fragment = new ArticleListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ArticleListFragment.TAG).commit();
        }

        // for shake of
        getLifecycle().addObserver(new SomeObserver());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }
}
