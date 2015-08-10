package com.example.eightpuzzle;


import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.algo.*;

public class MainActivity extends Activity
{
private TextView whatis8puzzle,tv_count,tv_solSteps;;
private Button button_submitSolveAI,button_nextPuzzle;
private GridView gridView_PuzzleImage;
private int countMovesMade=0;
private Context context;
private ImageAdapter2 imageAdapter2;
private int[] puzzle=new int[8];
public static int gwidth;
private DisplayMetrics dmetrics;
private int count_attempt = 0; 
private PuzzleMoves puzzleMove;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dmetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dmetrics);
		gwidth = dmetrics.widthPixels / 3;
		puzzleMove= new PuzzleMoves();
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
		puzzle=StaticVariableHolder.puzzle.clone();
		whatis8puzzle=(TextView)findViewById(R.id.tv_whatis8puzzle);
		tv_solSteps=(TextView)findViewById(R.id.tv_solSteps);
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
		imageAdapter2.setPuzzle(puzzle);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.setAdapter(imageAdapter2);	
		int widthPixels = dmetrics.widthPixels;
		int heightPixels = dmetrics.heightPixels;	
		button_submitSolveAI.setMinHeight((int) ((heightPixels/3)*.30));//set the min height /7 width of the button
		button_submitSolveAI.setMinWidth((widthPixels/2));
		button_nextPuzzle.setMinHeight((int) ((heightPixels/3)*.30));//set the min height & width of the second button
		button_nextPuzzle.setMinWidth((widthPixels/2));
		animatewhatis8puzzle();
		
		//assign swipe controls to grid/view
		gridView_PuzzleImage.setOnTouchListener(new OnSwipeTouchListener()
		{
			public boolean onSwipeTop()
			{
				movePuzzleUP();
				return true;
			}

			@Override
			public boolean onSwipeBottom()
			{
				movePuzzleDown();
				return true;
			}

			@Override
			public boolean onSwipeLeft()
			{
				movePuzzleLeft();
				return true;
			}

			@Override
			public boolean onSwipeRight()
			{
				movePuzzleRight();
				return true;
			}
			
		}
			);
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

	/**
	 * This method animates the what is 8puzzle text
	 */
	private void animatewhatis8puzzle()
	{
		SpannableString spanString = new SpannableString("What is 8 Puzzle?");
		// spanString.setSpan(new UnderlineSpan(), 0, spanString.length(),
		// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spanString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spanString.setSpan(new AbsoluteSizeSpan(25, true), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		whatis8puzzle.setText(spanString);
	}
	/**
	 * Method to move blank puzzle piece UP
	 */
	private void movePuzzleUP()
	{
		count_attempt++;
		tv_count.setText(String.valueOf(count_attempt));
		int[] p = puzzleMove.moveUP(puzzle);
		System.out.println("Puzzle After Swiping UP " + Arrays.toString(p));
		// paint the puzzle on screen
		imageAdapter2.setPuzzle(p);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.invalidateViews();

		if (Arrays.equals(p, GlobalData.goalState)) {
			System.out.println("******Goal State Reached ***********");
			animateGoalState();
		}
	}

	/**
	 * Method to move the blank piece of puzzle DOWN
	 */
	private void movePuzzleDown()
	{
		count_attempt++;
		tv_count.setText(String.valueOf(count_attempt));
		// Toast.makeText(MainActivity.this, "Swipe Bottom",
		// Toast.LENGTH_SHORT).show();
		int[] p = puzzleMove.moveDOWN(puzzle);
		System.out.println("Puzzle After Swiping DOWN " + Arrays.toString(p));
		imageAdapter2.setPuzzle(p);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.invalidateViews();
		// gridView.setAdapter(imageAdapter2);
		if (Arrays.equals(p, GlobalData.goalState)) {
			System.out.println("******Goal State Reached ***********");
			animateGoalState();
		}
	}

	/**
	 * Method to move blank puzzle piece LEFT
	 */
	private void movePuzzleLeft()
	{
		count_attempt++;
		tv_count.setText(String.valueOf(count_attempt));
		// Toast.makeText(MainActivity.this, "Swipe Left",
		// Toast.LENGTH_SHORT).show();
		int[] p = puzzleMove.moveLEFT(puzzle);
		System.out.println("Puzzle After Swiping LEFT " + Arrays.toString(p));
		imageAdapter2.setPuzzle(p);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.invalidateViews();
		// gridView.setAdapter(imageAdapter2);
		if (Arrays.equals(p, GlobalData.goalState)) {
			System.out.println("******Goal State Reached ***********");
			animateGoalState();
		}
	}

	/**
	 * Method to move blank puzzle piece RIGHT
	 */
	private void movePuzzleRight()
	{
		count_attempt++;
		tv_count.setText(String.valueOf(count_attempt));
		int[] p = puzzleMove.moveRIGHT(puzzle);
		System.out.println("Puzzle After Swiping RIGHT " + Arrays.toString(p));
		imageAdapter2.setPuzzle(p);// initialize the puzzle
		imageAdapter2.setImages();
		gridView_PuzzleImage.invalidateViews();
		if (Arrays.equals(p, GlobalData.goalState)) {
			System.out.println("******Goal State Reached ***********");
			animateGoalState();
		}
	}

	/**
	 * This method animates the goal state. Show user that they have won.
	 */
	private void animateGoalState()
	{
		SpannableString spanString = new SpannableString("You have won");
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// spanString.setSpan(new ForegroundColorSpan(Color.GREEN), 0,
		// spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spanString.setSpan(new AbsoluteSizeSpan(35, true), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tv_solSteps.setText(spanString);
	//TODO disable gridview swipe

	}

}
