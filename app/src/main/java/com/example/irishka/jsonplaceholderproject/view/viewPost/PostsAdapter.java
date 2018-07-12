package com.example.irishka.jsonplaceholderproject.view.viewPost;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.irishka.jsonplaceholderproject.R;
import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.view.viewComment.CommentsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    public static final String ID = "number_of_post";

    public static final String TITLE = "title_of_post";

    public static final String BODY = "body_of_post";

    private List<PostModel> postModelList = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public PostsAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setPostList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(PostModel postModel);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostModel post = postModelList.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_body)
        TextView body;

        View itemView;

        private PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.itemView = itemView;

        }

        void bind(PostModel post) {
            title.setText(post.getTitle());
            body.setText(post.getBody());

            itemView.setOnClickListener(view -> onItemClickListener.onItemClick(post));
        }
    }
}
