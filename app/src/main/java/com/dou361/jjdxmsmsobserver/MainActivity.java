package com.dou361.jjdxmsmsobserver;

import android.Manifest;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dou361.smsobserver.SmsObserver;
import com.dou361.smsobserver.SmsResponseCallback;
import com.dou361.smsobserver.VerificationCodeSmsFilter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.CompositePermissionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SmsResponseCallback {

    private EditText et_name;
    private EditText et_code;
    private EditText et_pwd;
    private Button btn_code;
    private Button bt_register;
    private TimeCount time;
    private SmsObserver smsObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_code = (EditText) findViewById(R.id.et_code);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_code = (Button) findViewById(R.id.btn_code);
        bt_register = (Button) findViewById(R.id.bt_register);
        btn_code.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        time = new TimeCount(60000, 1000);
        /***
         * 构造器
         * @param context
         * @param callback 短信接收器
         * @param smsFilter 短信过滤器
         */
        smsObserver = new SmsObserver(this, this, new VerificationCodeSmsFilter("10690"));
        smsObserver.registerSMSObserver();
        Dexter.checkPermission(new CompositePermissionListener(), Manifest.permission.READ_SMS);
    }

    @Override
    public void onCallbackSmsContent(String code) {
        et_code.setText(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smsObserver.unregisterSMSObserver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code:
                btn_code.setClickable(false);
                time.start();
                break;
            case R.id.bt_register:
                break;
        }
    }


    /**
     * ============================按钮倒计时===============================
     */
    class TimeCount extends CountDownTimer {

        /**
         * 参数依次为总时长,和计时的时间间隔
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            btn_code.setText("重新发送");
            btn_code.setClickable(true);
        }

        /**
         * 计时过程显示
         */
        @Override
        public void onTick(long millisUntilFinished) {
            btn_code.setClickable(false);
            btn_code.setText(millisUntilFinished / 1000 + "秒");
        }
    }

    /** ============================按钮倒计时=============================== */
}
