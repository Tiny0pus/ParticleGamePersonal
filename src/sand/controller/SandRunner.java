package sand.controller;

import sand.model.Constants;

public class SandRunner
{
   public static void main(String [] args)
   {
	  final int screenSize = Constants.SandDisplayConstants.SCREENSIZE;
      SandLab app = new SandLab(screenSize, screenSize); 
      app.run();
   }
  
}