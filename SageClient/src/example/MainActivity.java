package example;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.sage.client.AsyncHelper;
import com.sage.client.RestClient;
import com.sage.client.RestRequest;
import com.sageclient.example.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadDataFromNet();
	}

	private void loadDataFromNet() {
		final int lastIndex = 1;
		final int singleCount = 1;

		new AsyncHelper<UserDTO>() {
			@Override
			protected UserDTO await() {
				RestRequest request = new RestRequest();
				request.Url = "http://182.92.4.28/articlehandler.ashx";
				request.GetParamDic.add(new BasicNameValuePair("in", Integer
						.toString(lastIndex)));
				request.PostParamDic.add(new BasicNameValuePair("rn", Integer
						.toString(singleCount)));
				return RestClient.GetDtoFromServer(request, UserDTO.class);
			}

			@Override
			protected void async(UserDTO result) {

			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
