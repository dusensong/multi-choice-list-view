package cn.wehax.multichoicelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActionBarActivity {

    @InjectView(R.id.list)
    public ListView list;

    @InjectView(R.id.select_all)
    CheckBox cbSelectAll;

    private List<String> listData = new ArrayList<>();
    public ListViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateDummyData(20);
        adapter = new ListViewAdapter(this, listData);
        list.setAdapter(adapter);

        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                adapter.chooseAll(isChecked);
            }
        });
    }

    private void generateDummyData(int itemCount) {
        if (itemCount < 0)
            return;

        for (int i = 0; i < itemCount; i++) {
            listData.add("第" + i + "项");
        }
    }
}
