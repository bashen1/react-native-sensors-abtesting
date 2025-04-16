export default class SensorsTesting {
    /**
     * 初始化 SDK
     */
    static init(params: { serverUrl: string }): Promise<string>;

    /**
     * 读取本地缓存，缓存不存在时使用默认值
     */
    static fetchCacheABTest(experimentKey: string, defaultValue: string): string;

    /**
     * 忽略本地缓存，从服务端获取数据
     */
    static asyncFetchABTest(experimentKey: string, defaultValue: string): Promise<string>;

    /**
     * 优先读取本地缓存，缓存不存在时从服务端获取数据
     */
    static fastFetchABTest(experimentKey: string, defaultValue: string): Promise<string>;
}
