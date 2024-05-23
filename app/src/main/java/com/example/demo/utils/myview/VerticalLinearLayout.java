package com.example.demo.utils.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class VerticalLinearLayout extends ViewGroup {

    public VerticalLinearLayout(Context context) {
        super(context);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = 0;
        int height = getPaddingTop() + getPaddingBottom();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec); // 测量子视图

            // 找出最宽的子视图
            int childWidth = child.getMeasuredWidth();
            if (childWidth > width) {
                width = childWidth;
            }

            // 累加子视图的高度（包含间距）
            height += child.getMeasuredHeight();
            if (i < childCount - 1) {
                // 添加间距（如果需要）
                height += getVerticalSpacing();
            }
        }

        // 考虑左右内边距
        width += getPaddingLeft() + getPaddingRight();

        // 设置ViewGroup的最终大小
        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    // 布局子视图
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft(); // 所有子视图的左边界
        int top = getPaddingTop(); // 第一个子视图的顶部边界

        // 遍历所有子视图
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            // 放置子视图
            child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());

            // 更新下一个子视图的顶部边界
            top += child.getMeasuredHeight();
            if (i < getChildCount() - 1) {
                // 添加间距（如果需要）
                top += getVerticalSpacing();
            }
        }
    }

    // 假设的方法，用于获取子视图之间的垂直间距
    private int getVerticalSpacing() {
        // 这里可以根据需要返回间距值，例如返回dp值转换为像素值
        return dpToPx(10);
    }

    // 将dp值转换为像素值
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

}