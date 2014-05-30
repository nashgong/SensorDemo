package com.nash.android.sensor.demo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    /**
     * 传感器manager
     */
    private SensorManager sensorManager;
    /**
     * 传感器
     */
    private Sensor sensor;
    /**
     * 用于显示距离的textview
     */
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 初始化界面
        textView = (TextView) findViewById(R.id.text);
        // 获取SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        //注册传感器，第一个参数为距离监听器，第二个是传感器类型，第三个是延迟类型
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);//注销传感器监听
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] its = event.values;
        if (its != null && event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            // 经过测试，当手贴近距离感应器的时候its[0]返回值为0.0，当手离开时返回8.0
            textView.setText(its[0] + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
