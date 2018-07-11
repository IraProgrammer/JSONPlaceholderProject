package com.example.irishka.jsonplaceholderproject.view.viewComment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.irishka.jsonplaceholderproject.R;
import com.example.irishka.jsonplaceholderproject.model.modelComment.CommentModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<CommentModel> commentModelList = new ArrayList<>();

    public void setPostList(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentModel comment = commentModelList.get(position);

        holder.bind(comment);

    }

    @Override
    public int getItemCount() {
        return commentModelList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_body)
        TextView body;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CommentModel comment) {
            title.setText(comment.getEmail());
            body.setText(comment.getBody());
        }
    }
}
