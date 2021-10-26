package eu.javageek.clementonirobot;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickInit(View view) {
        Toast toast = Toast.makeText(this, "Robot Mio init", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
        playWav("2f0_init_sound.wav");
    }

    public void onClickForward(View view) {
        playWav("e5f_forward.wav");
    }

    public void onClickBack(View view) {
        playWav("b5f_back.wav");
    }

    public void onClickLeft(View view) {
        playWav("7e1_left.wav");
    }

    public void onClickRight(View view) {
        playWav("b4e_right.wav");
    }

    public void onClickStop(View view) {
        playWav("f37_stop.wav");
    }

    public void onClickLight(View view) {
        playWav("2b6_light.wav");
    }

    public void onClickLightBlink(View view) {
        playWav("023_light_blink.wav");
    }

    public void onClickSound1(View view) {
        playWav("903_sound_1.wav");
    }

    public void onClickSound2(View view) {
        playWav("cb9_sound_2.wav");
    }

    public void onClickSound3(View view) {
        playWav("a78_sound_3.wav");
    }

    public void onClickSound4(View view) {
        playWav("6d1_sound_4.wav");
    }

    public void onClickSound5(View view) {
        playWav("2fb_sound_5.wav");
    }

    private void playWav(String file) {
        try {
            AssetFileDescriptor afd = getAssets().openFd(file);
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setLooping(false);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
