import { NativeModules } from 'react-native';

const { RNReactNativeSensorsTesting } = NativeModules;

class SensorsTesting {
    /**
     * 初始化
     */
    static init = async (params) => {
        return await RNReactNativeSensorsTesting.init(params ?? {});
    }
    /**
     * 调用 A/B 测试并获取结果
     */
    static fetchCacheABTest = (experimentKey = '', defaultValue = '0') => {
        try {
            return RNReactNativeSensorsTesting.fetchCacheABTest(experimentKey, defaultValue);
        } catch (error) {
            return defaultValue;
        }
    }
    /**
    * 调用 A/B 测试并获取结果
    */
    static asyncFetchABTest = async (experimentKey = '', defaultValue = '0') => {
        try {
            return await RNReactNativeSensorsTesting.asyncFetchABTest(experimentKey, defaultValue);
        } catch (error) {
            return defaultValue;
        }
    }
    /**
     * 调用 A/B 测试并获取结果
     */
    static fastFetchABTest = async (experimentKey = '', defaultValue = '0') => {
        try {
            return await RNReactNativeSensorsTesting.fastFetchABTest(experimentKey, defaultValue);
        } catch (error) {
            return defaultValue;
        }
    }
}

export default SensorsTesting;
