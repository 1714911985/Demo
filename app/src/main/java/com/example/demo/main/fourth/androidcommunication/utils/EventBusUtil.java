package com.example.demo.main.fourth.androidcommunication.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: Eccentric
 * Created on 2024/5/22 18:25.
 * Description: com.example.demo.main.fourth.androidcommunication.utils.EventBusUtil
 */
public class EventBusUtil {
    public static void register(Object o) {
        if (!EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().register(o);
        }
    }

    public static void post(Object o) {
        EventBus.getDefault().post(o);
    }

    public static void unregister(Object o) {
        if (EventBus.getDefault().isRegistered(o)) {
            EventBus.getDefault().unregister(o);
        }
    }
}
