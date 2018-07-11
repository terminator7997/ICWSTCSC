package in.bvm.college.icwstcsc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    List<MessageList> Messagelists;
    MessageList messageList;
    ListView listView;
    ProgressDialog pd;
    ArrayAdapter<MessageList> messageListMessageListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Messagelists = new ArrayList<>();
        messageListMessageListAdapter = new MessageListAdapter(this, R.layout.item_message_received,Messagelists);
        listView = findViewById(R.id.msg_type);
        listView.setItemsCanFocus(true);
        listView.setAdapter(messageListMessageListAdapter);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        getSupportActionBar().setTitle(UserDetails.chatWith);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://bvm-chat.firebaseio.com/messages/" + UserDetails.username + "_" + UserDetails.chatWith);
        reference2 = new Firebase("https://bvm-chat.firebaseio.com/messages/" + UserDetails.chatWith + "_" + UserDetails.username);
        pd = new ProgressDialog(Chat.this);
        pd.setMessage("Loading...");
        pd.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                pd.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 2000);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){
                    messageText.trim();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                }
                messageArea.setText("");
            }
        });

        reference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if(userName.equals(UserDetails.username)){
                    messageList = new MessageList(message, R.layout.item_message_sent);
                    Messagelists.add(messageList);
                    messageListMessageListAdapter.notifyDataSetChanged();
                   // pd.dismiss();
                }
                else{
                    messageList = new MessageList(message,R.layout.item_message_received);
                    Messagelists.add(messageList);
                    messageListMessageListAdapter.notifyDataSetChanged();
                    //pd.dismiss();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //pd.dismiss();
    }
}