package com.shadow.horizon.GD;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.shadow.horizon.R;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import org.alicebot.ab.Timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by Amit Kumar on 30-Aug-17.
 */

public class Gd extends AppCompatActivity {

    public Bot bot;
    public static Chat chat;

    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;

    private TextView text1,text2,text3,text4;

    TextToSpeech textToSpeech;
    long time = System.currentTimeMillis()/1000;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdd);

        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);

        textToSpeech= new TextToSpeech(Gd.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }

            }
        });



        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                //bot
                String response = chat.multisentenceRespond(mEditTextMessage.getText().toString());
                String response2 = chat.multisentenceRespond(mEditTextMessage.getText().toString());
                String response3 = chat.multisentenceRespond(mEditTextMessage.getText().toString());

                if (TextUtils.isEmpty(message)) {
                    return;
                }

                mEditTextMessage.setText("");

                    text4.setText(message);
                    textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

                    if (response.equals(response2)) {
                        response = "Yes, I agree";
                    }
                    else if(response.equals(response3))
                        response3="Yes, I agree";
                    else if (response2.equals(response3))
                        response2="Yes, I agree";

                    text1.setText(response);
                    text2.setText(response2);
                    text3.setText(response3);

            }
        });
//checking SD card availablility
        boolean a = isSDCARDAvailable();
        //receiving the assets from the app directory
        AssetManager assets = getResources().getAssets();
        File jayDir = new File(Environment.getExternalStorageDirectory().toString() + "/Horizon/bots/Shadow");
        boolean b = jayDir.mkdirs();
        if (jayDir.exists()) {
            //Reading the file
            try {
                for (String dir : assets.list("Shadow")) {
                    File subdir = new File(jayDir.getPath() + "/" + dir);
                    boolean subdir_check = subdir.mkdirs();
                    for (String file : assets.list("Shadow/" + dir)) {
                        File f = new File(jayDir.getPath() + "/" + dir + "/" + file);
                        if (f.exists()) {
                            continue;
                        }
                        InputStream in = null;
                        OutputStream out = null;
                        in = assets.open("Shadow/" + dir + "/" + file);
                        out = new FileOutputStream(jayDir.getPath() + "/" + dir + "/" + file);
                        //copy file from assets to the mobile's SD card or any secondary memory
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //get the working directory
        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/Horizon";
        System.out.println("Working Directory = " + MagicStrings.root_path);
        AIMLProcessor.extension =  new PCAIMLProcessorExtension();
        //Assign the AIML files to bot for processing
        bot = new Bot("Shadow", MagicStrings.root_path, "chat");
        chat = new Chat(bot);
        String[] args = null;
        mainFunction(args);

    }

    //check SD card availability
    public static boolean isSDCARDAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)? true :false;
    }
    //copying the file
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    //Request and response of user and the bot
    public static void mainFunction (String[] args) {
        MagicBooleans.trace_mode = false;
        System.out.println("trace mode = " + MagicBooleans.trace_mode);
        Graphmaster.enableShortCuts = true;
        Timer timer = new Timer();
        String request = "Hello.";
        String response = chat.multisentenceRespond(request);

        System.out.println("Human: "+request);
        System.out.println("Robot: " + response);
        System.out.println("Robot: " + response);
        System.out.println("Robot: " + response);



    }
}
