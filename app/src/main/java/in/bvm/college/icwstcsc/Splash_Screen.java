package in.bvm.college.icwstcsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash_Screen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}