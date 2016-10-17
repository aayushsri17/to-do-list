package com.example.shoppingcart;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener
{
	private ArrayList<String> listOfItems;
	private ShoppingList myShoppingList;
	private DataBaseHelper myDbHelper;
	Menu Feedback,Share,follows,Exit;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
Feedback=(Menu)findViewById(R.id.instr);
		
		
		Share=(Menu)findViewById(R.id.share);
		Exit=(Menu)findViewById(R.id.exit);
		
		follows=(Menu)findViewById(R.id.follows);
		
		createStartupLogo(); 
		        
        // Creates database on startup
        DataBaseHelper myDbHelper = new DataBaseHelper(this);              
        myDbHelper.createDatabase();	          
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}


	/**
	 * Method for creating a store
	 * Go to create a store activity upon button press
	 * @param v view
	 */
	public void createStore(View v) 
	{		
		startActivity(new Intent(MainActivity.this, CreateStoreActivity.class));                               
	}	
	

	/**
	 * Method for managing store
	 * Go to manage store activity upon button press
	 * @param v view
	 */
	public void manageStores(View v)
	{
		startActivity(new Intent(MainActivity.this, ListOfStoresActivity.class));  		
	}
	
	/**
	 * Method for managing store
	 * Go to manage store activity upon button press
	 * @param v view
	 */
	public void importExportList(View v)
	{
		startActivity(new Intent(MainActivity.this, ManageShoppingListItemsActivity.class));  		
	}
	
	
	/**
	 * Method for creating a shopping list
	 * Go to create shopping list activity upon button press
	 * @param v view
	 */
	public void createList(View v)
	{
		startActivity(new Intent(MainActivity.this, CreateShoppingListActivity.class));
	}
	
	
	/**
	 * Method for managing a shopping list
	 * Go to manage shopping list activity upon button press
	 * @param v view
	 */
	public void manageList(View v)
	{
		startActivity(new Intent(MainActivity.this, ManageShoppingListActivity.class)); 
		finish();
	}
	
	
	/**
	 * Method handles creating the logo
	 */
	public void createStartupLogo()
	{
        ImageView cartImgView = (ImageView) findViewById(R.id.shoppingCartImgView);        
        int density= getResources().getDisplayMetrics().densityDpi;
        	       
        // Below if statements handle resizing image depending on screen device
        if  (density == DisplayMetrics.DENSITY_LOW)
        {
        	cartImgView.setImageResource(R.drawable.shopping_cart_img);        	
        	cartImgView.setVisibility(View.GONE); 
        }
        
        else if  (density == DisplayMetrics.DENSITY_MEDIUM)
        {
        	cartImgView.setImageResource(R.drawable.shopping_cart_img);
        	scaleImage(cartImgView, 100);
        }
       
        else if  (density == DisplayMetrics.DENSITY_HIGH)
        {
        	cartImgView.setImageResource(R.drawable.shopping_cart_img);
        	scaleImage(cartImgView, 200);
        }
       
        else if  (density == DisplayMetrics.DENSITY_XHIGH)
        {
        	cartImgView.setImageResource(R.drawable.shopping_cart_img);
        	 scaleImage(cartImgView, 100);
        } 
	}
	
	/**
	 * Method handles scaling the image shown on the home screen
	 * @param view
	 * @param boundBoxInDp
	 * Source: http://argillander.wordpress.com/2011/11/24/scale-image-into-imageview-then-resize-imageview-to-match-the-image/
	 */
	private void scaleImage(ImageView view, int boundBoxInDp)
	{
	    // Get the ImageView and its bitmap
	    Drawable drawing = view.getDrawable();
	    Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

	    // Get current dimensions
	    int width = bitmap.getWidth();
	    int height = bitmap.getHeight();

	    // Determine how much to scale: the dimension requiring less scaling is
	    // closer to the its side. This way the image always stays inside your
	    // bounding box AND either x/y axis touches it.
	    float xScale = ((float) boundBoxInDp) / width;
	    float yScale = ((float) boundBoxInDp) / height;
	    float scale = (xScale <= yScale) ? xScale : yScale;

	    // Create a matrix for the scaling and add the scaling data
	    Matrix matrix = new Matrix();
	    matrix.postScale(scale, scale);

	    // Create a new bitmap and convert it to a format understood by the ImageView
	    Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	    BitmapDrawable result = new BitmapDrawable(scaledBitmap);
	    width = scaledBitmap.getWidth();
	    height = scaledBitmap.getHeight();

	    // Apply the scaled bitmap
	    view.setImageDrawable(result);

	    // Now change ImageView's dimensions to match the scaled image
	    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
	    params.width = width;
	    params.height = height;
	    view.setLayoutParams(params);
	}

	/**
	 * Method converts dp to pixels
	 * @param dp
	 * @return
	 * Source: http://argillander.wordpress.com/2011/11/24/scale-image-into-imageview-then-resize-imageview-to-match-the-image/
	 */
	private int dpToPx(int dp)
	{
	    float density = getApplicationContext().getResources().getDisplayMetrics().density;
	    return Math.round((float)dp * density);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
switch (item.getItemId()) {
		
		 case R.id.instr:
			Intent in=new Intent(getApplicationContext(),Feedback.class);
			startActivity(in);
			break;
		
		case R.id.share:
			Intent shareIntent=new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT," ");
			shareIntent.putExtra(Intent.EXTRA_TEXT," ");
			startActivity(Intent.createChooser(shareIntent, "share Via"));
			break;
			
		case R.id.follows:
			Intent iu=new Intent(getApplicationContext(),Follow.class);
			startActivity(iu);
			break; 
		
		case R.id.exit:
			AlertDialog.Builder adb =new AlertDialog.Builder(this);
		
			adb.setTitle("Exit");
			
			adb.setMessage("Are you  sure want to exit");
			adb.setPositiveButton("yes",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
					
				Intent intent=new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
				int pid=android.os.Process.myPid();
				android.os.Process.killProcess(pid);
					
					
				}
			});
			adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),"continue",2000).show();
					
					
					
				}
			});
			adb.show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
		
	}	
}