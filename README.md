# react-native-sensors-abtesting

[![npm version](https://badge.fury.io/js/react-native-sensors-abtesting.svg)](https://badge.fury.io/js/react-native-sensors-abtesting)

iOS version：1.1.0

Android version：1.1.2

## 开始

`$ npm install react-native-sensors-abtesting --save`

### iOS

```sh
cd ios
pod install
```

### 接口

```javascript
import SensorsTesting from 'react-native-sensors-abtesting';

// 初始化神策AB SDK 时 需要先初始化神策 SDK
static initABTest = async (params) => {
  return await SensorsTesting.init(params);
};

// 从缓存中获取AB测试结果
static fetchCacheABTest = (key = '', defaultValue = 'default') => {
  return SensorsTesting.fetchCacheABTest(key, defaultValue);
};

// 从服务器获取AB测试结果
static asyncFetchABTest = async (key = '', defaultValue = 'default') => {
  return await SensorsTesting.asyncFetchABTest(key, defaultValue);
};

// 优先读取本地缓存，没有再从服务器获取AB测试结果
static fastFetchABTest = async (key = '', defaultValue = 'default') => {
  return await SensorsTesting.fastFetchABTest(key, defaultValue);
};
```
