package com.soubu.best_fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * 主页卡基类-不可滑动
 * <p>
 * 作者：余天然 on 2017/3/13 下午4:09
 */
public abstract class TabActivity extends AppCompatActivity {

    private static String CUR_TAB = "CUR_TAB";//缓存的key
    private int curTab;//当前的tab的位置
    private int tabNum = 4;//tab的个数

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CUR_TAB, curTab);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (savedInstanceState != null) {
            curTab = savedInstanceState.getInt(CUR_TAB, 0);
        }
        tabNum = getFragmentSize();
        initButton();
        switchTab(curTab);
    }

    protected abstract void initButton();

    protected void switchTab(int pos) {
        switchButton(pos);
        switchFragment(pos);
        curTab = pos;
    }

    protected abstract void switchButton(int pos);

    private void switchFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < tabNum; i++) {
            Fragment fragment = getFragmentWithCache(i);
            if (i == index) {
                if (fragment.isAdded()) {
                    transaction = transaction.show(fragment);
                } else {
                    transaction = transaction.add(getFragmentViewId(), fragment, String.valueOf(i));
                }
            } else {
                if (fragment.isAdded()) {
                    transaction = transaction.hide(fragment);
                }
            }
        }
        transaction.commit();
    }

    private Fragment getFragmentWithCache(int pos) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(pos));
        return fragment == null ? getFragment(pos) : fragment;
    }

    protected abstract int getContentViewId();

    protected abstract int getFragmentViewId();

    protected abstract int getFragmentSize();

    protected abstract Fragment getFragment(int pos);

}
