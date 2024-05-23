package com.example.demo.main.second.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.example.demo.R;
import com.example.demo.utils.adapter.MyTypeEvaluator;
import com.example.demo.utils.myview.MyView;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private AnimationDrawable animation;
    private Switch switchAnimaiton;
    private ImageView ivTweenedAnimationAlpha;
    private ImageView ivTweenedAnimationScale;
    private ImageView ivTweenedAnimationTranslate;
    private ImageView ivTweenedAnimationRotate;
    private ImageView imageView;
    private LottieAnimationView lottieAnimationView;
    private TextView tvType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initData();
        initView();

        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //switch控制帧动画开始停止
        switchAnimaiton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    animation.start();
                } else {
                    animation.stop();
                }
            }
        });


        //对象动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
        android.widget.Button button = findViewById(R.id.btn_object_animation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.setDuration(2000);
                animator.start();
            }
        });

        //值动画
        ValueAnimator animator1 = ValueAnimator.ofFloat(1f, 0f, 1f);
        animator1.setDuration(2000);
        android.widget.Button btnValueAnimation = findViewById(R.id.btn_value_animation);
        btnValueAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float currentValue = (float) animation.getAnimatedValue();
                        //Log.d("MainActivity", "cuurent value is " + currentValue);
                        imageView.setAlpha(currentValue);
                    }
                });
                animator1.start();
            }
        });

        //PropertyValueHolder可以让前面的一些动画同时执行
        PropertyValuesHolder alphaProper = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f);
        PropertyValuesHolder scaleXProper = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
        PropertyValuesHolder scaleYProper = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
        PropertyValuesHolder translationXProper = PropertyValuesHolder.ofFloat("translationX", -100, 100);
        PropertyValuesHolder translationYProper = PropertyValuesHolder.ofFloat("translationY", -100, 100);
        PropertyValuesHolder rotationProper = PropertyValuesHolder.ofFloat("rotation", 0, 360);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(imageView, alphaProper, scaleXProper, scaleYProper, translationXProper, translationYProper, rotationProper);
        android.widget.Button btnPropertyValueHolder = findViewById(R.id.btn_property_value_holder);
        btnPropertyValueHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator2.setDuration(5000);
                animator2.start();
            }
        });

        //动画组合
        //after(Animator anim) 将现有动画插入到传入的动画之后执行
        //after(long delay) 将现有动画延迟指定毫秒后执行
        //before(Animator anim) 将现有动画插入到传入的动画之前执行
        //with(Animator anim) 将现有动画和传入的动画同时执行
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(imageView, "translationX", -100, 100f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", -100, 100f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotate)
                .with(alpha)
                .after(scaleX)
                .before(translationX)
                .after(1000)
                .before(translationY)
                .with(scaleY);
        animatorSet.setDuration(5000);
        android.widget.Button btnAnimatorSet = findViewById(R.id.btn_animator_set);
        btnAnimatorSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorSet.start();
            }
        });

        //差值器
        //AccelerateInterpolator	加速查值器，参数越大，速度越来越快
        //DecelerateInterpolator	减速差值器，和加速查值器相反
        //AccelerateDecelerateInterpolator	先加速后减速
        //AnticipateInterpolator	先后退在加速前进
        //AnticipateOvershootInterpolator	以X/Y轴为轴的旋转度数
        //BounceInterpolator	弹球效果插值
        //CycleInterpolator	周期运动插值
        //LinearInterpolator	匀速插值
        //OvershootInterpolator	先快速完成动画，再回到结束样式
        animator.setInterpolator(new AccelerateInterpolator(5f));

        //估值器
        MyView myView = findViewById(R.id.myView);
        ObjectAnimator animator3 = ObjectAnimator.ofObject(myView, "color", new MyTypeEvaluator(), "#00ff00", "#ff0000");
        android.widget.Button btnTypeEvaluator = findViewById(R.id.btn_type_evaluator);
        btnTypeEvaluator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator3.setDuration(3000);
                animator3.start();
            }
        });

        //lottie
        lottieAnimationView.playAnimation();
    }

    private void initView() {
        toolbar = findViewById(R.id.tb_animation);
        ImageView ivFrameAnimation = findViewById(R.id.iv_frame_animation);
        animation = (AnimationDrawable) ivFrameAnimation.getDrawable();
        switchAnimaiton = findViewById(R.id.switch_animation);
        Button btnTweenedAnimationAlpha = findViewById(R.id.btn_tweened_animation_alpha);
        btnTweenedAnimationAlpha.setOnClickListener(this);
        Button btnTweenedAnimationScale = findViewById(R.id.btn_tweened_animation_scale);
        btnTweenedAnimationScale.setOnClickListener(this);
        Button btnTweenedAnimationTranslate = findViewById(R.id.btn_tweened_animation_translate);
        btnTweenedAnimationTranslate.setOnClickListener(this);
        Button btnTweenedAnimationRotate = findViewById(R.id.btn_tweened_animation_rotate);
        btnTweenedAnimationRotate.setOnClickListener(this);
        ivTweenedAnimationAlpha = findViewById(R.id.iv_tweened_animation_alpha);
        ivTweenedAnimationScale = findViewById(R.id.iv_tweened_animation_scale);
        ivTweenedAnimationTranslate = findViewById(R.id.iv_tweened_animation_translate);
        ivTweenedAnimationRotate = findViewById(R.id.iv_tweened_animation_rotate);

        imageView = findViewById(R.id.iv_img2);
        lottieAnimationView = findViewById(R.id.lav_heart);
        tvType = findViewById(R.id.tv_type_evaluator);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tweened_animation_alpha) {
            Animation alpha = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
            ivTweenedAnimationAlpha.startAnimation(alpha);
        } else if (v.getId() == R.id.btn_tweened_animation_scale) {
            Animation scale = AnimationUtils.loadAnimation(this, R.anim.animation_scale);
            ivTweenedAnimationScale.startAnimation(scale);
        } else if (v.getId() == R.id.btn_tweened_animation_translate) {
            Animation translate = AnimationUtils.loadAnimation(this, R.anim.animation_translate);
            ivTweenedAnimationTranslate.startAnimation(translate);
        } else if (v.getId() == R.id.btn_tweened_animation_rotate) {
            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
            ivTweenedAnimationRotate.startAnimation(rotate);
        }
    }
}