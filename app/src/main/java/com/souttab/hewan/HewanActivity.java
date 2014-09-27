package com.souttab.hewan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.souttab.hewan.adapter.HewanAdapter;
import com.souttab.hewan.util.KlikSuara;
import com.souttab.hewan.util.MyDatabase;

public class HewanActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hewan);

        MyDatabase database = new MyDatabase(this);
        final KlikSuara suara = new KlikSuara(this);

        // reference variable
        StaggeredGridView gridView = (StaggeredGridView) findViewById(R.id.gridViewHewan);
        // masukkan data dengan menggunakan adapter dibuat
        HewanAdapter hewanAdapter = new HewanAdapter(this, database.listHewan());
        // set adapter
        gridView.setAdapter(hewanAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long post) {
                // ambil Id dari item yang di clik
                TextView getId = (TextView) view.findViewById(R.id.textViewId);
                int id = Integer.parseInt(getId.getText().toString());

                suara.klik();
                // panggil layout detail dan
                // lempar id ke layout berikutnya
                Intent intent = new Intent(HewanActivity.this, HewanDetailActivity.class);
                // masukkan intent
                // untuk kirim id ke activity selanjutnya
                intent.putExtra("id", id);
                // start activitynya
                startActivity(intent);
            }
        });
    }
}
