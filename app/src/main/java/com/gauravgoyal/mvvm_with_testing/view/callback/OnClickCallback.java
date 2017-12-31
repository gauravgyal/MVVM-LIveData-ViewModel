package com.gauravgoyal.mvvm_with_testing.view.callback;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.gauravgoyal.mvvm_with_testing.service.model.Article;
import com.gauravgoyal.mvvm_with_testing.service.model.News;
import com.gauravgoyal.mvvm_with_testing.view.ui.NewsDetailActivity;

public class OnClickCallback {
	public void onClick(View view, Article article) {
		Context context = view.getContext();
		Intent i = new Intent(context, NewsDetailActivity.class);
		i.putExtra("url", article.getUrl());
		 context.startActivity(i);
	}
}
