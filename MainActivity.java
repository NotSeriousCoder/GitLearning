package com.bingor.forandroidlearning.multi_process.process1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bingor.forandroidlearning.R;
import com.bingor.forandroidlearning.multi_process.process2.SupportActivityGlobal;
import com.bingor.forandroidlearning.multi_process.process2.SupportActivityPrivate;
import com.bingor.forandroidlearning.multi_process.syncTest.SyncTest;
import com.bingor.utillib.log.Log;

/**
 * Created by Bingor on 2019/3/26.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

    }

    public void goPriProcess(View view) throws InterruptedException {
        SharedPreferences sp = getSharedPreferences("abc", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("qqq", 1);
		editor.putInt("fff", 1);
        editor.commit();
        startActivityForResult(new Intent(this, SupportActivityPrivate.class), 0x111);
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                SyncTest syncTest = new SyncTest();
//                for (int i = 0; i < 1000; i++) {
//                    syncTest.add();
//                }
//            }
//        };
//        thread.start();
//        thread.join();
//        Log.d("count == " + SyncTest.count);
    }

    public void goGloProcess(View view) {
        startActivity(new Intent(this, SupportActivityGlobal.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sp = getSharedPreferences("abc", MODE_PRIVATE);
        Log.d("sp value == " + sp.getInt("aaa", -1));
		Log.d("sp value == " + sp.getInt("aaa", -1));
    }
}
