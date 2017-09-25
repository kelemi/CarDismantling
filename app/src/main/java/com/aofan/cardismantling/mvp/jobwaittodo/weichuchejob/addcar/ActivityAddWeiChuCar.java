package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcar;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;

public class ActivityAddWeiChuCar extends BaseActivity implements AddWeiChuContract.View {


    private EditText etCarBrand;
    private EditText etEntranceNumber;
    private RadioGroup rgChaijieType;
    private RadioButton rbJingchai;
    private RadioButton rbCuchai;
    private TextView tvSubmit;

    private String userId;
    private String chaiType = "精拆";
    private AddWeiChuContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wei_chu_car);
        initTitleWithRightTvOrIv(null, null, "新增未出手续车", true, null, null);
        initData();
        initView();
        assignView();
    }

    @Override
    public void initData() {
        userId = (String) CommonUtil.getShareValue(ShareKey.SHARE_KEY_USER_ID, "");

        presenter = new AddWeiChuPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        rgChaijieType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    //经拆
                    case R.id.rb_jingchai:
                        chaiType = "精拆";
                        break;
                    //粗拆
                    case R.id.rb_cuchai:
                        chaiType = "粗拆";
                        break;
                }
            }
        });

        //提交
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carBrand = etCarBrand.getText().toString();
                String entranceNumber = etEntranceNumber.getText().toString();

                if (TextUtils.isEmpty(carBrand)) {
                    Toast.makeText(ActivityAddWeiChuCar.this, "车牌号码不为空", Toast.LENGTH_SHORT).show();
                    etCarBrand.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(entranceNumber)) {
                    Toast.makeText(ActivityAddWeiChuCar.this, "自定义入场编号不能为空", Toast.LENGTH_SHORT).show();
                    etEntranceNumber.requestFocus();
                    return;
                }

                presenter.addUnSignCar(userId, carBrand, entranceNumber, chaiType);
            }
        });
    }

    @Override
    public void initView() {

        etCarBrand = (EditText) findViewById(R.id.et_car_brand);
        etEntranceNumber = (EditText) findViewById(R.id.et_entrance_number);
        rgChaijieType = (RadioGroup) findViewById(R.id.rg_chaijie_type);
        rbJingchai = (RadioButton) findViewById(R.id.rb_jingchai);
        rbCuchai = (RadioButton) findViewById(R.id.rb_cuchai);
        tvSubmit = (TextView) findViewById(R.id.tv_submit);
    }

    @Override
    public void addUnSignCarFail(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addUnSignCarSuccess(String tip) {
        //添加成功
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void netError(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showProgressDialog(true, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void setPresenter(AddWeiChuContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
