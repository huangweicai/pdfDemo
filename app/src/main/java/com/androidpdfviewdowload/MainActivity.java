package com.androidpdfviewdowload;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidpdfviewdowload.createpdf.PdfTestRunner;
import com.androidpdfviewdowload.showpdf.PdfActivity;
import com.androidpdfviewdowload.showpdf.PermissionHelp;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp下载文件，及相关ui逻辑显示
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.show_pdf)
    Button show_pdf;
    @Bind(R.id.create_pdf)
    Button create_pdf;

    @Bind(R.id.fileDownload)
    Button btnFileDownload;
    @Bind(R.id.downloadSize)
    TextView tvDownloadSize;
    @Bind(R.id.tvProgress)
    TextView tvProgress;
    @Bind(R.id.netSpeed)
    TextView tvNetSpeed;

    //String pdfUrl = "http://partners.adobe.com/public/developer/en/xml/AdobeXMLFormsSamples.pdf";
    String pdfUrl = "http://m-health-image.oss-cn-shenzhen.aliyuncs.com/bcd986d4db5443398a1b7cbc9706ab96-20170511090500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //sd卡动态权限读写
        PermissionHelp.getInstance().verifyStoragePermissions(this);
    }

    //开始下载
    @OnClick(R.id.fileDownload)
    public void downPdf(View view) {
        OkHttpUtils.get(pdfUrl)
                .tag(this)
                .execute(new DownloadFileCallBack(Environment.getExternalStorageDirectory() +
                        "/temp", "qcl.pdf"));//保存到sd卡
    }

    //查看下载的pdf
    @OnClick(R.id.show_pdf)
    public void showPdf(View view) {
        startActivity(new Intent(this, PdfActivity.class));
    }

    //生成pdf入口
    @OnClick(R.id.create_pdf)
    public void createPdf(View view) {
        startActivity(new Intent(this, PdfTestRunner.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

    private class DownloadFileCallBack extends FileCallback {

        public DownloadFileCallBack(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onBefore(BaseRequest request) {
            btnFileDownload.setText("正在下载中");
        }

        @Override
        public void onResponse(boolean isFromCache, File file, Request request, Response response) {
            btnFileDownload.setText("下载完成");
            show_pdf.setVisibility(View.VISIBLE);
        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            System.out.println("downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);

            //数据大小格式化
            String downloadLength = Formatter.formatFileSize(getApplicationContext(),
                    currentSize);
            String totalLength = Formatter.formatFileSize(getApplicationContext(), totalSize);
            tvDownloadSize.setText(downloadLength + "/" + totalLength);

            //网速
            String netSpeed = Formatter.formatFileSize(getApplicationContext(), networkSpeed);
            tvNetSpeed.setText(netSpeed + "/S");

            //进度
            tvProgress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
        }

        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            super.onError(isFromCache, call, response, e);
            btnFileDownload.setText("下载出错");
        }
    }


}
