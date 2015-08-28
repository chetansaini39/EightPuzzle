package com.chetan.eightpuzzle;


import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetan.algo.*;
import com.chetan.nextPuzzle.NextPuzzleGenerator;

public class MainActivity extends Activity
{
private TextView whatis8puzzle,tv_count,tv_solSteps,tv_goalRached;;
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
private A_Star_H_Two aStar;
private String solution="";
private int aicount = 0 ;
SpannableString spanString;
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
		tv_goalRached=(TextView)findViewById(R.id.tv_goalReached);
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
		tv_solSteps.setSaveFromParentEnabled(false);
		tv_solSteps.setSaveEnabled(true);
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
		tv_solSteps.clearComposingText();
		spanString.removeSpan(new RelativeSizeSpan(2.0f) );
		spanString.removeSpan(new UnderlineSpan() );
		spanString.removeSpan(new ForegroundColorSpan(Color.YELLOW));
		spanString.removeSpan(tv_solSteps);
		spanString = new SpannableString("You have won");
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		// spanString.setSpan(new ForegroundColorSpan(Color.GREEN), 0,
		// spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spanString.setSpan(new AbsoluteSizeSpan(35, true), 0, spanString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		tv_solSteps.setText(spanString);
		
	//TODO disable gridview swipe

	}

	/**
	 * Method for solving the game using the AI Method is called for the AI
	 * button
	 * 
	 */
	public String solveUsingAI()
	{

		aStar = new A_Star_H_Two(puzzle);
		System.out.println("AI Solving Puzzle : " + Arrays.toString(puzzle));
		if (Arrays.equals(puzzle, GlobalData.goalState)) {
			Toast.makeText(MainActivity.this, "Already in GOAL state", Toast.LENGTH_SHORT).show();
		}

		else {

			aStar.solve();
			solution = aStar.getSolutionSteps();
			
		}
		return solution;
	}

	/**
	 * Methods that runs the astar algo as background thread or as async task to avoid UI thread errors
	 * @author chetan
	 *
	 */
	private class SolveUsingAI_AsyncTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{
			System.out.println("in do in background");
			return solveUsingAI();
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			System.out.println("in Post Execute");
			gridView_AI();animateSolutionSteps();;
		}
	

	}
	
	/**
	 * Method called when grid view follows the solutions steps.
	 */
	private void gridView_AI()
	{
		gridView_PuzzleImage.setOnTouchListener(new OnSwipeTouchListener()
		{
			@Override
			public boolean onSwipeLeft()
			{
				
				if (solution.charAt(aicount) == 'L') {
					aicount++;
					movePuzzleLeft();
					animateSolutionSteps();
				}

				return true;
			}

			@Override
			public boolean onSwipeRight()
			{
				if (solution.charAt(aicount) == 'R') {
					aicount++;
					movePuzzleRight();
					animateSolutionSteps();
				}
				return true;
			}

			@Override
			public boolean onSwipeTop()
			{
				if (solution.charAt(aicount) == 'U') {
					aicount++;
					movePuzzleUP();
					animateSolutionSteps();
				}
				return true;
			}

			@Override
			public boolean onSwipeBottom()
			{
				if (solution.charAt(aicount) == 'D') {
					aicount++;
					movePuzzleDown();
					animateSolutionSteps();
				}
				return true;
			}
		});
	}
	/**
	 * Method to change the effects on the solutions steptext. It underlines,
	 * change size of the current move
	 */
	private void animateSolutionSteps()
	{
		tv_solSteps.setTextColor(Color.BLUE);
		if(aicount<solution.length())
		{
		spanString = new SpannableString(solution);
		System.out.println("Span String-> "+spanString+" Length-> "+spanString.length()+" \t aicount : "+aicount);
		spanString.setSpan(new RelativeSizeSpan(2.0f), aicount, aicount+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		spanString.setSpan(new UnderlineSpan(), aicount, aicount+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		spanString.setSpan(new ForegroundColorSpan(Color.GREEN), aicount, aicount+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		tv_solSteps.setText(spanString);
		}
		
	}
	
	/**
	 * Method that gets called when solve using AI button is clicked
	 * @param v
	 */
	public void onClick_solveUsingAI(View v)
	{
		SolveUsingAI_AsyncTask asyncTask = new SolveUsingAI_AsyncTask();
		asyncTask.execute();
	}
	
	/**
	 * Method to generate next puzzle
	 */
	public void onClick_nextPuzzle(View v)
	{
		aicount=0;count_attempt=0;
		tv_solSteps.setText("");
//		tv_solSteps.clearComposingText();
//		spanString.removeSpan(tv_solSteps);
		NextPuzzleGenerator nextPuzzleGenerator=new NextPuzzleGenerator(context);
		StaticVariableHolder.puzzle=nextPuzzleGenerator.puzzle;
		initUI();
	}
	
	public void whatis8puzzle(View v)
	{
		Intent intent= new Intent(context, whatis8puzzle.class);
		startActivity(intent);
	}

}
