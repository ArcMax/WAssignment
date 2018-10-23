package com.archana.WAssignment.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.archana.WAssignment.R;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout,container,false);
         recyclerView = (RecyclerView)view.findViewById(R.id.list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ((MainActivity)getActivity()).updateDataToViews(recyclerView);
        return view;
    }

    public void updateDataToViewsOnFragment() {
        ((MainActivity)getActivity()).updateDataToViews(recyclerView);
    }
}
