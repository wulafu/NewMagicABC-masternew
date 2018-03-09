package cn.com.magicabc.ui.login;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.magicabc.BabyApplication;
import cn.com.magicabc.R;
import cn.com.magicabc.ui.base.BaseActivity;
import cn.com.magicabc.ui.login.component.DaggerLoginComponent;
import cn.com.magicabc.ui.login.contract.LoginContract;
import cn.com.magicabc.ui.login.module.LoginModule;
import cn.com.magicabc.ui.login.persenter.LoginPresenter;
import cn.com.magicabc.util.ToastUtils;
import cn.com.magicabc.widget.ClearEditText;

import static cn.com.magicabc.R.id.tv_title;

/**
 * Created by hellohome on 18/3/6.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(tv_title)
    TextView mTvTitle;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.et_phoneNum)
    ClearEditText mEtPhoneNum;
    @BindView(R.id.et_password)
    ClearEditText mEtPassword;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @Inject
    LoginPresenter mLoginPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mTvTitle.setText("登录");
        DaggerLoginComponent.builder()
                .applicationComponent(BabyApplication.getApplication().getApplicationComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @OnClick({R.id.iv_back,R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_login:
mLoginPresenter.onLogin();
                break;

        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
      //  mLoginPresenter=presenter;

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void loginSucess() {

        ToastUtils.showLong("登录成功");

    }

    @Override
    public void loginError(String message) {

    }
}
