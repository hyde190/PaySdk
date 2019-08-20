//package sdk.pay.it.evergrandelib.com.paysdk;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import cn.evergrandelib.it.pos.business.PaymentSDK;
//import cn.evergrandelib.it.pos.business.bean.QueryPayResult;
//import cn.evergrandelib.it.pos.business.callback.QueryPayResultCallBack;
//import cn.evergrandelib.it.pos.network.builder.QueryPayResultBuild;
//import cn.evergrandelib.it.pos.network.entity.QueryPayResultResponse;
//
//public class CScanBActivity extends Activity implements View.OnClickListener{
//
//    private TextView testContentTv;
//    private LinearLayout qrCodeLL;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.cscanb);
//        findViewById(R.id.button4).setOnClickListener(this);
//        testContentTv = (TextView)findViewById(R.id.test_content);
//        qrCodeLL = (LinearLayout)findViewById(R.id.qrcode_ll);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button4:
//
////                PaymentSDK.getInstance().prepay_poll(Config.TMP_DATA.TMP_C2B_APP_ID,
////                        Config.TMP_DATA.TMP_C2B_MCH_ID, Config.TMP_DATA.TMP_C2B_PREPAY_ID,
////                        Config.TMP_DATA.TMP_C2B_PAYTYPE,qrCodeLL, new QueryPayResultCallBack() {
////                    @Override
////                    public void onSuccess(QueryPayResult queryPayResult) {
////                        if(null == queryPayResult)return;
////                       // testContentTv.setText("request success:"+queryPayResult.getGoods_detail());
////                        testContentTv.setText("request success:");
////                    }
////
////                    @Override
////                    public void onError(int stateCode, String errorInfo) {
////                        testContentTv.setText("request error stateCode:"+stateCode+"  errorInfo:"+errorInfo);
////                        Log.d("xzw","null:onError("+stateCode+","+errorInfo+"):42");
////
////                    }
////                });
//                break;
//
//        }
//    }
//}
