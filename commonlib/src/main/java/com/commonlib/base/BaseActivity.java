package com.commonlib.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.commonlib.R;
import com.commonlib.utils.LogUtils;
import com.commonlib.views.LazyTextView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpParams;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext = this;
    protected String TAG;
    private SweetAlertDialog sweetAlertDialog;
    protected HttpParams mHttpParams = new HttpParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        TAG = this.getClass().getSimpleName();
        LogUtils.d(TAG, TAG + "-isCreate");
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initVariable();
        processLogic(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, TAG + "-isDestroy");
        ButterKnife.unbind(this);
        System.gc();
    }

    public abstract int getContentViewId();

    protected void setBack() {
        ImageView back = (ImageView) findViewById(getResources().getIdentifier("back", "id", mContext.getPackageName()));
        if (back == null)
            return;
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    protected void setTitle(String titleName) {
        TextView title = (TextView) findViewById(getResources().getIdentifier("title", "id", mContext.getPackageName()));
        if (title == null)
            return;
        title.setText(titleName);
    }

    protected void setRight() {
        LazyTextView right = (LazyTextView) findViewById(getResources().getIdentifier("submit", "id", mContext.getPackageName()));
        if (right == null)
            return;
        right.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化变量
     */
    protected abstract void initVariable();

    /**
     * 给View控件添加事件监听器
     */
//    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 调用接口时，显示dialog
     */
    public void showDialog() {
        showDialog(mContext.getResources().getString(R.string.loading), R.color.colorPrimary);
    }

    public void showDialog(String content, int colorRes) {
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(mContext, sweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.getProgressHelper().setBarColor(colorRes);
            sweetAlertDialog.setTitleText(content);
            sweetAlertDialog.setCancelable(true);
            sweetAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    OkHttpUtils.getInstance().cancelTag(BaseConfig.httpTag);
                }
            });
            if (!sweetAlertDialog.isShowing()) {
                sweetAlertDialog.show();
            }
        }
    }

    /**
     * 处理接口返回时，隐藏dialog
     */
    public void dismissDialog() {
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
            sweetAlertDialog = null;
        }
    }
}
