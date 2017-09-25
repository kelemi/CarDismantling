package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuComponent;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.updateComponent.ActivityUpdateComponent;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//添加零件
public class ActivityAddComponent extends BaseActivity implements AddComponentContract.View {

    public static final int REQUEST_TO_UPDATE = 0x22;

    private ListView lvComponents;
    private String baseid;//厂家ID
    private String tstype;//推送类型
    private String did;//分析id
    private AddComponentContract.Presenter presenter;

    private List<WeiChuComponent> weiChuComponentList = new ArrayList<>();

    private List<String> components = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);
        initTitleWithRightTvOrIv(null, null, "添加零件", true, null, null);
        initData();
        initView();
        assignView();

        presenter.getUnSignPartByCar(baseid);
    }

    @Override
    public void initData() {

        baseid = getIntent().getStringExtra("baseid");
        tstype = getIntent().getStringExtra("tstype");
        did = getIntent().getStringExtra("did");
        presenter = new AddComponentPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
    }

    @Override
    public void assignView() {
        //零件的点击事件
        lvComponents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent toUpComponent = new Intent(ActivityAddComponent.this, ActivityUpdateComponent.class);
                WeiChuComponent weiChuComponent = weiChuComponentList.get(position);
                toUpComponent.putExtra("weiChuComponent", (Serializable) weiChuComponent);
                toUpComponent.putExtra("tstype", tstype);
                toUpComponent.putExtra("did", did);
                toUpComponent.putExtra("baseid", baseid);
                startActivityForResult(toUpComponent, REQUEST_TO_UPDATE);
            }
        });
    }

    @Override
    public void initView() {
        lvComponents = (ListView) findViewById(R.id.lv_components);
    }

    @Override
    public void getUnSignPartByCarFail(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUnSignPartByCarSuccess(List<WeiChuComponent> weiChuComponents) {
        weiChuComponentList.clear();
        weiChuComponentList.addAll(weiChuComponents);

        components.clear();
        for (WeiChuComponent weiChuComponent : weiChuComponentList) {
            components.add(weiChuComponent.getPartname());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_weichu_component, components);
        lvComponents.setAdapter(arrayAdapter);

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
    public void setPresenter(AddComponentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回，刷新界面
        if (requestCode == REQUEST_TO_UPDATE && resultCode == RESULT_OK) {
           // presenter.getUnSignPartByCar(baseid);
            setResult(RESULT_OK);
            finish();
        }
    }
}
