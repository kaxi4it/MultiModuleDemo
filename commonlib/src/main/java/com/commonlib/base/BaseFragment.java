package com.commonlib.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commonlib.R;
import com.commonlib.utils.LogUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpParams;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 *
 */
public abstract class BaseFragment extends Fragment {
    private Context mContext;
    private SweetAlertDialog sweetAlertDialog;
    public LayoutInflater mInflater;
    public Activity mActivity;
    protected String TAG = this.getClass().getSimpleName();
    protected HttpParams mHttpParams;

    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(TAG, "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mContext = getActivity();
//        TAG=this.getClass().getSimpleName();
        mHttpParams = new HttpParams();
        hasCreateView = false;
        isFragmentVisible = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            mInflater = inflater;
            LogUtils.d(TAG, TAG + "is created in " + mActivity.getClass().getSimpleName());
            rootView = inflater.inflate(getContentViewId(), container, false);
            //View view = initXml(inflater, container, savedInstanceState);
            ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        LogUtils.d(TAG, "onFragmentVisibleChange -> isVisible: " + isVisible);
    }

    public abstract int getContentViewId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariable();
        processLogic(savedInstanceState);
//        initListener();
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(TAG, TAG + "is destroy in " + mActivity.getClass().getSimpleName());
        super.onDestroyView();
        ButterKnife.unbind(this);
        System.gc();
    }


    /**
     * 初始化布局;
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
//    protected abstract View initXml(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化变量;
     */
    protected abstract void initVariable();

    /**
     * 初始化数据,添加逻辑;
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 初始化事件;
     */
//    protected abstract void initListener();

    /**
     * 调用接口时，显示dialog
     */
    public void showDialog() {
        showDialog(mContext.getResources().getString(R.string.loading), R.color.colorPrimary);
    }

    public void showDialog(String content, int colorRes) {
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(mContext, sweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.getProgressHelper().setBarColor(colorRes);
            sweetAlertDialog.setTitleText(content);
            sweetAlertDialog.setCancelable(true);
            sweetAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    OkHttpUtils.getInstance().cancelTag(BaseConfig.httpTag);
                }
            });
            if (!sweetAlertDialog.isShowing()) {
                sweetAlertDialog.show();
            }
        }
    }

    /**
     * 处理接口返回时，隐藏dialog
     */
    public void dismissDialog() {
        if (sweetAlertDialog != null) {
            sweetAlertDialog.dismiss();
            sweetAlertDialog = null;
        }
    }

    /**
     * 简化findviewbyid;
     *
     * @param id
     * @param view
     * @param <T>
     * @return
     */
//    public <T extends View> T generateView(int id, View view) {
//        return (T) view.findViewById(id);
//    }

    /**
     * * Fragment切换
     *
     * @Params toFragment 将要切换到的Fragment
     * resId      装载Fragment的view Id
     * index      Fragment的标识index
     * toleft     判断Fragment向左切换还是向右切换，以采用不同的动画
     * Notes:  R.anim.push_left_in等均为简单的Tranlate动画
     * mCurrentFragment为当前所在的Fragment，继承自BaseFragment
     */
    protected void switchDiffFragmentContent
    (Fragment mCurrentFragment, Fragment toFragment, int resId, int index, boolean toleft) {
        if (null == mCurrentFragment || null == toFragment) {
            return;
        }
        if (mCurrentFragment.getArguments().getInt("Index") !=
                toFragment.getArguments().getInt("Index")) {
            FragmentTransaction fragmentTransaction;
            fragmentTransaction = getChildFragmentManager().beginTransaction();
//            if (toleft)
//            {
//                fragmentTrasaction.SetCustomAnimations(R.anim.push_left_in,
//                        R.anim.push_left_out);
//            }else{
//                fragmentTrasaction.setCustomAnimations(R.anim.push_right_in,
//                        R.anim.push_right_out);
//            }
            //先判断是否添加过
            if (!toFragment.isAdded()) {
                //隐藏当前fragment,add下一个fragment
                fragmentTransaction.hide(mCurrentFragment);
                fragmentTransaction.add(resId, toFragment, String.valueOf(index));
                fragmentTransaction.commit();
            } else {
                //隐藏当前fragment，show下一个fragment
                fragmentTransaction.hide(mCurrentFragment);
                fragmentTransaction.show(toFragment);
                fragmentTransaction.commit();
            }
            mCurrentFragment = (BaseFragment) toFragment;
        }
    }

    /**
     * fragment跳转
     *
     * @param containerViewId 替换的布局
     * @param fragment        跳转的fragment
     * @param bundle          参数
     * @param isBack          是否可以返回到当前页
     */
    protected void startFragment(int containerViewId, Fragment fragment, Bundle bundle, boolean isBack) {
        fragment.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment);
        if (isBack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
    }

    /**
     * fragment跳转
     *
     * @param containerViewId 替换的布局
     * @param fragment        跳转的fragment
     * @param isBack          是否可以返回到当前页
     */
    protected void startFragment(int containerViewId, Fragment fragment, boolean isBack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment);
        if (isBack) {
            ft.addToBackStack(fragment.getClass().getName());
        }
        ft.commit();
    }

    /**
     * fragment切换
     *
     * @param containerViewId
     * @param from
     * @param to
     * @param isback
     */
    public void switchFragment(int containerViewId, Fragment from, Fragment to, boolean isback) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerViewId, to);
        if (isback) {
            ft.addToBackStack(to.getClass().getName());
        }
        ft.commit();
    }

    /**
     * 内嵌fragment的切换
     *
     * @param containerViewId
     * @param from
     * @param to
     */
    protected void switchChildFragment(int containerViewId, Fragment from, Fragment to, boolean isback) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.hide(from);
        ft.replace(containerViewId, to);
        if (isback) {
            ft.addToBackStack(to.getClass().getName());
        }
        ft.commit();
    }
}
