package sdk.pay.it.evergrandelib.com.paysdk;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.evergrande.it.common.zxing.view.QrBarView;


public class ZxingActivity extends Activity{
    QrBarView mQrView,mBarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        initView();
    }

    public void initView(){
//        mQrView=findViewById(R.id.qrImage);
//        mQrView.setQrImage("你是谁啊");
//        mBarView=findViewById(R.id.barImage);
//        mBarView.setBarImage("3242343545656756765");
    }



}
