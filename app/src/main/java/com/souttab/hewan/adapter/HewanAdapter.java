package com.souttab.hewan.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.souttab.hewan.R;
import com.souttab.hewan.entity.Hewan;
import com.souttab.hewan.util.ConvertImage;

import java.util.List;

public class HewanAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<Hewan> hewanList;

    public HewanAdapter(Context context, List<Hewan> hewanList) {
        this.hewanList = hewanList;
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return hewanList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_item_list_hewan, parent, false);
            holder = new ViewHolder();
            view.setTag(holder);

            holder.imageViewGambar = (CircularImageView) view.findViewById(R.id.imageViewGambar);
            holder.textViewNama = (TextView) view.findViewById(R.id.textViewNamaHewan);
            holder.textViewId = (TextView) view.findViewById(R.id.textViewId);
        } else holder = (ViewHolder) view.getTag();

        // ganti font
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "Days.otf");
        // masukan ke button font yang telah di ambil
        holder.textViewNama.setTypeface(font);

        // set nama hewan
        holder.textViewNama.setText(hewanList.get(position).getNama());
        holder.imageViewGambar.setImageBitmap(ConvertImage.getImage(hewanList.get(position).getGambar()));
        holder.textViewId.setText(hewanList.get(position).get_id() + "");

        return view;
    }

    class ViewHolder {
        private TextView textViewNama, textViewId;
        private CircularImageView imageViewGambar;
    }
}
