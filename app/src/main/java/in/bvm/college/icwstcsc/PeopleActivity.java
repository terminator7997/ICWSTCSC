package in.bvm.college.icwstcsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class PeopleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<DataModel2> data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        recyclerView = (RecyclerView) findViewById(R.id.peopleRecycle2);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<DataModel2>();
        for (int i = 0; i < MyData2.nameArray.length; i++) {
            data.add(new DataModel2(
                    MyData2.nameArray[i],
                    MyData2.descriptionArray[i],
                    MyData2.id_[i],
                    MyData2.drawableArray[i]
            ));
        }
        adapter = new CustomAdapter2(data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(PeopleActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
