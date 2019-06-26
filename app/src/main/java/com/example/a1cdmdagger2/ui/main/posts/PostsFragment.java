package com.example.a1cdmdagger2.ui.main.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1cdmdagger2.R;
import com.example.a1cdmdagger2.models.Post;
import com.example.a1cdmdagger2.ui.main.Resource;
import com.example.a1cdmdagger2.util.VerticalSpaceItemDecoration;
import com.example.a1cdmdagger2.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

public class PostsFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;
    @Inject
    PostsRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        setupViewModel();
        setupRecycler();
        return view;
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(this, new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null && listResource.data != null && listResource.data.size() > 0) {
                    Timber.d(listResource.data.toString());
                    switch (listResource.status){
                        case ERROR:
                            Timber.d("Error retrieving posts %s", listResource.message);
                            break;
                        case LOADING:
                            Timber.d("Posts Loading");
                            break;
                        case SUCCESS:
                            adapter.setPosts(listResource.data);
                            break;
                    }
                }
            }
        });

    }

    private void setupRecycler(){
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(15));
        recyclerView.setAdapter(adapter);
    }
}
