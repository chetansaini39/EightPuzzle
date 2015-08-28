package com.chetan.eightpuzzle;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;


public class FileHandling {
   Context context;

   public FileHandling(Context var1) {
      this.context = var1;
   }

   public void initFileStorage() {
      if((new File(this.context.getFilesDir(), StaticVariableHolder.fileName)).exists()) {
         System.out.println(StaticVariableHolder.fileName + " : File Exists at location -> " + this.context.getFilesDir());
         System.out.println("setting new Puzzle");
         int var1 = this.readFromFile();
         StaticVariableHolder.readFromFile=var1;
      } else {
         this.writeToFile(6);
      }

   }

   public int readFromFile() {
      int var1 = 0;

      int var2;
      try {
         var2 = this.context.openFileInput(StaticVariableHolder.fileName).read();
      } catch (Exception var4) {
         System.out.println("exception : " + var4);
         var4.printStackTrace();
         return var1;
      }

      var1 = var2;
      return var1;
   }

   public void writeToFile(int var1) {
      try {
         FileOutputStream var2 = this.context.openFileOutput(StaticVariableHolder.fileName, 0);
         var2.write(var1);
         var2.close();
         PrintStream var5 = System.out;
         StringBuilder var3 = new StringBuilder("Written to file :");
         var5.println(var3.append(var1).toString());
      } catch (Exception var4) {
         Toast.makeText(this.context, "Error File Operation ", 1).show();
      }

   }
}
