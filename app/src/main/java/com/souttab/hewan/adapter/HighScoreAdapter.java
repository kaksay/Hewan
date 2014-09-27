package com.souttab.hewan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.souttab.hewan.R;
import com.souttab.hewan.entity.Score;

import java.util.List;

public class HighScoreAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Score> scoreList;

    public HighScoreAdapter(Context mContext, List<Score> scores) {
        this.context = mContext;
        this.scoreList = scores;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_item_highscore, parent, false);
            view.setTag(holder);

            // reference variable
            holder.textViewNama = (TextView) view.findViewById(R.id.textViewNamaScoreHighScore);
            holder.textViewScore = (TextView) view.findViewById(R.id.textViewScoreHighScore);
        } else holder = (ViewHolder) view.getTag();

        // setdata
        holder.textViewScore.setText(scoreList.get(position).getScore() + "");
        holder.textViewNama.setText(scoreList.get(position).getNama());

        return view;
    }

    class ViewHolder {
        TextView textViewNama, textViewScore;
    }
}
