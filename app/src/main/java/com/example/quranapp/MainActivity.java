package com.example.quranapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SurahAdapter surahAdapter;
    private List<Surah> surahList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        surahList = new ArrayList<>();
        // Add your Surah objects to the surahList here
        surahList.add(new Surah("Al-Fatiha"));
        surahList.add(new Surah("Al-Baqarah"));
        surahList.add(new Surah("Aali Imran"));
        surahList.add(new Surah("An-Nisa"));
        surahList.add(new Surah("Al-Ma'idah"));
        surahList.add(new Surah("Al-An'am"));
        // Add more Surah objects as needed

        surahAdapter = new SurahAdapter(surahList);
        recyclerView.setAdapter(surahAdapter);
        surahAdapter.setOnItemClickListener(new SurahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Surah clickedSurah = surahList.get(position);
                Toast.makeText(MainActivity.this, clickedSurah.getTitle(), Toast.LENGTH_SHORT).show();
                // Handle the click event for the selected Surah
                Intent intent = new Intent(MainActivity.this, SurahDetailActivity.class);
                intent.putExtra("surahTitle", clickedSurah.getTitle());
                intent.putExtra("surahContent", getSurahContent(clickedSurah.getTitle()));
                startActivity(intent);
            }
        });
    }

    private String getSurahContent(String surahTitle) {
        // Logic to retrieve the content of the selected Surah based on the title
        // Replace this with your actual implementation to retrieve the content
        String surahContent = "";

        if (surahTitle.equals("Al-Fatiha")) {
            surahContent = "This is the content of Surah Al-Fatiha";
        } else if (surahTitle.equals("Al-Baqarah")) {
            surahContent = "This is the content of Surah Al-Baqarah";
        } else if (surahTitle.equals("Aali Imran")) {
            surahContent = "This is the content of Surah Aali Imran";
        }
        // Add more conditions to retrieve the content for other Surahs

        return surahContent;
    }

    private static class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

        private List<Surah> surahList;
        private OnItemClickListener listener;

        public SurahAdapter(List<Surah> surahList) {
            this.surahList = surahList;
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_surah, parent, false);
            return new SurahViewHolder(itemView, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
            Surah currentSurah = surahList.get(position);
            holder.textViewTitle.setText(currentSurah.getTitle());
        }

        @Override
        public int getItemCount() {
            return surahList.size();
        }

        public static class SurahViewHolder extends RecyclerView.ViewHolder {

            public TextView textViewTitle;

            public SurahViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.text_view_title);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
            }
        }
    }
}
