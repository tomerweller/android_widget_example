package com.example.widget_example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends Activity
{
    private static final String TAG = "WidgetExample:MyActivity";

    private EditText messageText;
    private Button sendMessageButton;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        this.context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        messageText = (EditText) findViewById(R.id.messageEdit);
        sendMessageButton = (Button) findViewById(R.id.messageSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Broadcasting Message Update:" + getCurrentMessage());
                Intent intent = new Intent(Consts.UPDATE_WITH_MESSAGE_ACTION);
                intent.putExtra(Consts.MESSAGE_EXTRA, getCurrentMessage());
                context.sendBroadcast(intent);
            }
        });
    }

    private String getCurrentMessage(){
        return messageText.getText()+"";
    }

}
