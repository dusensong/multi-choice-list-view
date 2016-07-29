package cn.wehax.multichoicelistview;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListViewAdapter extends BaseAdapter {
    public static final String TAG = "ListViewAdapter";

    List<String> listData;
    Context ctx;

    /**
     * 保存选中项IDs
     */
    Set<Integer> selectedSet = new HashSet<>();

    public ListViewAdapter(Context ctx, List<String> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public String getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = View.inflate(ctx, R.layout.item_multi_choice_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.name.setText(getItem(position));
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isSelected(position)) {
                    Log.e(TAG, getItem(position) + "取消选中");
                    cancelSelect(position, holder);
                } else {
                    Log.e(TAG, getItem(position) + "被选中");
                    select(position, holder);
                }

                outputChooseResult(ctx);
            }
        });

        return convertView;
    }

    /**
     * 选中ID对应项
     *
     * @param position
     * @param holder
     */
    private void select(int position, ViewHolder holder) {
        selectedSet.add(position);
        holder.checkBox.setChecked(true);
    }

    /**
     * 取消选中ID对应项
     *
     * @param position
     * @param holder
     */
    private void cancelSelect(int position, ViewHolder holder) {
        selectedSet.remove(position);
        holder.checkBox.setChecked(false);
    }

    /**
     * 如果ID对应项已经被选中，返回true
     *
     * @param position
     * @return
     */
    private boolean isSelected(int position) {
        return selectedSet.contains(position);
    }

    class ViewHolder {
        RelativeLayout root;
        TextView name;
        CheckBox checkBox;

        ViewHolder(View view) {
            root = (RelativeLayout) view.findViewById(R.id.root_view);
            name = (TextView) view.findViewById(R.id.name);
            checkBox = (CheckBox) view.findViewById(R.id.select);
        }
    }

    public void outputChooseResult(Context ctx) {
        String msg;
        if (selectedSet.isEmpty()) {
            msg = "没有选中任何记录";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("已选中：");
            for(Integer item : selectedSet){
                sb.append(getItem(item) + ",");
            }
            msg= sb.toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(msg);
        builder.show();
    }
}
