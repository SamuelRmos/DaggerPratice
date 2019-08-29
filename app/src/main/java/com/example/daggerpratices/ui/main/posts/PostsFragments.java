package com.example.daggerpratices.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.daggerpratices.R;
import com.example.daggerpratices.models.Post;
import com.example.daggerpratices.ui.main.Resource;
import com.example.daggerpratices.utils.VerticalSpacingItemDecoration;
import com.example.daggerpratices.viewmodels.ViewModelProviderFactory;

import java.util.List;

import dagger.android.support.DaggerFragment;

import javax.inject.Inject;

public class PostsFragments extends DaggerFragment {

    private static final String TAG = "PostsFragments";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;


    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);
        initRecyclerView();
        subscribeObserves();
    }

    private void subscribeObserves(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null){
                    switch (listResource.status){
                        case LOADING:{
                            Log.d(TAG, "onChanged: LOADING...");
                        
                        }
                        case SUCCESS:{
                            Log.d(TAG, "onChanged: got posts...");
                            adapter.setPosts(listResource.data);
                            break;
                        }
                        case ERROR:{
                            Log.e(TAG, "onChanged: ERROR..." + listResource.message );
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
