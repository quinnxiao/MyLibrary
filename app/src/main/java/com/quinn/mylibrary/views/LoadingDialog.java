package com.quinn.mylibrary.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.quinn.mylibrary.R;

/**
 * 加载对话框
 */
public class LoadingDialog extends ProgressDialog {

    private CharSequence mMessage;
    private TextView mMessageView;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.loading_dialog);
        mMessageView = (TextView) findViewById(R.id.message);
        if (mMessage != null) {
            setMessage(mMessage);
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void setMessage(CharSequence message) {
        if (mMessageView!=null) {
            mMessageView.setText(message);
        }else{
            mMessage = message;
        }
    }

    @Override
    public void show() {
        super.show();
    }
}