package sdk.pay.it.evergrandelib.com.paysdk;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

import cn.evergrande.it.pos.business.callback.PreInfoCallBack;
import cn.evergrande.it.pos.jni.EncryptUserKey;
//import cn.evergrande.it.pos.utils.L;
import java.util.ArrayList;
import java.util.List;
//import cn.evergrande.it.common.http.HttpCallBack;
import cn.evergrande.it.common.zxing.view.QrBarView;
import cn.evergrande.it.pos.business.bean.CancelResult;
import cn.evergrande.it.pos.business.PaymentSDK;
import cn.evergrande.it.pos.business.bean.PayResult;
import cn.evergrande.it.pos.business.bean.QueryPayResult;
import cn.evergrande.it.pos.business.bean.RefundResult;
import cn.evergrande.it.pos.business.callback.CancelCallBack;
import cn.evergrande.it.pos.business.callback.PayCallBack;
import cn.evergrande.it.pos.business.callback.QueryPayResultCallBack;
import cn.evergrande.it.pos.business.callback.RefundCallBack;
import cn.evergrande.it.pos.network.ResponseCodeEnum;

import cn.evergrande.it.pos.network.entity.PrePaymentResp;
import sdk.pay.it.evergrandelib.com.paysdk.order.PrePaymentOrderIdResp;
import sdk.pay.it.evergrandelib.com.paysdk.qrview.QrScanActivity;
public class PayOrderActivity extends AppCompatActivity implements View.OnClickListener {
    //测试功能函数
    Button pay_prepare,scan_code,mQrcodeBt;
    QrBarView qrImage;
    //暂存预付订单信息
    static PrePaymentResp orderIdResp;
    //缓存支付结果，包括保留支付流水号
    PayResult mPayResult;
    private TextView logTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
    }

    public void initView() {
        findViewById(R.id.pay_prepare_pwd).setOnClickListener(this);
        findViewById(R.id.pay_prepare_npwd).setOnClickListener(this);
        findViewById(R.id.scan_code).setOnClickListener(this);
        findViewById(R.id.cancel_order).setOnClickListener(this);
        findViewById(R.id.clear_log).setOnClickListener(this);
        findViewById(R.id.refund_order).setOnClickListener(this);
        findViewById(R.id.initsdk).setOnClickListener(this);
        logTextView = (TextView)findViewById(R.id.logTextView);
        logTextView.setOnClickListener(this);
        mQrcodeBt=findViewById(R.id.createQrCode);
        mQrcodeBt.setOnClickListener(this);
        qrImage=(QrBarView)findViewById(R.id.qrImage);
    }
    /**
     * step1:生成订单id，只是针对票务系统
     *
     * */
//    public void createOrderIdNpwd(View v ) {
//        //qrBarView.syncSetBarImage("cjkcdscunoncnnece12903jn fnn dbbifb fibd b",qrBarView.getWidth(),200,24,true);
//        List<CreatOrderIdRep.TicketDetailedOrderDto> dtos = new ArrayList<>();
//        dtos.add(new CreatOrderIdRep.TicketDetailedOrderDto("00007191", 3,
//                "00001734_00007191", "2019-06-06", "00"));
//        dtos.add(new CreatOrderIdRep.TicketDetailedOrderDto("00007192", 1,
//                "00001734_00007192", "2019-06-06", "00"));
//        addToLogView("向业态系统请求生成订单");
//
//        new CreatGoodsOrderIdBuilder.Builder()
//                .addBodyObj(new CreatOrderIdRep(
//                        new CreatOrderIdRep.TicketOrderDto("888da101a2fc46b7a2ccff9b2046da97",
//                                "海洋王国", "5985", "jack", "13696969696",
//                                "1", "458789878978997"), dtos))
//
//                .build(new HttpCallBack<CreatGoodsOrderIdBuilder>() {
//                    @Override
//                    public void onSuccess(CreatGoodsOrderIdBuilder builder) {
//                        if (builder != null && builder.isSuccess()) {
//                            CreatGoodsOrderIdResp resp = builder.data;
//                            L.i( resp.toString() + " " + builder.code + resp.getOrderNo());
//                            //step2 生成预付订单
//                            // prePaymentOrderIdNeedPwd(resp.getOrderNo());
//                            addToLogView("生成免密订单:"+resp.toString() + " " + builder.code + resp.getOrderNo());
//                            prePaymentOrderIdNoNeedPwd(resp.getOrderNo());
//                        }
//                    }
//                    @Override
//                    public void onError(int stateCode, String errorInfo) {
//                        L.i(  errorInfo);
//                    }
//                });
//    }
    /**
     * step1:生成订单id，只是针对票务系统
     *
     * */
//    public void createOrderIdPwd(View v ) {
//        //qrBarView.syncSetBarImage("cjkcdscunoncnnece12903jn fnn dbbifb fibd b",qrBarView.getWidth(),200,24,true);
//        List<CreatOrderIdRep.TicketDetailedOrderDto> dtos = new ArrayList<>();
//        dtos.add(new CreatOrderIdRep.TicketDetailedOrderDto("00007191", 3,
//                "00001734_00007191", "2019-06-06", "00"));
//        dtos.add(new CreatOrderIdRep.TicketDetailedOrderDto("00007192", 1,
//                "00001734_00007192", "2019-06-06", "00"));
//        addToLogView("向业态系统请求生成订单");
//        new CreatGoodsOrderIdBuilder.Builder()
//                .addBodyObj(new CreatOrderIdRep(
//                        new CreatOrderIdRep.TicketOrderDto("888da101a2fc46b7a2ccff9b2046da97",
//                                "海洋王国", "5985", "jack", "13696969696",
//                                "1", "458789878978997"), dtos))
//                .build(new HttpCallBack<CreatGoodsOrderIdBuilder>() {
//                    @Override
//                    public void onSuccess(CreatGoodsOrderIdBuilder builder) {
//                        if (builder != null && builder.isSuccess()) {
//                            CreatGoodsOrderIdResp resp = builder.data;
//                            L.i(resp.toString() + " " + builder.code + resp.getOrderNo());
//                            //step2 生成预付订单
//                            addToLogView("生成非免密订单:"+resp.toString() + " " + builder.code + resp.getOrderNo());
//                            prePaymentOrderIdNeedPwd(resp.getOrderNo());
//
//                        }
//                    }
//                    @Override
//                    public void onError(int stateCode, String errorInfo) {
//                        L.i(  errorInfo);
//                    }
//                });
//
//    }
    /**
     * step2 发起预付订单
     *金额大于600，需要输入支付密码
     *
     *
     * */
    private void prePaymentOrderIdNeedPwd(final String orderId) {
        PaymentSDK.getInstance().getPrePaymentInfo(PayOrderActivity.this,
                "海洋王国",
                "LYY0110001",
                "电影会所",
                "1234567890",
                "线下票务",
                "test",
                "海洋世界票务两张",
                "海洋世界购票两张",
                orderId,
                201.0f,
                201.0f,
                2.0f,
                "test",
                "test",
                new PreInfoCallBack() {
                    @Override
                    public void onSuccess(PrePaymentResp preInfoResult) {

                        Log.i("abcdef", "step2 pre data: "+preInfoResult);

                        //step3 根据预付订单掉起钱包线上支付（获取是否需要密码，以及加密秘钥规则），或者给pos机器生成二维码
                        Toast.makeText(PayOrderActivity.this,"订单生成完成",Toast.LENGTH_SHORT).show();
                        orderIdResp = preInfoResult;
                        addToLogView("订单生成完成");
                        addToLogView(orderIdResp+"");

                    }

                    @Override
                    public void onError(String stateCode, String errorInfo) {
                        addToLogView("stateCode:"+stateCode+"  errorInfo:"+errorInfo);
                        Log.i("abcdef", "step2: "+errorInfo);
                    }
                }
        );
    }

    /**
     * step2 发起预付订单
     * 获取预付订单之后，+用户的支付条形码，发起支付
     *
     *100块钱一次，免密
     * */
    private void prePaymentOrderIdNoNeedPwd(final String orderId) {
        PaymentSDK.getInstance().getPrePaymentInfo(PayOrderActivity.this,
                "海洋王国",
                "LYY0110001",
                "电影会所",
                "1234567890",
                "线下票务",
                "test",
                "海洋世界票务两张",
                "海洋世界购票两张",
                orderId,
                2.0f,
                2.0f,
                1.0f,
                "test",
                "test",
                new PreInfoCallBack() {
                    @Override
                    public void onSuccess(PrePaymentResp preInfoResult) {
                        Log.i("abcdef", "step2 pre data: "+preInfoResult);
                        //step3 根据预付订单掉起钱包线上支付（获取是否需要密码，以及加密秘钥规则），或者给pos机器生成二维码
                        Toast.makeText(PayOrderActivity.this,"订单生成完成",Toast.LENGTH_SHORT).show();
                        orderIdResp = preInfoResult;
                        addToLogView("订单生成完成");
                        addToLogView(orderIdResp+"");
                    }
                    @Override
                    public void onError(String stateCode, String errorInfo) {
                        addToLogView("stateCode:"+stateCode+"  errorInfo:"+errorInfo);
                        Log.i("abcdef", "step2: "+errorInfo);
                    }
                }


        );


    }

    /**
     * 发起预扣
     *
     * 条形码+预付订单id
     *
     * **/
    public void prepay_pos(String qrcode){
       // if(orderIdResp==null||orderIdResp.getPrepayId()==null)return;
        // ToastUtils.showShort("发起支付");
        addToLogView("发起支付");
        PaymentSDK.getInstance().prepay_pos(PayOrderActivity.this,"订单号", qrcode, new PayCallBack() {
            @Override
            public void onSuccess(PayResult payResult) {
              //  L.e("this is prepay_pos+onSuccess+++"+payResult.getTradeState());
                addToLogView("this is prepay_pos+onSuccess+++"+payResult.getTradeState());
                //这里判断返回什么状态；更加不同状态确定是否轮训；
                if(payResult.getTradeState()== ResponseCodeEnum.ORDER_STATE_SUCCESS.getCode()) {
                    addToLogView("扣款成功："+payResult);
                    Toast.makeText(PayOrderActivity.this,"扣款成功",Toast.LENGTH_SHORT).show();
                    mPayResult=payResult;
                }else{
                    addToLogView("扣款失败："+payResult);
                    Toast.makeText(PayOrderActivity.this,"扣款失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String stateCode, String errorInfo) {
             //   L.e("this is prepay_pos++onError++"+stateCode);
                addToLogView("扣款失败："+stateCode+"  errorInfo:"+errorInfo);
                Toast.makeText(PayOrderActivity.this,"扣款失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
    /**
     * 生成二维码信息
     *
     * **/
    public void createQrCode(){
      //  if(orderIdResp==null)return;
        Gson gson = new Gson();
        String jsonStr = gson.toJson(orderIdResp);
        qrImage.setQrImage(EncryptUserKey.encodeAES(jsonStr));
        addToLogView("生成二维码："+"this is prepayid+++++++"+orderIdResp.getPrepayId());
      //  L.v("this is prepayid+++++++"+orderIdResp.getPrepayId());
        PaymentSDK.getInstance().prepay_poll(PayOrderActivity.this,orderIdResp.getPrepayId(),
                new QueryPayResultCallBack() {
                    @Override
                    public void onSuccess(QueryPayResult queryPayResult) {
                   //     L.e("this is prepay_poll++onSuccess++"+queryPayResult.getTradeState());
                        if(queryPayResult.getTradeState()==ResponseCodeEnum.ORDER_STATE_SUCCESS.getCode()) {
                            addToLogView("被扫扣款成功："+queryPayResult);
                            Toast.makeText(PayOrderActivity.this,"扣款成功",Toast.LENGTH_SHORT).show();
                            qrImage.setQrImage(EncryptUserKey.encodeAES("这个是二维码"));
                        } else{
                            addToLogView("被扫扣款失败："+queryPayResult);
                            Toast.makeText(PayOrderActivity.this,"扣款失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String stateCode, String errorInfo) {
                      //  L.e("this is prepay_poll++onError++"+stateCode);
                        addToLogView("被扫扣款失败 stateCode："+stateCode+"  errorInfo:"+errorInfo);
                        Toast.makeText(PayOrderActivity.this,"扣款失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //生成预付订单
            case R.id.pay_prepare_pwd:

               // createOrderIdPwd(v);
                break;
            case R.id.pay_prepare_npwd:
                //createOrderIdNpwd(v);
                break;
            case R.id.scan_code:
                // 意图实现activity的跳转
                Intent intent = new Intent(PayOrderActivity.this,
                        QrScanActivity.class);
                startActivityForResult(intent, 1); //REQUESTCODE--->1
                break;
            case R.id.createQrCode:
                createQrCode();
                break;
            case R.id.cancel_order:

                if(orderIdResp==null||orderIdResp.getPrepayId()==null){
                    Toast.makeText(this, "orderIdResp==null||orderIdResp.getPrepayId()==null", Toast.LENGTH_SHORT).show();
                    return;
                }
              //  addToLogView("请求取消订单："+orderIdResp.getPrepayId());
                PaymentSDK.getInstance().cancelOrder(PayOrderActivity.this,orderIdResp.getPrepayId(), new CancelCallBack() {
                    @Override
                    public void onSuccess(CancelResult cancelResult) {
                        addToLogView("取消订单成功:"+cancelResult);
                        Toast.makeText(PayOrderActivity.this, cancelResult.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String stateCode, String errorInfo) {
                        addToLogView("取消订单失败:"+stateCode+"  errorInfo:"+errorInfo);
                        Toast.makeText(PayOrderActivity.this, "stateCode:"+stateCode+"  errorInfo:"+errorInfo, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.clear_log:
                clearLog();
                break;
            case R.id.refund_order:
                refundOrder();
                break;
            case R.id.revoke_order:
                RevokeOrder();
                break;

            case R.id.initsdk:
                String tmp="hede19000";
                //   boolean result = PaymentSDK.getInstance().initSDK("test_mchId","test_device_uuid");
                addToLogView("加密后:"+ EncryptUserKey.encodeAES(tmp)
                        //  +"加密后结果对比："+EncryptUserKey   AESUtils.encrypt("b5e52765b81d1015", tmp)
                        +"\n  解密后："+EncryptUserKey.decodeAES(EncryptUserKey.encodeAES(tmp))
                );
                break;
            default:
                break;
        }
    }
    private void refundOrder(){
        if(     mPayResult==null||mPayResult.getPrepayId()==null){
            Toast.makeText(this, "orderIdResp==null||orderIdResp.getPrepayId()==null", Toast.LENGTH_SHORT).show();
            return;
        }
        addToLogView("请求退款 prePayId："+orderIdResp.getPrepayId()+"  payType:"+1+"  amount"+orderIdResp.getAmount() );
        PaymentSDK.getInstance().refundOrder(PayOrderActivity.this,
                mPayResult.getPaySerialId(),
                "1",//mPayResult.getPrepayId(), 1 钱包线下，2钱包线上
                mPayResult.getAmount(),
                new RefundCallBack() {
                    @Override
                    public void onSuccess(RefundResult cancelResult) {
                        addToLogView("退款成功");
                        Toast.makeText(PayOrderActivity.this,"退款成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String stateCode, String errorInfo) {
                        addToLogView("退款失败:"+stateCode+"  errorInfo:"+errorInfo);
                        Toast.makeText(PayOrderActivity.this, "stateCode:"+stateCode+"  errorInfo:"+errorInfo, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     *
     * 撤单接口
     * **/
    private void RevokeOrder(){
        if(     orderIdResp==null||orderIdResp.getPrepayId()==null){
            Toast.makeText(this, "orderIdResp==null||orderIdResp.getPrepayId()==null", Toast.LENGTH_SHORT).show();
            return;
        }
        addToLogView("请求撤销订单 prePayId："+orderIdResp.getPrepayId()+"  payType:"+1+"  amount"+orderIdResp.getAmount() );
        PaymentSDK.getInstance().RevokeOrder(PayOrderActivity.this,
                orderIdResp.getPrepayId(),
                new RefundCallBack() {
                    @Override
                    public void onSuccess(RefundResult cancelResult) {
                        addToLogView("撤单成功");
                        Toast.makeText(PayOrderActivity.this,"撤单成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String stateCode, String errorInfo) {
                        addToLogView("撤单失败:"+stateCode+"  errorInfo:"+errorInfo);
                        Toast.makeText(PayOrderActivity.this, "stateCode:"+stateCode+"  errorInfo:"+errorInfo, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void addToLogView(final String log) {
        Log.d("xzw", "addToLogView: " + log);
        logTextView.post(new Runnable() {
            @Override
            public void run() {
                logTextView.append(log );
                logTextView.append("\n********************************\n");
                int scrollAmount = logTextView.getLayout().getLineTop(logTextView.getLineCount())
                        - logTextView.getHeight();
                if (scrollAmount > 0)
                    logTextView.scrollTo(0, scrollAmount);
                else
                    logTextView.scrollTo(0, 0);
            }
        });
    }

    private void clearLog(){
        logTextView.post(new Runnable() {
            @Override
            public void run() {
                logTextView.setText("");
            }
        });
    }


    //预付订单信息


    // 为了获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
        // operation succeeded. 默认值是-1
        if (resultCode == 2) {
            if (requestCode == 1) {
                String  mResult = data.getStringExtra("result");
                addToLogView("扫描二维码返回："+mResult);
                Toast.makeText(PayOrderActivity.this,"支付中...",Toast.LENGTH_SHORT).show();
                prepay_pos(mResult);
            }
        }
    }












    @Override
    protected void onDestroy() {
        super.onDestroy();
        PaymentSDK.getInstance().stopPayLoopRequest();
    }
}