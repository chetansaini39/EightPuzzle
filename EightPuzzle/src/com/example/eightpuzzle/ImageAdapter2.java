package com.example.eightpuzzle;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter2 extends BaseAdapter{
	private int gwidth = MainActivity.gwidth; // gridview column width
	Context context;
	int[] puzzle;
	int[] imageInt= new int[9];
	public ImageAdapter2( Context context)
	{
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageInt.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageview;
		if(convertView==null){
			imageview= new ImageView(context);
			imageview.setLayoutParams(new GridView.LayoutParams(gwidth, gwidth));
			imageview.setScaleType(ScaleType.CENTER_CROP);
			imageview.setPadding(8, 8, 8, 8);
		}
		else
		{
			imageview=(ImageView) convertView;
		}
		imageview.setImageResource(imageInt[position]);
		return imageview;
	}
	
	public void setPuzzle(int[] puzzle)
	{
		this.puzzle=puzzle;
	}
	



public void setImages()
{
	System.out.println("**************************************************************");
//	System.out.println(Resources.getSystem().getIdentifier("i0",null,null));
	for(int i=0;i<puzzle.length;i++)
	{
//		imageInt[i]=Resources.getSystem().getIdentifier("i"+puzzle[i]+".jpg", "drawable-hdpi", "android");
//		System.out.println(Resources.getSystem().getIdentifier("i"+puzzle[i], "drawable-hdpi", "android"));
		imageInt[i]=getId("i"+puzzle[i], R.drawable.class);
	//	System.out.println(getId("i"+puzzle[i], R.drawable.class));
	}
	System.out.println("Image setup Done");
}
public static int getId(String resourceName, Class<?> c) {
    try {
        Field idField = c.getDeclaredField(resourceName);
        return idField.getInt(idField);
    } catch (Exception e) {
        throw new RuntimeException("No resource ID found for: "
                + resourceName + " / " + c, e);
    }
}

}
