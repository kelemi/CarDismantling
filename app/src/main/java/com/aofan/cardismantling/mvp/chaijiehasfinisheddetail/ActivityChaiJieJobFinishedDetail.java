package com.aofan.cardismantling.mvp.chaijiehasfinisheddetail;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.adapter.list.ListAdapterForChaiJieLingJianHasFinished;
import com.aofan.cardismantling.bean.ChaiJieLingJianInfoItem;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**拆解任务完成的详情界面
 * Created by Administrator on 2016/11/24.
 */

public class ActivityChaiJieJobFinishedDetail extends BaseActivity{


    private TextView tvChaijieRequest;
    private ListView lv;


    List<ChaiJieLingJianInfoItem> mChaiJieLingJianList;
    ListAdapterForChaiJieLingJianHasFinished mLingJianChaiJieInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaijie_job_finished_detail);
        initTitleWithRightTvOrIv(null,null,"拆解详情",true,null,null);
        initData();
        assignView();
        initView();
    }

    @Override
    public void initData() {
        mChaiJieLingJianList = new ArrayList<>();


        List<String> mLingJianPicList = new ArrayList<>();
        mLingJianPicList.add("http://img002.21cnimg.com/photos/album/20160326/m600/B920004B5414AE4C7D6F2BAB2966491E.jpeg");
        mLingJianPicList.add("http://imgsrc.baidu.com/baike/pic/item/d872d6952b367113d0135e6b.jpg");
        mLingJianPicList.add("http://imgsrc.baidu.com/baike/pic/item/cebd0017d33f2d6b4b90a70d.jpg");
        mLingJianPicList.add("http://d.hiphotos.baidu.com/zhidao/pic/item/f9dcd100baa1cd1181229564b912c8fcc2ce2dda.jpg");

        ChaiJieLingJianInfoItem chaiJieLingJianInfoItem1 = new ChaiJieLingJianInfoItem();
        chaiJieLingJianInfoItem1.setLingJianName("车门测试开发工具");
        chaiJieLingJianInfoItem1.setLingJianNum(5);
        chaiJieLingJianInfoItem1.setLingJianPicList(mLingJianPicList);

        ChaiJieLingJianInfoItem chaiJieLingJianInfoItem2 = new ChaiJieLingJianInfoItem();
        chaiJieLingJianInfoItem2.setLingJianName("车轴");
        chaiJieLingJianInfoItem2.setLingJianNum(5);
        chaiJieLingJianInfoItem2.setLingJianPicList(mLingJianPicList);

        ChaiJieLingJianInfoItem chaiJieLingJianInfoItem3 = new ChaiJieLingJianInfoItem();
        chaiJieLingJianInfoItem3.setLingJianName("车箱");
        chaiJieLingJianInfoItem3.setLingJianNum(2);
        chaiJieLingJianInfoItem3.setLingJianPicList(mLingJianPicList);

        ChaiJieLingJianInfoItem chaiJieLingJianInfoItem4 = new ChaiJieLingJianInfoItem();
        chaiJieLingJianInfoItem4.setLingJianName("车顶");
        chaiJieLingJianInfoItem4.setLingJianNum(4);
        chaiJieLingJianInfoItem4.setLingJianPicList(mLingJianPicList);

        mChaiJieLingJianList.add(chaiJieLingJianInfoItem1);
        mChaiJieLingJianList.add(chaiJieLingJianInfoItem2);
        mChaiJieLingJianList.add(chaiJieLingJianInfoItem3);
        mChaiJieLingJianList.add(chaiJieLingJianInfoItem4);

    }

    @Override
    public void assignView() {
        tvChaijieRequest = (TextView) findViewById(R.id.tv_chaijie_request);
        lv = (ListView) findViewById(R.id.lv);

        mLingJianChaiJieInfoAdapter = new ListAdapterForChaiJieLingJianHasFinished(mChaiJieLingJianList,this);
        lv.setAdapter(mLingJianChaiJieInfoAdapter);

    }

    @Override
    public void initView() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
