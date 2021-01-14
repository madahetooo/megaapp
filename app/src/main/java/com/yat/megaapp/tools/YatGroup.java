package com.yat.megaapp.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yat.megaapp.R;
import com.yat.megaapp.adapter.MyListViewAdapter;

public class YatGroup extends Fragment {

    ListView lvYatGroup;
    String yatGroupNames[] = {"Ali", "Yehia", "Afnan", "Mahmoud", "Ahmed Abd el Nasser",
            "Ali", "Yehia", "Afnan", "Mahmoud", "Ahmed Abd el Nasser"};
    String yatGroupPosition[] = {"Android Developer", "Ios Developer", "PHP Developer", "Web Developer", "Designer",
           " Android Developer", "Ios Developer", "PHP Developer", "Web Developer", "Designer",};
    Integer[] imgid={
            R.drawable.dog_or_cat,R.drawable.megalogo,
            R.drawable.temp_converter,R.drawable.dog_or_cat,
            R.drawable.megalogo,
            R.drawable.dog_or_cat,R.drawable.megalogo,
            R.drawable.temp_converter,R.drawable.dog_or_cat,R.drawable.dog_or_cat,
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_yat_group, container, false);
        lvYatGroup = v.findViewById(R.id.lvYatGroup);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, yatGroupNames);
//
//        lvYatGroup.setAdapter(adapter);

        MyListViewAdapter adapter=new MyListViewAdapter(getActivity(), yatGroupNames, yatGroupPosition,imgid);
        lvYatGroup.setAdapter(adapter);
        lvYatGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = String.valueOf(adapter.getItem(position));
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }


}