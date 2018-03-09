package cn.com.magicabc.ui.word;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.com.magicabc.BabyApplication;
import cn.com.magicabc.R;
import cn.com.magicabc.ui.activity.persenter.HomePresenter;
import cn.com.magicabc.ui.adapter.FragmentAdapter;
import cn.com.magicabc.ui.base.BaseActivity;
import cn.com.magicabc.ui.fragment.LocalMusicFragment;
import cn.com.magicabc.ui.fragment.OnlineMusicFragment;
import cn.com.magicabc.ui.main.MainFragment;
import cn.com.magicabc.util.PermissionListener;
import cn.com.magicabc.util.ToastUtils;


public class WordActivity extends BaseActivity implements PermissionListener {

    private static final String TAG = "main";


  @BindView(R.id.vp)
    ViewPager mVp;
    private LocalMusicFragment localMusicFragment;
    private OnlineMusicFragment onlineMusicFragment;

    @Inject HomePresenter homePersenter;

    @Override protected int getLayoutId() {
        return R.layout.activity_word;
    }

    @Override protected void afterCreate(Bundle savedInstanceState) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        localMusicFragment = new LocalMusicFragment();
        onlineMusicFragment = new OnlineMusicFragment();
        //  onlineMusicFragment.setPresenter(homePersenter);
     //   adapter.addFragment(localMusicFragment);
        MainFragment mainFragment = new MainFragment();
        adapter.addFragment(onlineMusicFragment);
mVp.setAdapter(adapter);

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},this);
        DaggerWordComponent.builder()
                .applicationComponent(BabyApplication.getApplication().getApplicationComponent())
                .wordModule(new WordModule(onlineMusicFragment))
                .build()
                .inject(this);

    }



    @Override
    public void onGranted() {
        //ToastUtils.showLong("kepyi");


    }

    @Override
    public void onDenied(List<String> deniedPermissions) {
        ToastUtils.showLong("wwww");

    }
}

//HttpFactory.getHttpApiSingleton()
//    .getCategoryData("Android", 10, 1)
//    .compose(new HttpTransformer<HttpResult<List<GankEntity>>, List<GankEntity>>())
//    .doOnSubscribe(new Action0() {
//      @Override public void call() {
//        showLoadingView();
//      }
//    })
//    .subscribe(new Subscriber<List<GankEntity>>() {
//      @Override public void onCompleted() {
//        LogUtils.d(TAG, "Completed");
//        showContentView();
//      }
//
//      @Override public void onError(Throwable e) {
//        LogUtils.d(TAG, "OnError, Error is " + e.toString());
//        showErrorView();
//      }
//
//      @Override public void onNext(List<GankEntity> gankEntities) {
//
//      }
//    });

