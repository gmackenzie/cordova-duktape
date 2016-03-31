//
//  CTProvisioner.h
//  mDesign 10
//
//  Created by Gary Meehan on 18/03/2016.
//  Copyright (c) 2016 CommonTime Limited. All rights reserved.
//

#import <Cordova/CDV.h>

@interface CTProvisioner : CDVPlugin

- (void) installVersion: (CDVInvokedUrlCommand*) command;

- (void) getLatestVersionNumber: (CDVInvokedUrlCommand*) command;

- (void) getVersionsInfo: (CDVInvokedUrlCommand*) command;

- (void) getInstalledVersion: (CDVInvokedUrlCommand*) command;

- (void) restartApplication: (CDVInvokedUrlCommand*) command;

@end