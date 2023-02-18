package ru.arvalon.chucknorrisjokes.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;

/**
 * Created by arvalon on 04.11.2016.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeHolder> {

    private JokeList jokeList;

    public JokesAdapter(JokeList jokeList) {
        this.jokeList = jokeList;
    }

    @Override
    public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.joke_row,parent,false);
        return new JokeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JokeHolder holder, int position) {
        Joke joke=jokeList.getValue().get(position);
        holder.id.setText(String.valueOf(joke.getId()));
        holder.jokeText.setText(joke.getJoke());
    }

    @Override
    public int getItemCount() {
        return jokeList.getValue().size();
    }

    public class JokeHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.jokeId)TextView id;
        @BindView(R.id.jokeText)TextView jokeText;

        public JokeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
