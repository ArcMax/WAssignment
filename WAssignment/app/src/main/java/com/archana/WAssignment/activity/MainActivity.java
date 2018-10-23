package com.archana.WAssignment.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.archana.WAssignment.R;
import com.archana.WAssignment.adapter.StackOverFlowItemsAdapter;
import com.archana.WAssignment.model.StackOverflowItems;
import com.archana.WAssignment.model.StackOverflowResponse;
import com.archana.WAssignment.rest.ApiClient;
import com.archana.WAssignment.rest.ApiInterface;
import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ShimmerLayout shimmerLayout;
    private StackOverFlowItemsAdapter adapter;
    private ListFragment list;
    private GridFragment grid;
    private int displayMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        // get the display mode
        displayMode = getResources().getConfiguration().orientation;
        if (displayMode == 1) { // it portrait mode
            list = new ListFragment();
            fragmentTransaction.replace(android.R.id.content, list);
        } else {// its landscape
            grid = new GridFragment();
            fragmentTransaction.replace(android.R.id.content, grid);
        }
        fragmentTransaction.commit();

        shimmerLayout = (ShimmerLayout) findViewById(R.id.shimmer_view_container);
        //check for network & notify
        registerNetworkBroadcast();

    }

    public void updateDataToViews(final RecyclerView recyclerView) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StackOverflowResponse> call = apiService.getUsers();
        call.enqueue(new Callback<StackOverflowResponse>() {
            @Override
            public void onResponse(Call<StackOverflowResponse> call, Response<StackOverflowResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        List<StackOverflowItems> items = response.body().getResults();
                        adapter = new StackOverFlowItemsAdapter(getApplicationContext(), items, R.layout.list_item);
                        recyclerView.setAdapter(adapter);
                    }
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<StackOverflowResponse> call, Throwable t) {
                Log.e("MainActivity", "Error retrieving users");
            }
        });
    }

    private void registerNetworkBroadcast() {
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isOnline(context)) {
                if (displayMode == 1) {
                    list.updateDataToViewsOnFragment();
                } else {
                    grid.updateDataToViewsOnFragment();
                }
            }
        }
    };

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerLayout.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        shimmerLayout.stopShimmerAnimation();
        super.onPause();
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
