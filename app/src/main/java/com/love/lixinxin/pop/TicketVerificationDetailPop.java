package com.love.lixinxin.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * 核销确认弹窗
 *
 * @author yanghongtao
 */
public class TicketVerificationDetailPop extends PopupWindow {

    private Context mContext;

    private View view;

    private LinearLayout popLayout;

    int fromYDelta;
    private TicketVerificationResultCallback resultCallback;

    public TicketVerificationDetailPop(final Context mContext) {
        this.mContext = mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.ticket_verification_pop, null);
        popLayout = (LinearLayout) view.findViewById(R.id.pop_layout);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                fromYDelta = getViewMeasuredHeight(view);
                getContentView().startAnimation(createOutAnimation(mContext, fromYDelta));
            }
        });

		/* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        //  this.setAnimationStyle(R.style.take_photo_anim);
    }


    public void showPop(View v) {

        fromYDelta = getViewMeasuredHeight(view);
        this.showAsDropDown(v);
        this.getContentView().startAnimation(createInAnimation(mContext, -fromYDelta));
    }


    interface TicketVerificationResultCallback {
        void resultCallback();
    }

    //动画持续时间
    public final static int ANIMATION_IN_TIME = 500;
    public final static int ANIMATION_OUT_TIME = 500;

    public static Animation createInAnimation(Context context, int fromYDelta) {
        AnimationSet set = new AnimationSet(context, null);
        set.setFillAfter(true);

        TranslateAnimation animation = new TranslateAnimation(0, 0, fromYDelta, 0);
        animation.setDuration(ANIMATION_IN_TIME);
        set.addAnimation(animation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(ANIMATION_IN_TIME);
        set.addAnimation(alphaAnimation);
        return set;
    }

    public static Animation createOutAnimation(Context context, int toYDelta) {
        AnimationSet set = new AnimationSet(context, null);
        set.setFillAfter(true);

        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, toYDelta);
        animation.setDuration(ANIMATION_OUT_TIME);
        set.addAnimation(animation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(ANIMATION_OUT_TIME);
        set.addAnimation(alphaAnimation);
        return set;
    }


    /**
     * 获取控件的高度
     *
     * @param view
     * @return
     */

    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度
     */
    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     */
    private static void calculateViewMeasure(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(w, h);
    }

}
