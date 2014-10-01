package com.example.chatdemo;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.GetFriendsProvider;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.UserInfo;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements ConnectCallback {

	private Handler mHandler;
	private int m=0;
	public static final String TOKEN2 = "CLXVGRzOMvL6UjBafZazbFy39VNgOXFxwoVjnEaIg+rPDcVsVH8XkakksHVsFm5ci14DNAwxk0g=";
	public static final String TOKEN10 = "3DymAonuSAKwI3iriEM8i3mjzpgkvXe2FDiD0OGIsEc+hAcXswQvNhQTdVJfe0vOm3ZoUuFmByZPIk7o3tnEIg==";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHandler = new Handler();
		
		RongIM.setGetFriendsProvider(new GetFriendsProvider() {
			
			@Override
			public List<UserInfo> getFriends() {
				// TODO Auto-generated method stub
				UserInfo userInfo=new UserInfo("10", "chc", "http://www.apkbus.com/design/style/devices_displays_density@2x.png");
				UserInfo userInfo2=new UserInfo("2", "ling", "http://www.apkbus.com/design/style/typography_main.png");
				List<UserInfo> list=new ArrayList<>();
				list.add(userInfo);
				list.add(userInfo2);
				return list;
			}
		}) ;
		findViewById(R.id.enter).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (m==0) {
							String userId = "10";
							String title = "chc";
							RongIM.getInstance().startPrivateChat(
									MainActivity.this, userId, title);
						}else if (m==1) {
							String userId = "2";
							String title = "ling";
							RongIM.getInstance().startPrivateChat(
									MainActivity.this, userId, title);
						}
						
					}
				});

			}
		});
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RongIM.connect(TOKEN2, MainActivity.this);
				m=0;
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RongIM.connect(TOKEN10, MainActivity.this);
				m=1;
			}
		});
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		RongIM.getInstance().disconnect();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onError(ErrorCode arg0) {
		// TODO Auto-generated method stub
		Log.i("MainActivity", "µÇÂ½Ê§°Ü:" + arg0);
	}

	@Override
	public void onSuccess(String arg0) {
		// TODO Auto-generated method stub
		Log.i("MainActivity", "µÇÂ½³É¹¦:" + arg0);
	}
}
