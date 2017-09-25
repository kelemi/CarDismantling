package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForWeiChuCar;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.IntentKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcomponent.ActivityAddComponent;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuChaijieDetail;
import com.aofan.cardismantling.mvp.picshow.ActivityPicShow;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityWeiChuDetail extends BaseActivity implements WeiChuDetailContract.View, View.OnClickListener, ListAdapterForWeiChuCar.PicItemClickListener {


    private ImageView ivRight;
    private String carbrand;
    private String did;//分析id
    private String baseid;//汽车id
    private String tstype;//推送类型
    private WeiChuDetailContract.Presenter presenter;

    private static final int REQUEST_TO_COMPONENT = 0x30;
    private WeiChuChaijieDetail weiChuChaijieDetail;
    private ListView lvChaijieDetail;
    private TextView tvCompleteChaijie;
    private RelativeLayout noticeEmpty;
    private ListAdapterForWeiChuCar listAdapterForWeiChuCar;
    private TextView tvCarBrand;
    private TextView tvChaijieMan;
    private TextView tvChaijieTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_chu_detail);
        initTitleWithRightTvOrIv(null, null, "拆解详情", true, null, R.drawable.ico_add_white);
        initData();
        initView();
        assignView();
        presenter.getChaiJieDetail(did);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        did = intent.getStringExtra("did");
        baseid = intent.getStringExtra("baseid");
        tstype = intent.getStringExtra("tstype");
        carbrand = intent.getStringExtra("carbrand");
        presenter = new WeiChuDetailPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        ivRight.setOnClickListener(this);

        tvCompleteChaijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.no_saveCompleteOrder(did);
            }
        });
    }

    @Override
    public void initView() {
        ivRight = (ImageView) findViewById(R.id.iv_right);
        lvChaijieDetail = (ListView) findViewById(R.id.lv_chaijie_detail);
        noticeEmpty = (RelativeLayout) findViewById(R.id.notice_empty);
        tvCompleteChaijie = (TextView) findViewById(R.id.tv_complete);

        lvChaijieDetail.setEmptyView(noticeEmpty);
        tvCarBrand = (TextView) findViewById(R.id.tv_car_brand);
        tvChaijieMan = (TextView) findViewById(R.id.tv_chaijie_man);
        tvChaijieTime = (TextView) findViewById(R.id.tv_chaijie_time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加零件
            case R.id.iv_right:
                Intent addLingjian = new Intent(ActivityWeiChuDetail.this, ActivityAddComponent.class);
                addLingjian.putExtra("did", did);
                addLingjian.putExtra("baseid", baseid);
                addLingjian.putExtra("tstype", tstype);
                startActivityForResult(addLingjian, REQUEST_TO_COMPONENT);
                break;
        }
    }

    @Override
    public void getChaiJieDetailFail(String tip) {

        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getChaiJieDetailSuccess(WeiChuChaijieDetail weiChuChaijieDetail) {

        //成功获取赋值
        tvCarBrand.setText("车牌号:" + carbrand);
        tvChaijieMan.setText("拆解人:" + weiChuChaijieDetail.getCreateperson());
        tvChaijieTime.setText("车辆添加时间:" + weiChuChaijieDetail.getCreatetime());
        listAdapterForWeiChuCar = new ListAdapterForWeiChuCar(ActivityWeiChuDetail.this, weiChuChaijieDetail.getPartlist(), this);

        lvChaijieDetail.setAdapter(listAdapterForWeiChuCar);
    }

    @Override
    public void saveCompleterSuccess(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void saveCompleterFail(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
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
    public void setPresenter(WeiChuDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //重刷一下界面
        if (requestCode == REQUEST_TO_COMPONENT && resultCode == RESULT_OK) {
            presenter.getChaiJieDetail(did);
        }
    }

    //查看图片
    @Override
    public void tolookPic(int position, List<String> picpathList) {
        List<String> picUrls = new ArrayList<>();

        picUrls.addAll(picpathList);

        Intent toShowPics = new Intent(ActivityWeiChuDetail.this, ActivityPicShow.class);

        toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_CUR_SHOW_POSITION, position);

        toShowPics.putExtra(IntentKey.INTENT_KEY_PIC_URLS, (Serializable) picUrls);

        startActivity(toShowPics);
    }
}
