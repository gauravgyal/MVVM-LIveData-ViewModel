package com.gauravgoyal.mvvm_with_testing.view.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gauravgoyal.mvvm_with_testing.R;
import com.gauravgoyal.mvvm_with_testing.databinding.ActivityNewsDetailBinding;

public class NewsDetailActivity extends LifecycleActivity {

    private ActivityNewsDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        binding.setUrl(getIntent().getStringExtra("url"));
    }

}
