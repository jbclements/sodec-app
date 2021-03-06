package edu.calpoly.sodec.sodecapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class PowerActivity extends ActionBarActivity {

    private Float bedroom_total;
    private Float bathroom_total;
    private Float living_total;
    private Float kitchen_total;
    private Float mech_total;
    private Float dining_total;

    private TextView kitchen_power;
    private TextView bathroom_power;
    private TextView bedroom_power;
    private TextView living_power;
    private TextView dining_power;
    private TextView mech_power;

    private PieChartData mData;
    private PieChartView mChart;

    private RelativeLayout mLayout;
    private BannerLayout bannerLayout;

    private String[] mRooms = {"Bedroom", "Kitchen", "Living Room", "Dining Room", "Bathroom",
                               "Mechanical"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        living_total = new Float(0.0);
        bathroom_total = new Float(0.0);
        bedroom_total = new Float(0.0);
        kitchen_total = new Float(0.0);
        dining_total = new Float(0.0);
        mech_total = new Float(0.0);

        initLayout();
        mData = new PieChartData();
        mChart = (PieChartView) findViewById(R.id.roomPowerChart);
        mLayout = (RelativeLayout) findViewById(R.id.PowerLayout);

        setData();

        mLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
                startActivity(new Intent(PowerActivity.this, PowergraphActivity.class));
            }

            public void onSwipeBottom() {
            }
        });
    }

    private void initLayout() {
        setContentView(R.layout.activity_power);
        bannerLayout = new BannerLayout(this);
        //bannerLayout.addView(R.layout.activity_main);

        View view = findViewById(R.id.PowerLayout);
        view.setBackgroundColor(Color.rgb(226, 231, 234));
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);
        bannerLayout.addView(view);
        parent.addView(bannerLayout);
        bannerLayout.setPageTitleText("Power");

        bedroom_power = (TextView) this.findViewById(R.id.bedroom_power);
        bathroom_power = (TextView) this.findViewById(R.id.bathroom_power);
        living_power = (TextView) this.findViewById(R.id.living_power);
        kitchen_power = (TextView) this.findViewById(R.id.kitchen_power);
        mech_power = (TextView) this.findViewById(R.id.mech_power);
        dining_power = (TextView) this.findViewById(R.id.dining_power);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_power, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPowerInfo();
    }

    private void loadPowerInfo() {
        PowerUtils.getPowerByID(Device.POW_USE_LAUNDRY, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                //living_power.setText(response);
                living_total += Float.parseFloat(response);
                living_power.setText(living_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_DISHWASHER, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                kitchen_total += Float.parseFloat(response);
                kitchen_power.setText(kitchen_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_REFRIGERATOR, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                kitchen_total += Float.parseFloat(response);
                kitchen_power.setText(kitchen_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_INDUCTION_STOVE, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                kitchen_total += Float.parseFloat(response);
                kitchen_power.setText(kitchen_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_EWH_SOLAR_WATER_HEATER, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_KITCHEN_RECEPS_1, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                kitchen_total += Float.parseFloat(response);
                kitchen_power.setText(kitchen_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_KITCHEN_RECEPS_2, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                kitchen_total += Float.parseFloat(response);
                kitchen_power.setText(kitchen_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_LIVING_RECEPS, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                living_total += Float.parseFloat(response);
                living_power.setText(living_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_DINING_RECEPS_1, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                dining_total += Float.parseFloat(response);
                dining_power.setText(dining_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_DINING_RECEPS_2, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                dining_total += Float.parseFloat(response);
                dining_power.setText(dining_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_BATHROOM_RECEPS, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                bathroom_total += Float.parseFloat(response);
                bathroom_power.setText(bathroom_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_BEDROOM_RECEPS_1, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                bedroom_total += Float.parseFloat(response);
                bedroom_power.setText(bedroom_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_BEDROOM_RECEPS_2, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                bedroom_total += Float.parseFloat(response);
                bedroom_power.setText(bathroom_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_MECHANICAL_RECEPS, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_ENTRY_RECEPS, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_EXTERIOR_RECEPS, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_GREY_WATER_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_BLACK_WATER_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_THERMAL_LOOP_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_WATER_SUPPLY_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_WATER_SUPPLY_BOOSTER_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_VEHICLE_CHARGING_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_HEAT_PUMP_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });

        PowerUtils.getPowerByID(Device.POW_USE_AIR_HANDLER_RECEP, new ServerConnection.ResponseCallback() {
            @Override
            public void execute(String response) {
                mech_total += Float.parseFloat(response);
                mech_power.setText(mech_total.toString());
                setData();
            }
        });


    }

    public ArrayList<SliceValue> generateSliceValues() {
        ArrayList<SliceValue> slices = (ArrayList<SliceValue>)mData.getValues();
        slices.clear();
        slices.add(new SliceValue(bedroom_total.floatValue(), ChartUtils.COLOR_BLUE)); // bedroom
        slices.add(new SliceValue(kitchen_total.floatValue(), ChartUtils.COLOR_VIOLET)); // kitchen
        slices.add(new SliceValue(living_total.floatValue(), ChartUtils.COLOR_GREEN)); // living room
        slices.add(new SliceValue(dining_total.floatValue(), ChartUtils.COLOR_ORANGE)); // dining room
        slices.add(new SliceValue(bathroom_total.floatValue(), ChartUtils.COLOR_RED)); // bathroom
        slices.add(new SliceValue(mech_total.floatValue(), Color.parseColor("#FF6699"))); // mechanical

        return slices;
    }

    // sets the room pie chart with hardcoded data
    public void setData() {
        ArrayList<SliceValue> slices = generateSliceValues();

        for (int i = 0; i < mRooms.length; i++)
        {
            slices.get(i).setLabel(mRooms[i].toCharArray());
        }
        mData.setHasLabels(true);
        mData.setValues(slices);
        mChart.setPieChartData(mData);

        mChart.setValueTouchEnabled(true);
        mChart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Intent intent = new Intent(PowerActivity.this, PowerUsedRoomActivity.class);
                intent.putExtra("room", mRooms[i]);
                startActivity(intent);
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }
}
