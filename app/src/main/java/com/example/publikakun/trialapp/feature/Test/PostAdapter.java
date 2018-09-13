package com.example.publikakun.trialapp.feature.Test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.publikakun.trialapp.R;
import com.example.publikakun.trialapp.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.posts_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof PostAdapter.ViewHolder)
        {
            final Post post = postList.get(position);
            holder.tvTitle.setText(String.valueOf(post.getTitle()));
            holder.tvAuthor.setText(String.valueOf(post.getUserId()));
            holder.tvContent.setText(new StringBuilder(post.getBody().substring(0,20)).append(".......").toString());
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle,tvContent,tvAuthor;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txt_title);
            tvContent = itemView.findViewById(R.id.txt_content);
            tvAuthor = itemView.findViewById(R.id.txt_author);
        }
    }
}
