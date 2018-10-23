package com.archana.WAssignment.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.archana.WAssignment.R;

public class GridFragment extends Fragment {

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_fragment_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.gird_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        ((MainActivity) getActivity()).updateDataToViews(recyclerView);
        return view;
    }

    public void updateDataToViewsOnFragment() {
        ((MainActivity) getActivity()).updateDataToViews(recyclerView);
    }
}
