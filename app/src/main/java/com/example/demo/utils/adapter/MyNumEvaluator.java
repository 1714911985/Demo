package com.example.demo.utils.adapter;

import android.animation.TypeEvaluator;

public class MyNumEvaluator implements TypeEvaluator<Integer> {


    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {

        return (int) (fraction * (endValue - startValue));
    }
}
