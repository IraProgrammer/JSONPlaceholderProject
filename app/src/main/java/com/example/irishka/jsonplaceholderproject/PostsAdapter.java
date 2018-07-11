package com.example.irishka.jsonplaceholderproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//TODO ты можешь в дженерик передавать сразу свой ViewHolder
// и лучше назвать как-нибудь по другому, например - PostViewHolder
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<PostModel> postModelList = new ArrayList<>();

    public void setPostList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    public void addItem(PostModel post){
        postModelList.add(post);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostModel post = postModelList.get(position);

        //TODO лучше создавать метод внутри holder и там производить все манипуляции
        // типа void bind(PostModel post) {
        //          title.setText(post.getTitle());
        //          body.setText(post.getBody());
        //      }
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        //TODO проверка на null нужна, если у тебя lazy инициализация postModelList, но список создается пустым при инициализации класса
        // и если после if идет одна строка, то лучше записывать в 1 строку все
        if (postModelList == null)
            return 0;
        return postModelList.size();
    }

    //TODO нужен private модификатор, что бы к нему нельзя было обратиться из-вне и устроить утечку
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView title;

        @BindView(R.id.tv_body)
        TextView body;

        //TODO конструктор тоже private
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
