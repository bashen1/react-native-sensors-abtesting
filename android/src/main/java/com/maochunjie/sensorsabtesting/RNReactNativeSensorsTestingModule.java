package com.maochunjie.sensorsabtesting;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class RNReactNativeSensorsTestingModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public RNReactNativeSensorsTestingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNReactNativeSensorsTesting";
    }

    @ReactMethod
    public void init(final ReadableMap data, final Promise p) {
    }
}
