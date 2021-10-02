package com.students.app.ui.show.adapter.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.students.app.R;

import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<CommonItem> skills;

    public CommonAdapter(Context context, List<CommonItem> skills) {
        this.skills = skills;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_skills, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommonItem commonItem = skills.get(position);
        holder.itemSkillsText.setText(commonItem.getText());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView itemSkillsText;
        ViewHolder(View view){
            super(view);
            itemSkillsText = view.findViewById(R.id.itemSkillsText);
        }
    }
}