package in.bvm.college.icwstcsc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentChat extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (AppStatus.getInstance(container.getContext().getApplicationContext()).isOnline()) {
            Intent intent = new Intent(getActivity(),Users.class);
            startActivity(intent);
        } else {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(container.getContext());
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

        return inflater.inflate(R.layout.activity_fragment_chat,null);
    }
}
