package eu.javageek.clementonirobot;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int DELAY_IN_PRG_MODE = 1500; // in milliseconds

    private boolean ctrlManual = true;

    private List<PrgStep> prgSteps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickManualMode(View view) {
        showToast("Robot Mio manual mode");
        playWav("2f0_init_sound");
        ctrlManual = true;
    }

    public void onClickPrgMode(View view) {
        showToast("Robot Mio program mode");
        playWav("ade_prg_init_sound");
        ctrlManual = false;
    }

    public void onClickPrgRecord(View view) {
        showToast("Clear robot program");
        prgSteps.clear();
    }

    public void onClickPrgRun(View view) {
        if (!prgSteps.isEmpty()) {
            showToast("Send program to robot...");
            playWav("2c1_prg_pkg_start");
            sleep(DELAY_IN_PRG_MODE);
            for (PrgStep step : prgSteps) {
                switch (step) {
                    case FORWARD:
                        playWav("9b1_prg_forward");
                        break;
                    case BACK:
                        playWav("a2e_prg_back");
                        break;
                    case LEFT:
                        playWav("c7a_prg_left");
                        break;
                    case RIGHT:
                        playWav("d9b_prg_right");
                        break;
                    case LIGHT:
                        playWav("2b6_light");
                        break;
                    case LIGNT_BLINK:
                        playWav("023_light_blink");
                        break;
                    case SOUND_1:
                        playWav("903_sound_1");
                        break;
                    case SOUND_2:
                        playWav("cb9_sound_2");
                        break;
                    case SOUND_3:
                        playWav("a78_sound_3");
                        break;
                    default:
                        showToast("Undefined command in program");
                }
                sleep(DELAY_IN_PRG_MODE);
            }
            playWav("c00_prg_pkg_end");
        } else
            showToast("Program is empty");
    }

    public void onClickForward(View view) {
        if (ctrlManual)
            playWav("e5f_forward");
        else
            prgSteps.add(PrgStep.FORWARD);
    }

    public void onClickBack(View view) {
        if (ctrlManual)
            playWav("b5f_back");
        else
            prgSteps.add(PrgStep.BACK);
    }

    public void onClickLeft(View view) {
        if (ctrlManual)
            playWav("7e1_left");
        else
            prgSteps.add(PrgStep.LEFT);
    }

    public void onClickRight(View view) {
        if (ctrlManual)
            playWav("b4e_right");
        else
            prgSteps.add(PrgStep.RIGHT);
    }

    public void onClickStop(View view) {
        if (ctrlManual)
            playWav("f37_stop");
        else
            showToast(prgSteps.toString());
    }

    public void onClickLight(View view) {
        if (ctrlManual)
            playWav("2b6_light");
        else
            prgSteps.add(PrgStep.LIGHT);
    }

    public void onClickLightBlink(View view) {
        if (ctrlManual)
            playWav("023_light_blink");
        else
            prgSteps.add(PrgStep.LIGNT_BLINK);
    }

    public void onClickSound1(View view) {
        if (ctrlManual)
            playWav("903_sound_1");
        else
            prgSteps.add(PrgStep.SOUND_1);
    }

    public void onClickSound2(View view) {
        if (ctrlManual)
            playWav("cb9_sound_2");
        else
            prgSteps.add(PrgStep.SOUND_2);
    }

    public void onClickSound3(View view) {
        if (ctrlManual)
            playWav("a78_sound_3");
        else
            prgSteps.add(PrgStep.SOUND_3);
    }

    public void onClickSound4(View view) {
        if (ctrlManual)
            playWav("6d1_sound_4");
    }

    public void onClickSound5(View view) {
        if (ctrlManual)
            playWav("2fb_sound_5");
    }

    private void playWav(String file) {
        try {
            AssetFileDescriptor afd = getAssets().openFd(file + ".wav");
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setLooping(false);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
