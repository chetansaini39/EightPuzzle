

package com.chetan.eightpuzzle;


public class StaticVariableHolder
{

    
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    public static String fileName = "EightPuzzleData";
    public static String solutionSteps = "";
    public static boolean usingAI = false;
    public static int[] puzzle={1,3,4,8,6,2,7,0,5};//working
    public static int readFromFile;
    public static int maxLevel=10;

}
