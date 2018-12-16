package io.plainapp.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.plainapp.R;
import io.plainapp.core.data.DataManager;
import io.plainapp.core.ui.FeedAdapter;
import io.plainapp.core.ui.FilterAdapter;

import static io.plainapp.dagger.Injector.inject;

/**
 * Created by ronit on 25/10/17.
 */

public class HomeActivity extends Activity{

    private static final int RC_SEARCH = 0;
    private static final int RC_NEW_DESIGNER_NEWS_STORY = 4;
    private static final int RC_NEW_DESIGNER_NEWS_LOGIN = 5;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private RecyclerView grid;
    private ImageButton fab;
    private RecyclerView filtersList;
    private ProgressBar loading;
    private @Nullable
    ImageView noConnection;
    ImageButton fabPosting;
    GridLayoutManager layoutManager;
    private int columns;
    boolean connected = true;
    private TextView noFiltersEmptyText;
    private boolean monitoringConnectivity = false;

    // data
    @Inject
    DataManager dataManager;
    @Inject
    FeedAdapter adapter;
    @Inject
    FilterAdapter filtersAdapter;
//    @Inject
//    LoginRepository loginRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindResources();
        inject(this, data -> {
//            adapter.addAndResort(data);
//            checkEmptyState();
        });

        drawer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        setActionBar(toolbar);
        if (savedInstanceState == null) {
//            animateToolbar();
        }

    }


    private void bindResources() {
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        grid = findViewById(R.id.grid);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> { fabClick(); });
        filtersList = findViewById(R.id.filters);
        loading = findViewById(android.R.id.empty);
        noConnection = findViewById(R.id.no_connection);

        columns = getResources().getInteger(R.integer.num_columns);
    }

    protected void fabClick() {
        /*if (loginRepository.isLoggedIn()) {
            Intent intent = ActivityHelper.intentTo(Activities.DesignerNews.PostStory.INSTANCE);
            FabTransform.addExtras(intent,
                    ContextCompat.getColor(this, R.color.accent), R.drawable.ic_add_dark);
            intent.putExtra(PostStoryService.EXTRA_BROADCAST_RESULT, true);
            registerPostStoryResultListener();
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab,
                    getString(R.string.transition_new_designer_news_post));
            startActivityForResult(intent, RC_NEW_DESIGNER_NEWS_STORY, options.toBundle());
        } else {
            Intent intent = ActivityHelper.intentTo(Activities.DesignerNews.Login.INSTANCE);
            FabTransform.addExtras(intent,
                    ContextCompat.getColor(this, R.color.accent), R.drawable.ic_add_dark);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, fab,
                    getString(R.string.transition_designer_news_login));
            startActivityForResult(intent, RC_NEW_DESIGNER_NEWS_LOGIN, options.toBundle());
        }*/
    }

}
