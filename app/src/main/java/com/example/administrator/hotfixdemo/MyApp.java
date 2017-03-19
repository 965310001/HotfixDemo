package com.example.administrator.hotfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/19.
 */

public class MyApp extends Application {

    private static final String TAG = "MyApp";

    /**
     * apatch文件
     */
    private static final String APATCH_PATH = "/Dennis.apatch";
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.get("http://127.0.0.1:5000/static/Dennis.apatch")//
                .tag(this)//
                .execute(new FileCallback() {  //文件下载时，可以指定下载的文件目录和文件名
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        // file 即为文件数据，文件保存在指定目录
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
                    }
                });

        mPatchManager=new PatchManager(this);
        mPatchManager.init("1.0");

        mPatchManager.loadPatch();

        //apatch文件的目录
        String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + APATCH_PATH;
        File apatchPath = new File(patchFileString);

        if (apatchPath.exists()) {
            Log.i(TAG, "补丁文件存在");
            try {
                mPatchManager.addPatch(patchFileString);
            } catch (IOException e) {
                Log.i(TAG, "打补丁出错了");
                e.printStackTrace();
            }
        }else{
            Log.i(TAG, "补丁文件不存在");
        }
    }
}
