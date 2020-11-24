//
//  HCPEvents.m
//
//  Created by Nikolay Demyankov on 13.08.15.
//

#import "HCPEvents.h"

#pragma mark Event names declaration

NSString *const kHCPUpdateDownloadProgressEvent = @"chcp_updateLoadProgress";
NSString *const kHCPUpdateDownloadErrorEvent = @"chcp_updateLoadFailed";
NSString *const kHCPNothingToUpdateEvent = @"chcp_nothingToUpdate";
NSString *const kHCPUpdateIsReadyForInstallationEvent = @"chcp_updateIsReadyToInstall";
NSString *const kHCPBeforeInstallEvent = @"chcp_beforeInstall";
NSString *const kHCPUpdateInstallationErrorEvent = @"chcp_updateInstallFailed";
NSString *const kHCPUpdateIsInstalledEvent = @"chcp_updateInstalled";
NSString *const kHCPNothingToInstallEvent = @"chcp_nothingToInstall";
NSString *const kHCPBeforeBundleAssetsInstalledOnExternalStorageEvent = @"chcp_beforeAssetsInstalledOnExternalStorage";
NSString *const kHCPBundleAssetsInstalledOnExternalStorageEvent = @"chcp_assetsInstalledOnExternalStorage";
NSString *const kHCPBundleAssetsInstallationErrorEvent = @"chcp_assetsInstallationError";

NSString *const kHCPEventUserInfoErrorKey = @"error";
NSString *const kHCPEventUserInfoTaskIdKey = @"taskId";
NSString *const kHCPEventUserInfoTaskDetailKey = @"taskDetail";
NSString *const kHCPEventUserInfoApplicationConfigKey = @"appConfig";

@implementation HCPEvents

#pragma mark Public API

+ (NSNotification *)notificationWithName:(NSString *)name applicationConfig:(HCPApplicationConfig *)appConfig taskId:(NSString *)taskId {
    return [HCPEvents notificationWithName:name applicationConfig:appConfig taskId:taskId error:nil taskDetail:nil];
}
+ (NSNotification *)notificationWithName:(NSString *)name taskId:(NSString *)taskId taskDetail:(NSMutableDictionary*)taskDetail {
    return [HCPEvents notificationWithName:name applicationConfig:nil taskId:taskId error:nil taskDetail:taskDetail];
}
+ (NSNotification *)notificationWithName:(NSString *)name applicationConfig:(HCPApplicationConfig *)appConfig taskId:(NSString *)taskId error:(NSError *)error taskDetail:(NSDictionary *)taskDetail {
    NSMutableDictionary *userInfo = [[NSMutableDictionary alloc] init];
    if (appConfig) {
        userInfo[kHCPEventUserInfoApplicationConfigKey] = appConfig;
    }
    
    if (taskId) {
        userInfo[kHCPEventUserInfoTaskIdKey] = taskId;
    }
    
    if (error) {
        userInfo[kHCPEventUserInfoErrorKey] = error;
    }
    if(taskDetail)
    {
        userInfo[kHCPEventUserInfoTaskDetailKey] = taskDetail;
    }
    return [NSNotification notificationWithName:name object:nil userInfo:userInfo];
}

@end
