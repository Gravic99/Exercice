package com.example.cours3.notification;

import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.example.cours3.R;
import com.example.cours3.model.MessageModel;

public class NotificationCreator {
    public static Notification createNotificationForMessage(Context context, MessageModel messageModel){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"42")
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(messageModel.getSender())
                .setContentText(messageModel.getMessage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder.build();

    }
    //public static Notification createNotificationForService(Context contect,)
}
