package io.plainapp.core.ui;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.ViewPreloadSizeProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import io.plainapp.core.R;
import io.plainapp.core.data.DataLoadingSubject;
import io.plainapp.core.data.PlaidItem;
import io.plainapp.core.data.PlaidItemSorting;
import io.plainapp.core.dribbble.data.api.ShotWeigher;
import io.plainapp.core.dribbble.data.api.model.Shot;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements DataLoadingSubject.DataLoadingCallbacks,
        ListPreloader.PreloadModelProvider<Shot> {

    public static final int REQUEST_CODE_VIEW_SHOT = 5407;

    private static final int TYPE_DESIGNER_NEWS_STORY = 0;
    private static final int TYPE_DRIBBBLE_SHOT = 1;
    private static final int TYPE_PRODUCT_HUNT_POST = 2;
    private static final int TYPE_LOADING_MORE = -1;

    // we need to hold on to an activity ref for the shared element transitions :/
    final Activity host;
    private final LayoutInflater layoutInflater;
    private final PlaidItemSorting.PlaidItemComparator comparator;
    private final boolean pocketIsInstalled;
    private final @Nullable
    DataLoadingSubject dataLoading;
    private final int columns;
    private final ColorDrawable[] shotLoadingPlaceholders;
    private final ViewPreloadSizeProvider<Shot> shotPreloadSizeProvider;

    private final @ColorInt
    int initialGifBadgeColor;
    private List<PlaidItem> items;
    private boolean showLoadingMore = false;
    private PlaidItemSorting.NaturalOrderWeigher naturalOrderWeigher;
    private ShotWeigher shotWeigher;
//    private StoryWeigher storyWeigher;
//    private PostWeigher postWeigher;

    @Inject
    public FeedAdapter(Activity hostActivity,
                       @Nullable DataLoadingSubject dataLoading,
                       int columns,
                       boolean pocketInstalled, ViewPreloadSizeProvider<Shot> shotPreloadSizeProvider) {
        this.host = hostActivity;
        this.dataLoading = dataLoading;
        if (dataLoading != null) {
            dataLoading.registerCallback(this);
        }
        this.columns = columns;
        this.pocketIsInstalled = pocketInstalled;
        this.shotPreloadSizeProvider = shotPreloadSizeProvider;
        layoutInflater = LayoutInflater.from(host);
        comparator = new PlaidItemSorting.PlaidItemComparator();
        items = new ArrayList<>();
        setHasStableIds(true);

        // get the dribbble shot placeholder colors & badge color from the theme
        final TypedArray a = host.obtainStyledAttributes(R.styleable.DribbbleFeed);
        final int loadingColorArrayId =
                a.getResourceId(R.styleable.DribbbleFeed_shotLoadingPlaceholderColors, 0);
        if (loadingColorArrayId != 0) {
            int[] placeholderColors = host.getResources().getIntArray(loadingColorArrayId);
            shotLoadingPlaceholders = new ColorDrawable[placeholderColors.length];
            for (int i = 0; i < placeholderColors.length; i++) {
                shotLoadingPlaceholders[i] = new ColorDrawable(placeholderColors[i]);
            }
        } else {
            shotLoadingPlaceholders = new ColorDrawable[]{new ColorDrawable(Color.DKGRAY)};
        }
        final int initialGifBadgeColorId =
                a.getResourceId(R.styleable.DribbbleFeed_initialBadgeColor, 0);
        initialGifBadgeColor = initialGifBadgeColorId != 0 ?
                ContextCompat.getColor(host, initialGifBadgeColorId) : 0x40ffffff;
        a.recycle();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public List<Shot> getPreloadItems(int position) {
        return null;
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Shot item) {
        return null;
    }

    @Override
    public void dataStartedLoading() {

    }

    @Override
    public void dataFinishedLoading() {

    }
}
