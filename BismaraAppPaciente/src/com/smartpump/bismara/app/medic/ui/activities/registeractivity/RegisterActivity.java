package com.smartpump.bismara.app.medic.ui.activities.registeractivity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.adapter.TabsRegisterFlowAdapter;

/**
 * Activity
 * 
 * @author nesanche
 *
 */
public class RegisterActivity extends FragmentActivity implements TabListener {
    
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    /** Paginador */
    private ViewPager viewPager;
    /** Adapter que inserta las paginas deseadas en el viewpager */
    private TabsRegisterFlowAdapter mAdapter;
    /** Android activity action bar */
    private ActionBar actionBar;
    /** Nombres de las distintas pestañas */
    private String[] tabs = { "Start", "Name", "License" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.pagerRegister);
        actionBar = getActionBar();
        mAdapter = new TabsRegisterFlowAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
//                final int state = intent.getIntExtra(
//                        BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d("BT Event", "DISCOVERY STARTED");
//                mDeviceList = new ArrayList<BluetoothDevice>();
//
//                progress.show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                    .equals(action)) {
                Log.d("BT Event", "DISCOVERY FINISHED");

//                progress.dismiss();
//
//                adapter.setData(mDeviceList);
//
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
//                        getActivity());
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//                View convertView = (View) inflater.inflate(
//                        R.layout.custom_list, null);
//                alertDialog.setView(convertView);
//                // TODO crear nombre de lista
//                alertDialog.setTitle("List");
//                ListView lv = (ListView) convertView
//                        .findViewById(R.id.lvDevices);
//                lv.setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view,
//                            int position, long id) {
//                        String name = (String) parent.getItemAtPosition(position);
//                    }
//                });
//                String[] devices = new String[mDeviceList.size()];
//                for (int i = 0; i < mDeviceList.size(); i++) {
//                    devices[i] = mDeviceList.get(i).getName();
//                }
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        getActivity(), android.R.layout.simple_list_item_1,
//                        devices);
//                lv.setAdapter(adapter);
//                alertDialog.show();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d("BT Event", "DEVICE FOUND");
//                BluetoothDevice device = (BluetoothDevice) intent
//                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                mDeviceList.add(device);
            }
        }
    };
    
    public void enableBluetooth() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();                    
        } else {
            // Si esta desactivado, activarlo
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1000);
            while(!mBluetoothAdapter.isEnabled()) {
                
            }
            mBluetoothAdapter.startDiscovery();
        }
    }
    
    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        
        super.onDestroy();
    }
}
