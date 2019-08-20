package sdk.pay.it.evergrandelib.com.paysdk;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import cn.evergrande.it.common.http.HttpManager;
import cn.evergrande.it.pos.business.PaymentSDK;
import cn.evergrande.it.pos.utils.SystemUtil;

public class MainApplication extends MultiDexApplication {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PaymentSDK.initSDK(getApplicationContext(),
                "Y100",//商户id
                "ticketVTXwqOGwrYvq9PT1UjhhAm1ve4");
        //支付初始化
//        PayManager.init(this, "", "APP");
//        PayManager.setOrUpdateAuthorization("authorization");
    }

}
