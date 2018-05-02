package android.sgz.com.adapter;

import android.sgz.com.fragment.TechnologyLearnFragment;
import android.sgz.com.fragment.TenderInfomationFragment;
import android.sgz.com.fragment.WorkAttendanceFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by WD on 2018/4/29.
 */

public class FirstFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitle = new String[]{"工作考勤", "标书信息", "技术学习"};

    public FirstFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WorkAttendanceFragment();
        } else if (position == 1) {
            return new TenderInfomationFragment();
        } else {
            return new TechnologyLearnFragment();
        }
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
