package com.example.eightpuzzle;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity
{
private TextView whatis8puzzle,tv_count;
private Button button_submitSolveAI,button_nextPuzzle;
private GridView gridView_PuzzleImage;
private int countMovesMade=0;
private Context context;
private ImageAdapter2 imageAdapter2;
private int[] puzzle=new int[8];
public static int gwidth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DisplayMetrics dmetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dmetrics);
		gwidth = dmetrics.widthPixels / 3;
		initUI();//initialize the UI
		startGame();
	}
/**
 * Method to start the game;
 */
	private void startGame()
	{
		
		
	}
	
	/**
	 * Initialize the UI elements
	 */
	private void initUI()
	{
		whatis8puzzle=(TextView)findViewById(R.id.tv_whatis8puzzle);
		tv_count=(TextView)findViewById(R.id.tv_count);
		button_submitSolveAI=(Button)findViewById(R.id.button_SolveUsingAI);
		button_nextPuzzle=(Button)findViewById(R.id.button_NextPuzzle);
		gridView_PuzzleImage=(GridView)findViewById(R.id.gridView_puzzleImageGrid);
		
		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(150); // You can manage the blinking time with this
		anim.setStartOffset(50);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(Animation.INFINITE);
		tv_count.setAnimation(anim);
		tv_count.setText(String.valueOf(countMovesMade));
		context= getApplicationContext();
		imageAdapter2 = new ImageAdapter2(context);
		imageAdapter2.setPuzzle(StaticVariableHolder.puzzle);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.setAdapter(imageAdapter2);
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
