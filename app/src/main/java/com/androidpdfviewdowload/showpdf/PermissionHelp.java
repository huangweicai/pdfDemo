package com.androidpdfviewdowload.showpdf;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * 创建时间：2017/5/16
 * 编写者：黄伟才
 * 功能描述：动态权限帮助类
 */

public class PermissionHelp {

    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    //sd卡动态权限读写
    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PermissionHelp instance;

    private PermissionHelp() {
    }

    //唯一实例入口
    public static PermissionHelp getInstance() {
        if (null == instance) {
            synchronized (PermissionHelp.class) {
                if (null == instance) {
                    instance = new PermissionHelp();
                }
            }
        }
        return instance;
    }
}
