package com.example.demo.utils.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo.R;

public class CircleImage extends View {
    float radius;
    private Paint bgpaint;
    private Paint textpaint;
    private ImageView imageView;
    private AttributeSet attrs;
    private String text;
    private float size;
    private  Drawable drawable;

    public CircleImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //attrs = this.attrs;
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        radius = 300f;
        bgpaint = new Paint();

        bgpaint.setStyle(Paint.Style.FILL);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleImage);
        int color = typedArray.getColor(R.styleable.CircleImage_bgcolor, Color.GREEN);
        text = typedArray.getString(R.styleable.CircleImage_text);
        size = typedArray.getFloat(R.styleable.CircleImage_size, 20);
        drawable = typedArray.getDrawable(R.styleable.CircleImage_bgimg);
        bgpaint.setColor(color);

        textpaint = new Paint();
        textpaint.setColor(Color.BLACK);
        textpaint.setTextSize(size);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(radius, radius, radius, bgpaint);

        canvas.drawText(text, radius / 2, radius / 2, textpaint);

        if (drawable!=null){
            drawable.setBounds(0,0,getWidth(),getHeight());
            drawable.draw(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        radius = (float) MeasureSpec.getSize(widthMeasureSpec) / 2;
    }

}
