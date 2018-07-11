package in.bvm.college.icwstcsc;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class eventmap extends AppCompatActivity {


    ImageView iv1,iv2,iv3,iv4,iv5;
    TextView tv1,tv2,tv3;
    int height1;
    RelativeLayout linearmain;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventmap);
        getSupportActionBar().setTitle("Eventmap");
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        tv1 = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);
        iv4.setVisibility(View.GONE);
        iv5.setVisibility(View.GONE);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv1.getVisibility()==View.GONE) {
                    iv1.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                }
                else {
                    iv1.setVisibility(View.GONE);
                    iv2.setVisibility(View.GONE);
                }
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv3.getVisibility() == View.GONE) {
                    iv3.setVisibility(View.VISIBLE);
                    iv4.setVisibility(View.VISIBLE);
                } else {
                    iv3.setVisibility(View.GONE);
                    iv4.setVisibility(View.GONE);
                }
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv5.getVisibility()==View.GONE) {
                    iv5.setVisibility(View.VISIBLE);
                }
                else {
                    iv5.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(eventmap.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}