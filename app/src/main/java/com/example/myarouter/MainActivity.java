package com.example.myarouter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.lib_base.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mMViewPager;
    private TabLayout mToolbarTab;
    /**
     * 图标
     */
    private int[] tabIcons = {
            R.mipmap.tab_home,
            R.mipmap.tab_weichat,
            R.mipmap.tab_recommend,
            R.mipmap.tab_user
    };
    private DemandAdapter mDemandAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        setViewPagerAdapter();
        setTabBindViewPager();
        //跳转到我的模块登录页面
//        ARouter.getInstance().build(RouteUtils.Me_Login).navigation();
    }

    private void initData() {
        fragments.clear();
        fragments.add(FragmentUtils.getHomeFragment());
        fragments.add(FragmentUtils.getChatFragment());
        fragments.add(FragmentUtils.getRecomFragment());
        fragments.add(FragmentUtils.getMeFragment());
    }

    private void initView() {
        mMViewPager = findViewById(R.id.mViewPager);
        mToolbarTab = findViewById(R.id.toolbar_tab);

    }

    private void setViewPagerAdapter() {
        mDemandAdapter = new DemandAdapter(getSupportFragmentManager(), fragments);
        mMViewPager.setAdapter(mDemandAdapter);
        setItem();
    }

    private void setTabBindViewPager() {
        mToolbarTab.setupWithViewPager(mMViewPager);
    }

    private void setItem() {
        /**
         * 一定要在设置适配器之后设置Icon
         */
        for (int i = 0; i < mToolbarTab.getTabCount(); i++) {
            mToolbarTab.getTabAt(i).setCustomView(getTabView(i));
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        ImageView tab_image = view.findViewById(R.id.tab_image);
        tab_image.setImageResource(tabIcons[position]);
        return view;
    }

    public class DemandAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mList;

        public DemandAdapter(FragmentManager fm) {
            super(fm);
        }

        public DemandAdapter(FragmentManager fm, List<Fragment> list) {
            this(fm);
            // TODO Auto-generated constructor stub
            mList = list;
        }

        /*
         * 生成新的 Fragment 对象。 .instantiateItem() 在大多数情况下，都将调用 getItem() 来生成新的对象
         */
        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            return mList.get(position);
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }
    }
}
