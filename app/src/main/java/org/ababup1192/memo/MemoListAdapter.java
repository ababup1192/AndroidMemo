package org.ababup1192.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MemoListAdapter extends BaseAdapter {

    private List<Memo> memoList;
    private LayoutInflater layoutInflater;

    static class ViewHolder {
        TextView titleText;
    }


    public MemoListAdapter(Context context) {
        this.memoList = new ArrayList<Memo>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setMemoList(List<Memo> memoList) {
        this.memoList = memoList;
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoList.get(position);
    }

    public Memo getMemo(int position) {
        return (Memo) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_memo, null);
            holder = new ViewHolder();
            holder.titleText = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Memo memo = memoList.get(position);

        holder.titleText.setText(memo.getTitle() + "\n" + memo.getDate());

        return convertView;
    }
}
