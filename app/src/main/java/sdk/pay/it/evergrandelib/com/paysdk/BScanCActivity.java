//package sdk.pay.it.evergrandelib.com.paysdk;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import cn.evergrandelib.it.pos.Config;
//import cn.evergrandelib.it.pos.business.PaymentSDK;
//import cn.evergrandelib.it.pos.business.bean.CancelResult;
//import cn.evergrandelib.it.pos.business.bean.PayResult;
//import cn.evergrandelib.it.pos.business.bean.QueryCheckResult;
//import cn.evergrandelib.it.pos.business.callback.CancelCallBack;
//import cn.evergrandelib.it.pos.business.callback.PayCallBack;
//import cn.evergrandelib.it.pos.business.callback.QueryCheckResultCallBack;
//import cn.evergrandelib.it.pos.network.builder.PayBuild;
//import cn.evergrandelib.it.pos.network.builder.QueryCheckResultBuild;
//import cn.evergrandelib.it.pos.network.entity.CancelResponse;
//import cn.evergrandelib.it.pos.network.entity.PayResponse;
//import cn.evergrandelib.it.pos.network.entity.QueryCheckResultResponse;
//
//public class BScanCActivity extends Activity implements View.OnClickListener{
//    private TextView testContentTv;
//    private EditText prepayIdEt;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bscanc);
//        findViewById(R.id.button4).setOnClickListener(this);
//        testContentTv = (TextView)findViewById(R.id.test_content);
//        prepayIdEt = (EditText)findViewById(R.id.prepayid_et);
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.button4:
//                checkAndPay();
//                break;
//            case R.id.button5:
//                cancelOrder();
//                break;
//        }
//    }
//
//    private void cancelOrder(){
//        CancelCallBack cancelCallBack = new CancelCallBack() {
//            @Override
//            public void onSuccess(CancelResult cancelResponse) {
//                Toast.makeText(BScanCActivity.this,"取消订单成功,订单:"+cancelResponse.getPrepay_id()+"  更新状态为："+cancelResponse.getTrade_state(),Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(int stateCode, String errorInfo) {
//                Toast.makeText(BScanCActivity.this,"取消订单失败:"+errorInfo,Toast.LENGTH_SHORT).show();
//
//            }
//        };
//        PaymentSDK.getInstance().cancelOrder(prepayIdEt.getText().toString(),cancelCallBack);
//    }
//
//    private void checkAndPay(){
//        final QueryCheckResultCallBack queryCheckResultCallBack = new QueryCheckResultCallBack() {
//            @Override
//            public void onSuccess(QueryCheckResult queryCheckResult) {
//                int tradeState = queryCheckResult.getTrade_state();
//                if ("tmp_pay_success".equals(tradeState)) {
//                    testContentTv.append("query check result success!\n");
//                }else if("tmp_pay_fail".equals(tradeState)){
//                    testContentTv.append("query check result fail!\n");
//                    //todo 扫码失败了，需要重新走B扫C的流程
//                }else if("USERPAYING".equals(tradeState)){
//                    //继续轮询
//                }
//                testContentTv.append(queryCheckResult.toString());
//            }
//
//            @Override
//            public void onError(int stateCode, String errorInfo) {
//
//            }
//        };
//
//        final PayCallBack payCallBack = new PayCallBack() {
//            @Override
//            public void onSuccess(PayResult payResult) {
//                testContentTv.setText("request success:"+payResult.toString());
//                int tradeState = payResult.getTrade_state();
//                if ("USERPAYING".equals(tradeState)) {//需要等待C端输入密码，进入轮询
//                    PaymentSDK.getInstance().prepay_poll(null,null,
//                            null,null,null,
//                            null);
//                    testContentTv.append("start check result loop\n");
//                    testContentTv.append(payResult.toString());
//                }else if("SUCCESS".equals(tradeState)){
//                    testContentTv.append("prepay_pos success!\n");
//                    testContentTv.append(payResult.toString());
//                }else if("tmp_pay_fail".equals(tradeState)){
//                    //todo C端应刷新二维码并重新发起请求
//                    testContentTv.append("prepay_pos fail!\n");
//                    testContentTv.append(payResult.toString());
//                }
//            }
//
//            @Override
//            public void onError(int stateCode, String errorInfo) {
//                testContentTv.setText("request error stateCode:"+stateCode+"  errorInfo:"+errorInfo);
//            }
//        };
//        PaymentSDK.getInstance().prepay_pos(Config.TMP_DATA.TMP_B2C_PREPAY_ID, Config.TMP_DATA.TMP_B2C_QRCODE, payCallBack);
//    }
//}
