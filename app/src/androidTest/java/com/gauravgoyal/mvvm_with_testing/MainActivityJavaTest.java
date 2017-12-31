package com.gauravgoyal.mvvm_with_testing;

import android.arch.lifecycle.ViewModelProviders;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.gauravgoyal.mvvm_with_testing.view.adapter.NewsAdapter;
import com.gauravgoyal.mvvm_with_testing.view.ui.ArticleListFragment;
import com.gauravgoyal.mvvm_with_testing.view.ui.MainActivity;
import com.gauravgoyal.mvvm_with_testing.viewmodel.NewsViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.CoreMatchers;

import java.util.List;

import kotlin.jvm.JvmField;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.mockito.Mock;

/**
 * Created by gauravgoyal on 16/12/17.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityJavaTest {

    @Mock
    private NewsViewModel newsViewModel;

    @Mock
    private NewsAdapter newsAdapter;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureFrameLayoutIsPresent() throws Exception {

        MainActivity activity = rule.getActivity();
        FrameLayout viewById = activity.findViewById(R.id.fragment_container);
        assertThat(viewById, instanceOf(FrameLayout.class));

        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();
        assertEquals(fragmentList.size(), 1);
        Fragment fragment = (Fragment) fragmentList.get(0);
        assertThat(fragmentList.get(0), instanceOf(ArticleListFragment.class));
        fragment = (ArticleListFragment) fragment;

        // fragment testing
        View view = fragment.getView();
        View recyclerView = view.findViewById(R.id.project_list);
        assertThat(recyclerView, instanceOf(RecyclerView.class));
        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        recyclerView = (RecyclerView) recyclerView;

        newsViewModel = ViewModelProviders.of(activity, new NewsViewModel.Factory(activity.getApplication()))
                .get(NewsViewModel.class);

        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        newsAdapter = (NewsAdapter) ((RecyclerView) recyclerView).getAdapter();

        int count = newsAdapter.getItemCount();
        assertThat(count, greaterThan(0));

    }
}
