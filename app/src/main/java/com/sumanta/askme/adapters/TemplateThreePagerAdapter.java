package com.sumanta.askme.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.sumanta.askme.fragment.PagerFragment;
import com.sumanta.askme.entities.ItemEntity;
import java.util.ArrayList;

/**
 * Created by sumanta on 6/3/16.
 */
public class TemplateThreePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ItemEntity> itemList;
    public TemplateThreePagerAdapter(FragmentManager fm, ArrayList<ItemEntity> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(itemList.get(position));
    }

    @Override
    public int getCount() {
        return itemList.size();
    }
}