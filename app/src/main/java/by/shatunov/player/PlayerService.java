package by.shatunov.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class PlayerService extends Service {

    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.audio);
        player.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int command = intent.getExtras().getInt(MainActivity.COMMAND, -1);

        switch (command) {
            case MainActivity.START:
                player.start();
                Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
                break;
            case MainActivity.STOP:
                player.stop();
                Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
                break;
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
