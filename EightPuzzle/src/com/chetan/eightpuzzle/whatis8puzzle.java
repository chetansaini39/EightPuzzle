package com.chetan.eightpuzzle;

import com.chetan.algo.GlobalData;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;

public class whatis8puzzle extends Activity
{
	private GridView gridView;
	private ImageAdapter2 imageAdapter;
	private Context context;
	
@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.whatis8puzzle);
	gridView=(GridView) findViewById(R.id.gridView01);
	context=getApplicationContext();
	imageAdapter = new ImageAdapter2(context);
	imageAdapter.setPuzzle(GlobalData.goalState);// initialize the puzzle
	imageAdapter.setImages();
	gridView.setAdapter(imageAdapter);	
	

}
	
}
