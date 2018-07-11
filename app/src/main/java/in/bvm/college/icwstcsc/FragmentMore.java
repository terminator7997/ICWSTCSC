package in.bvm.college.icwstcsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentMore extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_fragment_more,null);
        ImageButton button = view.findViewById(R.id.notesButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Notes.class));
            }
        });

        ImageButton button2 = view.findViewById(R.id.teamButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TeamActivity.class));
            }
        });
        ImageButton button3 = view.findViewById(R.id.feedbackButton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FeedbackActivity.class));
            }
        });
        ImageButton button4 = view.findViewById(R.id.expertButton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),experttalk.class));
            }
        });

        ImageButton button5 = view.findViewById(R.id.conferenceButton);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),conference.class));
            }
        });
        return view;
    }


}
