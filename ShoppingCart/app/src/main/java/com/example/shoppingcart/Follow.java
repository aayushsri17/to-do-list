package com.example.shoppingcart;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Follow extends Activity implements OnClickListener{
Button b1,b2,b3,b4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_follow);
		 b1=(Button) findViewById(R.id.facebook);
	        b2=(Button) findViewById(R.id.twitter);
	        b3=(Button) findViewById(R.id.gmail);
	        b4=(Button) findViewById(R.id.linkedin);
	        b1.setOnClickListener(this);
			b2.setOnClickListener(this);
			b3.setOnClickListener(this);
			b4.setOnClickListener(this);

	}

	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.facebook:

			Intent ifacebook = new Intent(getApplicationContext(),
					Facebook.class);
			startActivity(ifacebook);

			break;

		case R.id.twitter:

			Intent itwitter = new Intent(getApplicationContext(), Twitter.class);
			startActivity(itwitter);
			break;

		case R.id.linkedin:

			Intent ilinkedin = new Intent(getApplicationContext(),
					Linkedin.class);
			startActivity(ilinkedin);
			break;

		case R.id.gmail:

			Intent igm = new Intent(getApplicationContext(),
					Gmail.class);
			startActivity(igm);
			break;

			}

		
	}
    
}

