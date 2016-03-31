package com.commontime.cordova.plugins.duktape;

import org.apache.cordova.CordovaPlugin;

public class DuktapePlugin extends CordovaPlugin {

	private static final String TAG = "DuktapePlugin";

	@Override
	protected void pluginInitialize() {

        String filename = preferences.getString("duktape_includejs", "");

        if( !filename.isEmpty() ) {
            DuktapeEngine.get(cordova.getActivity()).setFile(filename);
        }

        // Test:
//        DuktapeEngine.get(cordova.getActivity()).execute("DuktapeResult.finish(duktapeResultId, generateToken('http://www.google.com', 'RootManageSharedAccessKey', '66zR0psl8bwbA/mkTEXEnVUiEBemg1Jp5/7n+AhBZkU='));", new DuktapeCallback() {
//			@Override
//			public void onFinished(String result) {
//				System.out.println(result);
//			}
//		});
	}



}


