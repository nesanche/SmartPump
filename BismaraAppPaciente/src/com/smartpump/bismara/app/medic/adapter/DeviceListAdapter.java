package com.smartpump.bismara.app.medic.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartpump.bismara.app.medic.R;

@SuppressLint("InflateParams")
public class DeviceListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<BluetoothDevice> mData;

    public DeviceListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<BluetoothDevice> data) {
        mData = data;
    }

    public int getCount() {
        return (mData == null) ? 0 : mData.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_device, null);
            holder = new ViewHolder();
            holder.nameTv = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BluetoothDevice device = mData.get(position);

        holder.nameTv.setText(device.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView nameTv;
    }

    private void setListener(OnItemClickListener listener) {
//        this.s
    }
    
    public interface OnPairButtonClickListener {
        public abstract void onPairButtonClick(int position);
    }
}