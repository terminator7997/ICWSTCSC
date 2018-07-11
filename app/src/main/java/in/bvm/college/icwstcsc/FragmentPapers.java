package in.bvm.college.icwstcsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentPapers extends android.support.v4.app.Fragment {

    ImageView iv1,iv2,iv3,iv4,iv5;
    TextView tv1,tv2,tv3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_fragment_papers,null);
        Intent intent = new Intent(getActivity(),eventmap.class);
        startActivity(intent);
        return view;
    }
}
