package android.csulb.edu.arttherapy;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * Created by Keval on 21-03-2017.
 */
public class ClearScreen extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ClearScreen() {
        super("");
    }
    public ClearScreen(String name) {
        super(name);
    }


    protected void onHandleIntent(Intent intent) {

        MediaPlayer mp = MediaPlayer.create(this, R.raw.eraser);
        mp.start();
        while (mp.isPlaying()) {


        }
        mp.reset();
        mp.release();
    }
}
