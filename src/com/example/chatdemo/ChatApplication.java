package com.example.chatdemo;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient.ConnectCallback;
import android.app.Application;
import android.util.Log;

public class ChatApplication extends Application implements ConnectCallback {

	private static final String RONG_APP_ID = "25wehl3uwxhjw";
	public static final String TOKEN = "3DymAonuSAKwI3iriEM8i3mjzpgkvXe2FDiD0OGIsEc+hAcXswQvNhQTdVJfe0vOm3ZoUuFmByZPIk7o3tnEIg==";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		RongIM.init(getApplicationContext(), RONG_APP_ID,
				R.drawable.ic_launcher);
//		RongIM.connect(TOKEN, this);
	}

	@Override
	public void onError(ErrorCode arg0) {
		// TODO Auto-generated method stub
		Log.i("ChatApplication", "µÇÂ½Ê§°Ü:" + arg0);
	}

	@Override
	public void onSuccess(String arg0) {
		// TODO Auto-generated method stub
		Log.i("ChatApplication", "µÇÂ½³É¹¦:" + arg0);
	}

}
