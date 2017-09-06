package com.masker.discover.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import com.github.clans.fab.FloatingActionMenu;
import com.orhanobut.logger.Logger;

/**
 * Author: masker
 * Date: 2017/8/9
 * Description:
 */

public class FloatingActionMenuBehavior extends CoordinatorLayout.Behavior<FloatingActionMenu> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;
    private boolean mIsAnimatingIn = false;

    public FloatingActionMenuBehavior(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionMenu child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionMenu child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionMenu child, View directTargetChild, View target, int nestedScrollAxes) {
        Logger.i("==! "+(nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL));
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, final FloatingActionMenu child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        Logger.i("dxConsumed = "+dxConsumed+"  ; dyConsumed = "+dyConsumed);
        if (dyConsumed > 10 && !this.mIsAnimatingOut && !this.mIsAnimatingIn && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            if (child.isOpened()) {
                child.close(true);
            }
            animateOut(child);
        } else if (dyConsumed < -10 && !this.mIsAnimatingOut && !this.mIsAnimatingIn && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child);
        }
    }

    private void animateOut(final FloatingActionMenu menu) {
        menu.animate().translationYBy(200).withLayer()
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mIsAnimatingOut = false;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mIsAnimatingOut = false;
                        menu.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void animateIn(FloatingActionMenu menu) {
        menu.setVisibility(View.VISIBLE);
        menu.animate().translationYBy(-200).withLayer()
                .setListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                        mIsAnimatingIn = true;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mIsAnimatingIn = false;
                    }


                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mIsAnimatingIn = false;
                    }
                });

    }
}