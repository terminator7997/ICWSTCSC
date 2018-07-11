package in.bvm.college.icwstcsc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Users extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
            usersList = (ListView) findViewById(R.id.usersList);
            noUsersText = (TextView) findViewById(R.id.noUsersText);
            if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
            } else {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Please connect to Internet !!!");
                dialog.setPositiveButton(this.getResources().getString(R.string.setting), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                        startActivity(intent);
                        // finish();
                    }
                });
                dialog.show();
            }


            if (Shared.getUserName(Users.this).length() == 0) {
                Intent intent = new Intent(Users.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Extra","1");
                startActivity(intent);
                // call Login Activity
            } else {
                // Stay at the current activity.
                UserDetails.username = Shared.getUserName(Users.this);
                UserDetails.emailid = Shared.getEmailid(Users.this);
            }
            String url = "https://bvm-chat.firebaseio.com/users.json";

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    doOnSuccess(s);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    //Toast.makeText(Users.this,volleyError.getMessage(),Toast.LENGTH_LONG).show();

                    //     pd.dismiss();
                }
            });

            RequestQueue rQueue = Volley.newRequestQueue(Users.this);
            rQueue.add(request);

            usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    UserDetails.chatWith = al.get(position);
                    startActivity(new Intent(Users.this, Chat.class));
                }
            });
        }catch (Exception e){
            Toast.makeText(Users.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void doOnSuccess(String s){
        try {

            //pd = new ProgressDialog(Users.this);
            //pd.setMessage("Loading...");
            //pd.show();
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";
            while(i.hasNext()){
                key = i.next().toString();
                if(!key.equals(UserDetails.username)) {
                    //al.add(obj.getJSONObject(key).getString("name"));
                    al.add(key);
                }
                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }
        //pd.dismiss();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sign_out_menu : {
                Shared.setUserName(Users.this, "");
                Intent intent = new Intent(Users.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Extra","1");
                startActivity(intent);
                return true;
            }
            default:
                return true;
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Users.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}