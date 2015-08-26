/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Chetan
 */
public class HelperMethods {
    static int count=0;
    String initVal="";
    public static String solutionSteps;
    long startTime= System.currentTimeMillis();
    int[] goalSet = {1, 2, 3, 8, 0, 4, 7, 6, 5};
    HashMap<String, String> m= new HashMap<String, String>();
     boolean finished=true;
    
     public int[] DeepCopyArray(int[] arr)
    {
        int[] copy= new int[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            copy[i]=arr[i];
        }
        return copy;
    }
    
     public int[] convertStringToIntArray(String data)
     {
         String data1=data.replaceAll("[\\,\\[\\]\\ ]", "");
         int[] arrInt= new int[9];
         int length=data1.length();
         for(int i=0;i<length;i++)
         {
             arrInt[i]=Character.getNumericValue(data1.charAt(i));
         }
         return arrInt;
     }
     /**
      * Method to maintain count state for MAP
      * @return 
      */
     public int getCount()
     {
        count =count+1;
//         System.out.println("Count : "+count);
         
         return count;
     }
     /**
      * Method for printing the contents of the MAP
      * @param map 
      */
     public void printMap(HashMap<String,Integer> map)
     {
        for(String key : map.keySet())
        {
            System.out.println(key + " " + map.get(key));
        }
     }
     public void printQueue(Queue q)
     {
         int count=1;
         Iterator it=q.iterator();
         while(it.hasNext())
         {
             
             count++;
//             System.out.println(count+ " : "+it.next());
         }
     }
     
     /**
      * Map to add the moves
      */
     public void addMovesToMap(int[] key, String value)
     {
         String s=Arrays.toString(key);
         m.put(s, value);
     }
     

    public int FindPositionOf(int data, int[] puzzle)
    { if(m!=null)
    {
        m=null;
    }
        int postion=10;
        for(int i=0;i<puzzle.length;i++)
        {
            if(data==puzzle[i])
            {
                postion=i;
            }
        }
        return postion;
    }
    
     /**
      * Method to print the puzzle board in current state
      * @param arr 
      */
     public void printPuzzle(int[] arr)
    {System.out.println("");
        if(arr.length!=9)
        {
            System.out.println("Improper puzzle length");
        }
        
            System.out.println(arr[0]+" | "+arr[1]+" | "+arr[2]+"\n"+"---------"+"\n"+
                               arr[3]+" | "+arr[4]+" | "+arr[5]+"\n"+"---------"+"\n"+
                               arr[6]+" | "+arr[7]+" | "+arr[8]);
            
            
        
    }
     
     
     
}
