package com.maochunjie.sensorsabtesting;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.facebook.react.bridge.ReadableMap;
import com.sensorsdata.abtest.SensorsABTest;
import com.sensorsdata.abtest.OnABTestReceivedData;
import com.sensorsdata.abtest.SensorsABTestConfigOptions;

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

    // 初始化神策 A/B Testing SDK
    @ReactMethod
    public void init(ReadableMap params, final Promise promise) {
        try {
            String serverUrl = params.getString("serverUrl");
            if (serverUrl != null && !serverUrl.isEmpty()) {
                Log.d("SensorsTesting", "Initializing SensorsABTest with server URL: " + serverUrl);
                // 创建配置选项
                SensorsABTestConfigOptions abTestConfigOptions = new SensorsABTestConfigOptions(serverUrl);
                // 初始化 A/B Testing SDK
                SensorsABTest.startWithConfigOptions(reactContext, abTestConfigOptions);
            }
            Log.d("SensorsTesting", "SensorsABTest initialized successfully.");
        } catch (Exception e) {
            Log.e("SensorsTesting", "Error initializing SensorsABTest", e);
        }
        promise.resolve("succeed");
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String fetchCacheABTest(final String experimentKey, final String defaultValue) {
        try {
            return SensorsABTest.shareInstance().fetchCacheABTest(experimentKey, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @ReactMethod
    public void asyncFetchABTest(final String experimentKey, final String defaultValue, final Promise promise) {
        try {
            SensorsABTest.shareInstance().asyncFetchABTest(experimentKey, defaultValue, new OnABTestReceivedData<String>() {
                @Override
                public void onResult(String result) {
                    promise.resolve(result);
                }
            });
        } catch (Exception e) {
            Log.d("SensorsTesting", "SensorsABTest" + e);
            promise.resolve(defaultValue);
        }
    }

    @ReactMethod
    public void fastFetchABTest(final String experimentKey, final String defaultValue, final Promise promise) {
        try {
            SensorsABTest.shareInstance().fastFetchABTest(experimentKey, defaultValue, new OnABTestReceivedData<String>() {
                @Override
                public void onResult(String result) {
                    promise.resolve(result);
                }
            });
        } catch (Exception e) {
            // 捕获异常并通过 Promise 返回错误信息
            Log.d("SensorsTesting", "SensorsABTest" + e);
            promise.resolve(defaultValue);
        }
    }
}
