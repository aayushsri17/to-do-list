package com.example.shoppingcart;





import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Feedback extends Activity {

	EditText name, email, message, feedback_message;
	
	ProgressDialog pDialog;
	Spinner category;
	Button sendfeedback;
	String complainttype[] = { "Personal", "Office"};
	private static String url_all_products = "http://192.168.93.1/feedback/new_category.php";
	private static final String TAG_SUCCESS = "success";
	JSONArray profile = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		category = (Spinner) findViewById(R.id.category);
		name = (EditText) findViewById(R.id.fullname);
		email = (EditText) findViewById(R.id.email);
		message = (EditText) findViewById(R.id.feedbackmessage);
		sendfeedback = (Button) findViewById(R.id.feedback);
		ArrayAdapter<String> adp = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_dropdown_item_1line, complainttype);
		category.setAdapter(adp);

		sendfeedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new CreateNewProfile().execute();

			}
		});
	}

	

class CreateNewProfile extends AsyncTask<String, String, String> {

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(Feedback.this);
		pDialog.setMessage("submit feedback..");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}

	@Override
	protected String doInBackground(String... arg0) {
		String fullname = name.getText().toString();
		String useremail = email.getText().toString();
		String category1 = category.getSelectedItem().toString();
		String feedbackmessage = message.getText().toString();

		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("name", fullname));
		params.add(new BasicNameValuePair("email", useremail));

		params.add(new BasicNameValuePair("category1", category1));
		params.add(new BasicNameValuePair("feedback_message",
				feedbackmessage));
		// params.add(new BasicNameValuePair("correct_answer",
		// correct_answer));

		// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json = Jsonparser.makeHttpRequest(url_all_products,
				"POST", params);

		// check log cat fro response
		Log.d("Create Response", json.toString());

		// check for success tag
		try {
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				// successfully created product
				Intent i = new Intent(getApplicationContext(),
						Feedbaccksecond.class);
				startActivity(i);

			} else {
				// failed to create product
				Toast.makeText(getApplicationContext(), "not sumitted",
						Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		// super.onPostExecute(result);
		pDialog.dismiss();
	}

}



}
