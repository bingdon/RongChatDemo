package com.example.chatdemo;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM.GetFriendsProvider;
import io.rong.imkit.RongIM.GetGroupInfoProvider;
import io.rong.imkit.RongIM.OperationCallback.ErrorCode;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.Group;
import io.rong.imlib.RongIMClient.UserInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements ConnectCallback {

	private static final String TAG=MainActivity.class.getSimpleName();
	private Handler mHandler;
	private int m = 0;
	public static final String TOKEN2 = "CLXVGRzOMvL6UjBafZazbFy39VNgOXFxwoVjnEaIg+rPDcVsVH8XkakksHVsFm5ci14DNAwxk0g=";
	public static final String TOKEN10 = "3DymAonuSAKwI3iriEM8i3mjzpgkvXe2FDiD0OGIsEc+hAcXswQvNhQTdVJfe0vOm3ZoUuFmByZPIk7o3tnEIg==";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHandler = new Handler();

		initData();

		findViewById(R.id.enter).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (m == 0) {
							String userId = "10";
							String title = "chc";
							RongIM.getInstance().startPrivateChat(
									MainActivity.this, userId, title);
						} else if (m == 1) {
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
				m = 0;
			}
		});

		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RongIM.connect(TOKEN10, MainActivity.this);
				m = 1;
			}
		});

		findViewById(R.id.chatlist).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RongIM.getInstance().startConversationList(MainActivity.this);
			}
		});
		
		findViewById(R.id.custom).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, ChatActivity.class));
			}
		});
		

		findViewById(R.id.addgroup).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initGroup();
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
		Log.i("MainActivity", "登陆失败:" + arg0);
	}

	@Override
	public void onSuccess(String arg0) {
		// TODO Auto-generated method stub
		Log.i("MainActivity", "登陆成功:" + arg0);
	}
	
	
	private void initData(){
		RongIM.setGetFriendsProvider(new GetFriendsProvider() {

			@Override
			public List<UserInfo> getFriends() {
				// TODO Auto-generated method stub
				UserInfo userInfo = new UserInfo("10", "chc",
						"http://www.apkbus.com/design/style/devices_displays_density@2x.png");
				UserInfo userInfo2 = new UserInfo("2", "ling",
						"http://www.apkbus.com/design/style/typography_main.png");
				List<UserInfo> list = new ArrayList<>();
				list.add(userInfo);
				list.add(userInfo2);
				return list;
			}
		});

		RongIM.setGetGroupInfoProvider(new GetGroupInfoProvider() {

			@Override
			public Group getGroupInfo(String arg0) {
				// TODO Auto-generated method stub
				Group group = new Group("1", "lbc",
						"http://developer.android.com/assets/images/dac_logo.png");
				return group;
			}
		});
		
	}
	
	private void initGroup(){
		List<Group> groups=new ArrayList<>();
		Group group = new Group("1", "lbc",
				"http://developer.android.com/assets/images/dac_logo.png");
		groups.add(group);
		RongIM.getInstance().syncGroup(groups,  new RongIM.OperationCallback() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Log.i(TAG, "返回成功");
			}
			
			@Override
			public void onError(ErrorCode arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "返回失败:"+arg0);
			}
		});
		RongIM.getInstance().joinGroup("1", "lbc", new RongIM.OperationCallback() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Log.i(TAG, "返回成功");
			}
			
			@Override
			public void onError(ErrorCode arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "返回失败:"+arg0);
			}
		});
	}
	
	
}
