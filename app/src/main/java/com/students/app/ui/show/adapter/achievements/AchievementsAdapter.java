package com.students.app.ui.show.adapter.achievements;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.model.StreamEncoder;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.students.app.R;
import com.students.app.ui.show.ShowActivity;
import com.students.app.ui.show.adapter.common.CommonAdapter;
import com.students.app.ui.show.adapter.common.CommonItem;

import java.io.InputStream;
import java.util.List;

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ViewHolder> {

    private final List<AchievementsItem> achievements;
    private final ShowActivity showActivity;

    public AchievementsAdapter(Context context, List<AchievementsItem> achievements) {
        this.achievements = achievements;
        this.showActivity = (ShowActivity) context;
    }
    @Override
    public AchievementsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(showActivity).inflate(R.layout.item_achievements, parent, false);
        return new AchievementsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AchievementsAdapter.ViewHolder holder, int position) {
        AchievementsItem achievementsItem = achievements.get(position);
        holder.itemAchievementsText.setText(achievementsItem.getDescription());
        GlideToVectorYou
                .init()
                .with(showActivity)
                .load(Uri.parse(achievementsItem.getImage()), holder.itemAchievementsImage);
        Log.d("url", achievementsItem.getImage());

    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView itemAchievementsText;
        final ImageView itemAchievementsImage;
        ViewHolder(View view){
            super(view);
            itemAchievementsText = view.findViewById(R.id.itemAchievementsText);
            itemAchievementsImage = view.findViewById(R.id.itemAchievementsImage);
        }


    }
}
