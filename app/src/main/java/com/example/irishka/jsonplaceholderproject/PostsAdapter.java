package com.example.irishka.jsonplaceholderproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irishka.jsonplaceholderproject.model.modelPost.PostModel;
import com.example.irishka.jsonplaceholderproject.view.viewComment.CommentsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    public static final String ID = "number_of_post";

    public static final String TITLE = "title_of_post";

    public static final String BODY = "body_of_post";

    private List<PostModel> postModelList = new ArrayList<>();

    private View v;

    private Context context;

    public PostsAdapter(Context context) {
        this.context = context;
    }

    public void setPostList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
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

    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_body)
        TextView body;

        PostModel postModel;

        private PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(PostModel post) {
            postModel = post;
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(context, String.valueOf(postModel.getId()), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, CommentsActivity.class);
            intent.putExtra(ID, postModel.getId());
            intent.putExtra(TITLE, postModel.getTitle());
            intent.putExtra(BODY, postModel.getBody());
            context.startActivity(intent);
        }
    }
}
