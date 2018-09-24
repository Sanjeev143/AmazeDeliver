package com.sanjeev.er.amazedeliver.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sanjeev.er.amazedeliver.R;
import com.sanjeev.er.amazedeliver.adapter.ListAdapter;
import com.sanjeev.er.amazedeliver.api_interface.API;
import com.sanjeev.er.amazedeliver.model.Constant;
import com.sanjeev.er.amazedeliver.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

/* Author Sanjeev Sangral*/
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout errorLayout;
    private Button retryButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private List<Model> jsonResponse;

    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        getDeliveryList();
        return view;
    }

    private void init() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        errorLayout = (LinearLayout)view.findViewById(R.id.error_layout);
        retryButton  = (Button)view.findViewById(R.id.retry_btn);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                if (Constant.isInternetOn(getContext())) {
                    getDeliveryList();
                }{
                    errorLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setVisibility(View.GONE);
                }
            }
        });
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setRefreshing(true);
                if (Constant.isInternetOn(getContext())) {
                    getDeliveryList();
                }{
                    errorLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void getDeliveryList(){
        API.getClient(getContext()).getDeliveriesList().enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    jsonResponse = response.body();
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    errorLayout.setVisibility(View.GONE);
                    Log.d("Size ", ""+jsonResponse.size());
                    if (jsonResponse.size()>0) {
                        ListAdapter adapter = new ListAdapter(jsonResponse,getContext());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Network error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                swipeRefreshLayout.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Fail to retrieve the list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
