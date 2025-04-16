#import <SensorsABTest.h>
#import "RNReactNativeSensorsTesting.h"

@implementation RNReactNativeSensorsTesting {
    bool hasListeners;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

RCT_EXPORT_MODULE()

// Will be called when this module's first listener is added.
- (void)startObserving {
    hasListeners = YES;
    // Set up any upstream listeners or background tasks as necessary
}

// Will be called when this module's last listener is removed, or on dealloc.
- (void)stopObserving {
    hasListeners = NO;
    // Remove upstream listeners, stop unnecessary background tasks
}

RCT_EXPORT_METHOD(init:(NSDictionary *)params resolve:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        NSString *requestURL = params[@"serverUrl"];

        if (requestURL && ![requestURL isEqual:@""]) {
            SensorsABTestConfigOptions *abtestConfigOptions = [[SensorsABTestConfigOptions alloc] initWithURL:requestURL];
            [SensorsABTest startWithConfigOptions:abtestConfigOptions];
        }
    } @catch (NSException *exception) {
        NSString *errorMessage = [NSString stringWithFormat:@"fail: %@", exception.reason];
        NSLog(@"%@", errorMessage);
    }
    resolve(@"succeed");
}

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(fetchCacheABTest:(NSString *)experimentKey defaultValue:(NSString *)defaultValue) {
    @try {
        return [[SensorsABTest sharedInstance] fetchCacheABTestWithParamName:experimentKey defaultValue:defaultValue];
    } @catch (NSException *exception) {
        return defaultValue;
    }
}

RCT_EXPORT_METHOD(asyncFetchABTest:(NSString *)experimentKey defaultValue:(NSString *)defaultValue resolve:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[SensorsABTest sharedInstance] asyncFetchABTestWithParamName:experimentKey
                                                         defaultValue:defaultValue
                                                    completionHandler:^(id _Nullable result) {
            resolve(result);
        }];
    } @catch (NSException *exception) {
        NSString *errorMessage = [NSString stringWithFormat:@"SensorsTesting: %@", exception.reason];
        NSLog(@"%@", errorMessage);
        resolve(defaultValue);
    }
}

RCT_EXPORT_METHOD(fastFetchABTest:(NSString *)experimentKey defaultValue:(NSString *)defaultValue resolve:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    @try {
        [[SensorsABTest sharedInstance] fastFetchABTestWithParamName:experimentKey
                                                        defaultValue:defaultValue
                                                   completionHandler:^(id _Nullable result) {
            resolve(result);
        }];
    } @catch (NSException *exception) {
        NSString *errorMessage = [NSString stringWithFormat:@"SensorsTesting: %@", exception.reason];
        NSLog(@"%@", errorMessage);
        resolve(defaultValue);
    }
}

@end
