package com.soubu.best_fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.soubu.best_fragment.base.TabActivity;
import com.soubu.best_fragment.fragment.ChatFragment;
import com.soubu.best_fragment.fragment.FindFragment;
import com.soubu.best_fragment.fragment.HomeFragment;
import com.soubu.best_fragment.fragment.MineFragment;

public class MainActivity extends TabActivity {

    private TextView[] tabs;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentViewId() {
        return R.id.fr_fragment;
    }

    @Override
    protected int getFragmentSize() {
        return 4;
    }

    @Override
    protected Fragment getFragment(int pos) {
        switch (pos) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ChatFragment();
            case 2:
                return new FindFragment();
            case 3:
                return new MineFragment();
        }
        return null;
    }

    @Override
    protected void initButton() {
        TextView tvHome = (TextView) findViewById(R.id.tv_home);
        TextView tvChat = (TextView) findViewById(R.id.tv_chat);
        TextView tvFind = (TextView) findViewById(R.id.tv_find);
        TextView tvMine = (TextView) findViewById(R.id.tv_mine);
        tabs = new TextView[]{tvHome, tvChat, tvFind, tvMine};

        for (int i = 0; i < tabs.length; i++) {
            final int finalI = i;
            tabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchTab(finalI);
                }
            });
        }
    }

    @Override
    protected void switchButton(int pos) {
        for (int i = 0; i < tabs.length; i++) {
            if (i == pos) {
                tabs[i].setSelected(true);
            } else {
                tabs[i].setSelected(false);
            }
        }
    }

}
