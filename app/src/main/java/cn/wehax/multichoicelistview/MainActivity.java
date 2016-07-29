package cn.wehax.multichoicelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActionBarActivity {

    @InjectView(R.id.list)
    public ListView list;

    @InjectView(R.id.select_all)
    Button selectAllBtn;

    private List<String> listData = new ArrayList<>();
    public ListViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genarateDummyData(20);
        adapter = new ListViewAdapter(this, listData);
        list.setAdapter(adapter);

        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 实现全选
            }
        });
    }

    private void genarateDummyData(int itemCount) {
        if (itemCount < 0)
            return;

        for (int i = 0; i < itemCount; i++) {
            listData.add("第" + i + "项");
        }
    }
}
