package cn.edu.bjtu.gymclub.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.bjtu.gymclub.R;
import cn.edu.bjtu.gymclub.activity.MainActivity;
import cn.edu.bjtu.gymclub.activity.VideoActivity;


public class FragmentOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,container,false);

        super.onCreate(savedInstanceState);
        Button buttonCall = (Button) view.findViewById(R.id.buttonCall);
        Button buttonWatch = (Button) view.findViewById(R.id.buttonWatch);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                startActivity(callIntent);
            }
        });

        buttonWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}

