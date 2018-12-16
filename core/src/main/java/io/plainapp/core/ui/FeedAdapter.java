package io.plainapp.core.ui;

import android.app.Activity;
import android.view.ViewGroup;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.util.ViewPreloadSizeProvider;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import io.plainapp.core.data.DataLoadingSubject;
import io.plainapp.core.dribbble.data.api.model.Shot;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements DataLoadingSubject.DataLoadingCallbacks,
        ListPreloader.PreloadModelProvider<Shot> {

    @Inject
    public FeedAdapter(Activity hostActivity,
                       @Nullable DataLoadingSubject dataLoading,
                       int columns,
                       boolean pocketInstalled, ViewPreloadSizeProvider<Shot> shotPreloadSizeProvider) {

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
