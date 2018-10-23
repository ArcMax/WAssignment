package com.archana.WAssignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.archana.WAssignment.R;
import com.archana.WAssignment.model.BadgeCount;
import com.archana.WAssignment.model.StackOverflowItems;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StackOverFlowItemsAdapter extends RecyclerView.Adapter<StackOverFlowItemsAdapter.StackOverFlowItemsViewHolder> {
    private Context context;
    private int rowLayout;
    private List<StackOverflowItems> items;


    public StackOverFlowItemsAdapter(Context context, List<StackOverflowItems> items, int rowLayout){
        this.context = context;
        this.items = items;
        this.rowLayout = rowLayout;
    }

    @Override
    public StackOverFlowItemsAdapter.StackOverFlowItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new StackOverFlowItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StackOverFlowItemsAdapter.StackOverFlowItemsViewHolder holder, int position) {
        String imageUrl = items.get(position).getProfile_image();
        String titleText = items.get(position).getDisplay_name();
        BadgeCount count = items.get(position).getBadge_counts();
        int gold_badge = count.getGold();
        int silver_badge = count.getSilver();
        int bronze_badge = count.getBronze();

        Picasso.with(context).load(imageUrl).into(holder.imageView);
        holder.textView.setText(titleText);
        holder.goldTextView.setText(Integer.toString(gold_badge));
        holder.silverTextView.setText(Integer.toString(silver_badge));
        holder.bronzeTextView.setText(Integer.toString(bronze_badge));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class StackOverFlowItemsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView goldTextView;
        private TextView silverTextView;
        private TextView bronzeTextView;
        public StackOverFlowItemsViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.coverImageView);
            textView = (TextView)itemView.findViewById(R.id.titleTextView);
            goldTextView = (TextView)itemView.findViewById(R.id.gold);
            silverTextView = (TextView)itemView.findViewById(R.id.silver);
            bronzeTextView = (TextView)itemView.findViewById(R.id.bronze);
        }
    }
}
