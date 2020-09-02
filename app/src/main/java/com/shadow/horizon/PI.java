package com.shadow.horizon;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.shadow.horizon.GD.Gd;

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
import java.util.ArrayList;
import java.util.Locale;

public class PI extends Activity {

    public Bot bot;
    public static Chat chat;

    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;

    private TextView text1,text4;

    TextToSpeech textToSpeech;

    TextToSpeech toSpeechPI;
    long time = System.currentTimeMillis()/1000;
    int flag=0;
    int milliseconds;

    String message,response;

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi);

        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);


        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);

        text1 = (TextView)findViewById(R.id.text1);
        text4 = (TextView)findViewById(R.id.text4);


        textToSpeech= new TextToSpeech(PI.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }

            }
        });

        toSpeechPI= new TextToSpeech(PI.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status != TextToSpeech.ERROR) {
                    toSpeechPI.setLanguage(Locale.UK);
                    toSpeechPI.setPitch(0.8f);
                    toSpeechPI.setSpeechRate(1);
                }

            }
        });


        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = mEditTextMessage.getText().toString();
                //bot
               // response = chat.multisentenceRespond(mEditTextMessage.getText().toString()); // for edit text response input

                response = chat.multisentenceRespond(String.valueOf(result));

                if (TextUtils.isEmpty(message)) {
                    return;
                }

                mEditTextMessage.setText("");

                text4.setText(message);
                // textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);

                String trimmed = message.trim();                                           //to count number  of words in a string
                int words = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;        //to count number  of words in a string
                int len= (words/2)+1;
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                delay(len);

                toSpeechPI.speak(response, TextToSpeech.QUEUE_FLUSH, null);
                //text1.setText(response);
                // text2.setText(response2);
                // text3.setText(response3);

            }
        });

        //Speech input onClick
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
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


    }


    public void delay(int seconds){
        milliseconds = seconds * 1000;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        text1.setText(response);



                    }
                }, milliseconds);
            }
        });
    }


    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Exception occured",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

}
