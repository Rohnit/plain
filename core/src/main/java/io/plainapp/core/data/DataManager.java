package io.plainapp.core.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.plainapp.core.designernews.domain.LoadStoriesUseCase;
import io.plainapp.core.designernews.domain.SearchStoriesUseCase;
import io.plainapp.core.dribbble.data.ShotsRepository;
import io.plainapp.core.producthunt.data.api.ProductHuntInjection;
import io.plainapp.core.producthunt.data.api.ProductHuntRepository;
import io.plainapp.core.ui.FilterAdapter;
import retrofit2.Call;

/**
 * Responsible for loading data from the various sources. Instantiating classes are responsible for
 * providing the {code onDataLoaded} method to do something with the data.
 */
public class DataManager extends BaseDataManager<List<? extends PlaidItem>> implements LoadSourceCallback {

    private final ShotsRepository shotsRepository;
    private final FilterAdapter filterAdapter;
    private final LoadStoriesUseCase loadStoriesUseCase;
    private final SearchStoriesUseCase searchStoriesUseCase;
    private final ProductHuntRepository productHuntRepository;
    Map<String, Integer> pageIndexes;
    Map<String, Call> inflightCalls;

    public DataManager(OnDataLoadedCallback<List<? extends PlaidItem>> onDataLoadedCallback,
                       LoadStoriesUseCase loadStoriesUseCase,
                       SearchStoriesUseCase searchStoriesUseCase,
                       ShotsRepository shotsRepository,
                       FilterAdapter filterAdapter) {
        super();
        this.loadStoriesUseCase = loadStoriesUseCase;
        this.searchStoriesUseCase = searchStoriesUseCase;
        productHuntRepository = ProductHuntInjection.provideProductHuntRepository();
        this.shotsRepository = shotsRepository;
        this.filterAdapter = filterAdapter;
        setOnDataLoadedCallback(onDataLoadedCallback);

        filterAdapter.registerFilterChangedCallback(filterListener);
        setupPageIndexes();
        inflightCalls = new HashMap<>();
    }


    private void setupPageIndexes() {
        final List<Source> dateSources = filterAdapter.getFilters();
        pageIndexes = new HashMap<>(dateSources.size());
        for (Source source : dateSources) {
            pageIndexes.put(source.key, 0);
        }
    }


    @Override
    public void cancelLoading() {
        if (inflightCalls.size() > 0) {
            for (Call call : inflightCalls.values()) {
                call.cancel();
            }
            inflightCalls.clear();
        }
        shotsRepository.cancelAllSearches();
        loadStoriesUseCase.cancelAllRequests();
        searchStoriesUseCase.cancelAllRequests();
        productHuntRepository.cancelAllRequests();
    }

    @Override
    public void sourceLoaded(@Nullable List<? extends PlaidItem> result, int page, @NotNull String source) {

    }

    @Override
    public void loadFailed(@NotNull String source) {

    }


    private final FilterAdapter.FiltersChangedCallbacks filterListener =
            new FilterAdapter.FiltersChangedCallbacks() {

                @Override
                public void onFiltersChanged(@NotNull Source changedFilter) {
                    super.onFiltersChanged(changedFilter);
                }
            };

}
