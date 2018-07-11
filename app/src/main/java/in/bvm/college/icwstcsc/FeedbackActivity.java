package in.bvm.college.icwstcsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedbackActivity extends AppCompatActivity {

    private TextView name;
    private TextView feedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/d/e/").build();
        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);

        name = (TextView) findViewById(R.id.nameText);
        feedBack = (TextView) findViewById(R.id.feedbackText) ;
        findViewById(R.id.buttonSubmit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = name.getText().toString();
                        String pass = feedBack.getText().toString();

                        if(user.equals("")){
                            name.setError("can't be blank");
                        }
                        else if(pass.equals("")){
                            feedBack.setError("can't be blank");
                        }

                        if(Shared.getUserName(FeedbackActivity.this).equals("")){
                            Intent intent = new Intent(FeedbackActivity.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("Extra","2");
                            startActivity(intent);
                        }
                        else {
                            String pid = name.getText().toString();
                            String paperid = Shared.getUserName(FeedbackActivity.this);
                            String feedback = feedBack.getText().toString();
                            Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(paperid, pid, feedback);
                            completeQuestionnaireCall.enqueue(callCallback);
                            Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                }
        );

    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };
}
