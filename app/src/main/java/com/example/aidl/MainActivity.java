package com.example.aidl;
/*
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface aidl;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidl = IMyAidlInterface.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            aidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void bindService(){
        Intent intent = new Intent();
        intent.setAction("com.example.binder.CaclService");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(){
        unbindService(serviceConnection);
    }

    public void add() throws RemoteException {
        if(aidl!=null){
            int result = aidl.add(5,5);
            Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();

        }
    }

    public void minxy() throws RemoteException {
        if(aidl!=null){
            int result = aidl.min(12,50);
            Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}*/
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.binder.aidl.IMyAidlInterface;


public class MainActivity extends Activity
{
    private IMyAidlInterface mCalcAidl;

    private ServiceConnection mServiceConn = new ServiceConnection()
    {
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            Log.e("client", "onServiceDisconnected");
            mCalcAidl = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.e("client", "onServiceConnected");
            mCalcAidl = IMyAidlInterface.Stub.asInterface(service);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 点击BindService按钮时调用
     * @param view
     */
    public void bindService(View view)
    {
        Intent intent = new Intent();
        intent.setAction("com.example.binder.cacl");
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }
    /**
     * 点击unBindService按钮时调用
     * @param view
     */
    public void unbindService(View view)
    {
        unbindService(mServiceConn);
        Toast.makeText(MainActivity.this,"unbind",Toast.LENGTH_SHORT).show();
    }
    /**
     * 点击12+12按钮时调用
     * @param view
     */
    public void addInvoked(View view) throws Exception
    {

        if (mCalcAidl != null)
        {
            int addRes = mCalcAidl.add(12, 12);
            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();

        }

    }
    /**
     * 点击50-12按钮时调用
     * @param view
     */
    public void minInvoked(View view) throws Exception
    {

        if (mCalcAidl != null)
        {
            int addRes = mCalcAidl.min(52, 12);
            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "服务端未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();

        }

    }

}