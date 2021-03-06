/*
 * Copyright 2016 Alireza Eskandarpour Shoferi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.meness.easyintro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

class EasyIntroPagerAdapter extends SmartFragmentStatePagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();

    public EasyIntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        if (!fragmentAlreadyAdded(fragment)) {
            mFragments.add(fragment);
            notifyDataSetChanged();
        }
    }

    public boolean fragmentAlreadyAdded(Fragment fragment) {
        return mFragments.indexOf(fragment) != -1;
    }

    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        int oldIndex = mFragments.indexOf(oldFragment);
        if (oldIndex != -1) {
            mFragments.set(oldIndex, newFragment);
            notifyDataSetChanged();
        }
    }

    public void removeFragment(Class<? extends Fragment> aClass) {
        for (Fragment fragment : mFragments) {
            if (fragment.getClass().equals(aClass)) {
                mFragments.remove(fragment);
                notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // only update changed fragments
        int position = mFragments.indexOf(object);

        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }
}
