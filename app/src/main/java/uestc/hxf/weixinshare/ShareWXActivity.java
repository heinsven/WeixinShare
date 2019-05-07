package uestc.hxf.weixinshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import uestc.hxf.weixinshare.adapter.ShareGridAdapter;
import uestc.hxf.weixinshare.widget.ShareGridDialog;

public class ShareWXActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ShareWXActivity";
    private EditText et_share_title;
    private EditText et_share_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_wx);
        et_share_title = (EditText) findViewById(R.id.et_share_title);
        et_share_content = (EditText) findViewById(R.id.et_share_content);
        findViewById(R.id.btn_share_wx).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_share_wx) {
            ShareGridDialog dialog = new ShareGridDialog(this, null);
            dialog.setUrl("http://blog.csdn.net/aqi00");
            dialog.setTitle(et_share_title.getText().toString());
            dialog.setContent(et_share_content.getText().toString());
            dialog.setImgUrl("http://avatar.csdn.net/C/1/5/1_aqi00.jpg");
            dialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode + " resultCode="
                + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN
                || requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data,
                    ShareGridAdapter.mLoginListener);
        } else if (requestCode == Constants.REQUEST_QQ_SHARE
                || requestCode == Constants.REQUEST_QZONE_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data,
                    ShareGridAdapter.mShareListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
