package com.example.cours3.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.cours3.MainActivity;
import com.example.cours3.model.MessageModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class NotificationService extends Service {

    public static final String CHANNEL_ID = "NotificationServiceChannel";
    private static final String TAG ="NotificationSerice";
    NotificationManager notificationManager;

    FirebaseFirestore database;
    int idNotification = 2;

    @Override
    public void onCreate(){
        super.onCreate();
        createChannelService();
        createChannelNotificationMessage();
        database = FirebaseFirestore.getInstance();
    }

    private void createChannelService(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelID = CHANNEL_ID;
            CharSequence channelName = "NotificationService";
            String channelDescription = "Notification service";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelID,channelName,channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void createChannelNotificationMessage(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelID = "42";
            CharSequence channelName = "NotificationMessage";
            String channelDescription = "Notification message";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelID,channelName,channelImportance);
            channel.setDescription(channelDescription);
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent,int falgs,int stratId){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,0,
                notificationIntent,0);
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Notification Service")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
        listenToMessage();

        return START_STICKY;


    }
    private void listenToMessage(){
        database.collection("Notification").whereEqualTo("read",false).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e!= null){
                    Log.d(TAG,e.toString());
                }
                for (QueryDocumentSnapshot documentMessage : queryDocumentSnapshots){
                    MessageModel message = documentMessage.toObject(MessageModel.class);
                    sendNotificationForMessage(message);
                    message.setRead(true);
                    database.collection("Notification").document(documentMessage.getId()).set(message);
                }
            }
        });
    }
    private void sendNotificationForMessage(MessageModel newMessage){
    Notification notificationToSend = NotificationCreator.createNotificationForMessage(this,newMessage);
    notificationManager.notify(idNotification,notificationToSend);
    idNotification++;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
