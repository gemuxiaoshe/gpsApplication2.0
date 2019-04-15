package cn.gemuxiaoshe.gpsapplication20;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.gemuxiaoshe.gpsapplication20.Tools.Timetool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LocationManager lm;
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7;
    private Button btn1, btn2;
    private List<GpsSatellite> numSatlliteList = new ArrayList<GpsSatellite>();  // 卫星信号
    private Location location;

    private LocationListener locationListener= new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // 当gps定位信息发生改变时,更新定位
            updateShow(location);
        }
        @Override
        public void onProviderEnabled(String provider) {
            // 当gpsLocationProvider可用时,更新定位
            updateShow(null);
        }
        @Override
        public void onProviderDisabled(String s) {
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button)findViewById(R.id.start);
        btn2 = (Button)findViewById(R.id.stop);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_7 = (TextView) findViewById(R.id.tv_7);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }
    //点击事件

    public void onClick(View v) {
        if (v == btn1) {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Toast.makeText(this, "gps已关闭,请手动打开再试!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "gps定位中...", Toast.LENGTH_SHORT).show();
            }
            // new 对象设置精度信息
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(true);
            criteria.setBearingRequired(true);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);

            String provider = lm.getBestProvider(criteria, true);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location = lm.getLastKnownLocation(provider);
            updateShow(location);
            //设置动态监听,时间为两秒
            lm.requestLocationUpdates(provider,2000,0,locationListener);
            // 设置动态回调函数.statusListener是回调函数
            lm.addGpsStatusListener(statusListener);  // 注册状态信息回调.
        }else if (v == btn2){
            finish();
           // super.onDestroy();
           // super.onStop();
           // lm.removeUpdates(locationListener);
        }

    }

    // 卫星状态监听器
    GpsStatus.Listener statusListener = new GpsStatus.Listener() {
        @Override
        public void onGpsStatusChanged(int i) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            GpsStatus status = lm.getGpsStatus(null);
            updateGpsStatus(i,status);
        }
    };

    // 获取卫星数

    private void updateGpsStatus(int event, GpsStatus status){
        if (status == null){
        }else if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS){
            // 获取最大的卫星数(这只是一个预设的值)
            int maxStaellites = status.getMaxSatellites();
            Iterator<GpsSatellite> it = status.getSatellites().iterator();
            numSatlliteList.clear();
            int count = 0;
            while(it.hasNext() && count <= maxStaellites){
                GpsSatellite s = it.next();
                numSatlliteList.add(s);
                count++;
            }
        }else if(event == GpsStatus.GPS_EVENT_STARTED){
            // 定位开始
        }else if (event == GpsStatus.GPS_EVENT_STOPPED){
            // 定位结束
        }
    }

    // 定义更新显示的方法
    private void updateShow(Location location){
        if (location!=null){

            tv_1.setText("经度:"+location.getLongitude());
            tv_2.setText("维度:"+location.getLatitude());
            tv_3.setText("海拔:"+location.getAltitude());
            tv_4.setText("速度:"+location.getSpeed());
            tv_5.setText("方位:"+location.getBearing());
            tv_6.setText("时间:"+Timetool.SdateAllTime_mc());
            tv_7.setText("卫星数:"+numSatlliteList.size());
        }else
        {
            tv_1.setText("地理位置位置或正在获取地理位置中...");
        }
    }


    private boolean isGpsAble(LocationManager lm) {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)?true:false;
    }

    // 打开设置界面让用户自己设置
    private void openGps(){
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivityForResult(intent,0);
    }
}
