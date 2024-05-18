package com.example.task_dmrank;

import static android.content.Context.NOTIFICATION_SERVICE;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<ModelClass> modelClasses;
    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 100;

    public MyAdapter(Context context, List<ModelClass> modelClasses) {
        this.context = context;
        this.modelClasses = modelClasses;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView firstname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.titleContentRecycle);
            view = itemView.findViewById(R.id.viewContentRecycler);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_content_layout, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.firstname.setText(modelClasses.get(position).getFirstName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RetrieveDataRelatedToSpecificUser.class);
                intent.putExtra("values", modelClasses.get(holder.getAdapterPosition()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                int age = Integer.parseInt(modelClasses.get(holder.getAdapterPosition()).getAge());
                addNotification(age);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public void addNotification(int age) {

        //For Notification
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.musicconcert, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if ((age <= 16)) {
                notification = new Notification.Builder(context)
                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentText("You are eligible to get 15% Discount")
                        .setSubText("Congratulation")
                        .setChannelId(CHANNEL_ID)
                        .build();
                notificationManager.notify(NOTIFICATION_ID, notification);
            }
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(context)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("New Message")
                    .setSubText("new message from me")
                    .build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
