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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public abstract class EasyIntroSlidesInside extends EasyIntroFragment {
    // first slide is the main one
    private static List<Fragment> mSlidesInside = new ArrayList<>();
    private  int mCurrentSlideLocation = 0;
    private  boolean mFirstInit;

    public final void withSlide(Fragment fragment) {
        mSlidesInside.add(fragment);
    }

    public final void withBackMainSlide() {
        withReplaceSlide(mSlidesInside.get(mCurrentSlideLocation), mSlidesInside.get(0));
        mCurrentSlideLocation = 0;
    }

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!mFirstInit) {
            withMainSlide(this);
            init();
            mFirstInit = true;
        }
    }

    private void withMainSlide(Fragment fragment) {
        mSlidesInside.add(fragment);
    }

    protected abstract void init();

    /**
     * @param location Slide location inside the group
     *                 Note: Starts with 0 (main slide)
     */
    public final void withReplace(int location) {
        withReplaceSlide(mSlidesInside.get(mCurrentSlideLocation), mSlidesInside.get(location));
        mCurrentSlideLocation = location;
    }

    public final List<Fragment> getSlidesInside() {
        return mSlidesInside;
    }

    public static class EasyIntroSlidesInsideFragment extends EasyIntroSlidesInside {
        @Override
        protected void init() {
            // necessary, empty
        }
    }
}
