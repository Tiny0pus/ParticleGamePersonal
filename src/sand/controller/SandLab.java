package sand.controller;
import java.util.HashMap;
import java.util.Random;

import sand.model.Constants;
import sand.model.particles.Particle;
import sand.view.SandDisplay;






public class SandLab
{



// Map Particle Types to an integer representation
	private final static Particle[] PARTICLETYPES = Constants.SandDisplayConstants.PARTICLETYPES;	
	HashMap<Integer, String> particleIdentifiers = new HashMap<Integer, String>();
	for (int index = 0; index < PARTICLETYPES.length; index++)
	{
		particleIdentifiers.put(index, PARTICLETYPES[index].getClass().getSimpleName());
	}
  //do not add any more fields below
  private Particle[][] grid;
  private SandDisplay display;
  
  private Random rand = new Random();
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[Constants.SandDisplayConstants.PARTICLETYPES.length];
    // Each value needs a name for the button
	for (int particleIntegerRepresentation = 0; particleIntegerRepresentation < PARTICLETYPES.length; particleIntegerRepresentation++)
	{
		String className = particleIdentifiers.get(particleIntegerRepresentation);
		names[particleIntegerRepresentation] = className;
	}

    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[rows][cols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int particleIntegerRepresentation)
  {
  this.grid[row][col] = particleIntegerRepresentation;

   
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
    
	for (int row = 0; row < grid.length; row++)
	{
		for (int col = 0; col < grid[row].length; col++)
		{
			int tool = grid[row][col];
			ParticleColor particle = ParticleColor.values()[tool];
			display.setColor(row, col, particle.getColor());
			
		}
	}
	  
	  
  }

  

  
  
  
  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
	 
	  int randomCol = (int) (Math.random() * grid[0].length);
	  int randomRow = (int) (Math.random() * grid.length);
	  
	  
	  
	  
	  
	 
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    
    
  }
  

  private boolean isWithinBounds(int row, int column)
  {
	  return row >= 0 && column >= 0 && row < grid.length && column < grid[row].length;
	  
  }
  
  private boolean isValidAndContains(int row, int column, int tool)
  {
	  return (isWithinBounds(row, column)) && grid[row][column] == tool; 
  }
  private boolean isValidAndContains(int row, int column, int[] tools)
  {
	  boolean response = isWithinBounds(row, column);
	  boolean doesContain = false;

	  if (!response)
	  {
		  return response;
	  }
	  int particleAtLocation = grid[row][column];
	  for (int tool : tools)
	  {
		  if (particleAtLocation == tool)
		  {
			  response = true;
			  return response;
		  }
	  }
	  return false;
	  
	  
  }
  
  
  
  
  private void swapParticles(int rowOne, int colOne, int rowTwo, int colTwo)
  {
	  int tool = grid[rowOne][colOne];
	  grid[rowOne][colOne] = grid[rowTwo][colTwo];
	  grid[rowTwo][colTwo] = tool;
	  
  }
  
  
 

	  
  
  
 
  
  

  
  
 
	  
	  
	  
	  
	  
  
  
  
  
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}