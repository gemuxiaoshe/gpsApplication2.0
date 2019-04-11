package cn.gemuxiaoshe.gpsapplication20;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
public class MainActivity extends AppCompatActivity {
    private LocationManager lm;
    private TextView tv_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show = (TextView) findViewById(R.id.tv_show);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!isGpsAble(lm)) {
            Toast.makeText(MainActivity.this, "请打开Gps!", Toast.LENGTH_SHORT).show();
            openGps();
        }
        // 从gps获取最近的定位信息
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location lc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateShow(lc);
        //设置间隔两秒获得一次gps定位信息
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // 当gps定位信息发生改变时,更新定位
                updateShow(location);
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String provider) {
                // 当gpsLocationProvider可用时,更新定位
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                updateShow(lm.getLastKnownLocation(provider));
            }
            @Override
            public void onProviderDisabled(String s) {
            updateShow(null);
            }
        });
    }
    // 定义更新显示的方法
    private void updateShow(Location location){
        if (location!=null){
            StringBuilder sb =new StringBuilder();
            sb.append("当前gps位置定位信息:\n");
            sb.append("经度:"+location.getLongitude()+"\n");
            sb.append("维度:"+location.getLatitude()+"\n");
            sb.append("海拔:"+location.getAltitude()+"\n");
            sb.append("速度:"+location.getSpeed()+"\n");
            sb.append("方位:"+location.getBearing()+"\n");
            sb.append("时间:"+location.getTime()+"\n");
            sb.append("定位精度:"+location.getLongitude()+"\n");
            tv_show.setText(sb.toString());
        }else
            tv_show.setText("");
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
