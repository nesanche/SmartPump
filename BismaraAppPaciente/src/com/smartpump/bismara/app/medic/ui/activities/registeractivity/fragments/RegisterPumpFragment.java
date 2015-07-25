package com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.adapter.DeviceListAdapter;

public class RegisterPumpFragment extends Fragment {

    private ProgressDialog progress;
    private BluetoothAdapter mBluetoothAdapter;
    private Button btnScan;
    private ArrayList<BluetoothDevice> mDeviceList;
    private DeviceListAdapter adapter;
    private BluetoothDevice selectedPump;
    private AlertDialog devicesList;
    private AlertDialog bond;
    private boolean isPairing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register_pump, container, false);
        
        isPairing = false;
        
        btnScan = (Button) rootView.findViewById(R.id.btnDiscover);
        //TODO Change text
        btnScan.setText("Buscar infusor");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        adapter = new DeviceListAdapter(getActivity());
        progress = new ProgressDialog(getActivity());
        progress.setMessage("Buscando dispositivos...");
        progress.setCancelable(false);

        btnScan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mBluetoothAdapter.isEnabled()) {
                    scanDevices();
                } else {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, 1000);
                    while (!mBluetoothAdapter.isEnabled()) {

                    }
                    scanDevices();
                }
            }
        });

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(mReceiver, filter);

        return rootView;
    }
    
    private void scanDevices() {
        mBluetoothAdapter.startDiscovery();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mDeviceList = new ArrayList<BluetoothDevice>();
                progress.show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                progress.dismiss();

                adapter.setData(mDeviceList);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.custom_list, null);
                alertDialog.setView(convertView);
                // TODO crear nombre de lista
                alertDialog.setTitle("Lista");
                ListView lv = (ListView) convertView.findViewById(R.id.lvDevices);
                lv.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        askForVinculation(position);
                        devicesList.dismiss();
                    }
                });
                String[] devices = new String[mDeviceList.size()];
                for (int i = 0; i < mDeviceList.size(); i++) {
                    devices[i] = mDeviceList.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, devices);
                lv.setAdapter(adapter);
                devicesList = alertDialog.create();
                if(!isPairing) {
                    devicesList.show();
                }
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device);
            }
        }
    };
    
    private void askForVinculation(int position) {
        final BluetoothDevice device = mDeviceList.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Vincular infusor");
        alert.setMessage("¿Vincular con el infusor " + device.getName() + "?");
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isPairing = true;
                if(device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    selectedPump = device;
                } else {
                    try {
                        Method method = device.getClass().getMethod("createBond", (Class[]) null);
                        method.invoke(device, (Object[]) null);
                    } catch (Exception e) {
                        Log.e("Error bonding device", "Unable to bond to bluetooth device");
                    }
                }
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isPairing = false;
                devicesList.show();
            }
        });
        bond = alert.create();
        bond.show();
    }
}
