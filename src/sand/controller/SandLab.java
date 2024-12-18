package sand.controller;
import java.awt.*;
import sand.model.*;
import java.util.*;
import sand.view.*;
import java.util.Random;
public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int FONDANT = 1;
  public static final int CRUMB = 2;
  public static final int HONEY = 3;
  public static final int FIRE = 4;
  public static final int FLY = 5;
  
  //do not add any more fields below
  private Particle[][] grid;
  private SandDisplay display;
  
  private Random rand = new Random();
  static private enum ParticleColor 
  {
	  EMPTY(new Color(156,255,220)),
	  FONDANT(new Color(255,255,255)),
	  CRUMB(new Color(95, 75, 55)),
	  HONEY(new Color(245,193,37)),
	  FIRE(new Color(250, 27, 27)),
	  FLY(new Color(0,0,0));
	  
	  
	  
	  private final Color color;
	  
	  ParticleColor(Color color)
	  {
		  this.color = color;
	  }
	  
	  public Color getColor()
	  {
		  return this.color;
	  }
	  
	  static public int getSize()
	  {
		  return values().length;
	  }
  }
  
  
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
    names = new String[ParticleColor.getSize()];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[FONDANT] = "Fondant";
    names[CRUMB] = "Crumb";
    names[HONEY] = "Honey";
    names[FIRE] = "Fire";
    names[FLY] = "Fly";
    
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
  this.grid[row][col] = tool;

    //2. Assign the values associated with the parameters to the grid
   
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
	  int particleType = grid[randomRow][randomCol];
	  
	  if (particleType == CRUMB)
	  {
		  updateCrumb(randomRow, randomCol);
	  }
	  else if (particleType == HONEY)
	  {
		  updateHoney(randomRow, randomCol);
	  }
	  else if (particleType == FIRE)
	  {
		  updateFire(randomRow, randomCol);
		  
	  }
	  else if (particleType == FLY)
	  {
		  updateFly(randomRow, randomCol);
	  }
	  
	  
	  
	  
	 
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
  
  
  private void updateFly(int currentRow, int currentCol)
  {
	  int otherRow = currentRow;
	  int otherCol = currentCol;
	  boolean horizontal;
	  boolean vertical;
	  int finalDirection = 1;
	  //choose cardinal direction
	  if (Math.random() < 0.5)
	  {
		  finalDirection = -1;
	  }
	  
	  
	  if (Math.random() < 0.5)
	  {
		  horizontal = true;
		  vertical = false;
	  }
	  else
	  {
		  vertical = true;
		  horizontal = false;
	  }
	  if (horizontal)
	  {
		  otherRow += finalDirection;
	  }
	  else
	  {
		  otherCol += finalDirection;
	  }
	  if (isValidAndContains(otherRow, otherCol, new int[] {HONEY, EMPTY, FONDANT}))
	  {
		  swapParticles(currentRow, currentCol, otherRow, otherCol);
	  }
	  
	  
	  
	  
	  
	  
	  
  }
  
 
  
  
  private void updateFire(int currentRow, int currentCol)
  {
	  if (Math.random() < 0.01)
	  {
		  grid[currentRow][currentCol] = 0;
	  }
	  int randomDirection = (int) (Math.random() * 3)-1;
	  int otherRow = currentRow;
	  int otherCol = currentCol;
	  if (otherRow < 1)
	  {
		  return;
	  }
	  if (randomDirection == 0)
	  {
		  otherRow --;
	  }
	  otherCol += randomDirection;
	  
	  

	  
	  
	  if (!isValidAndContains(otherRow, otherCol, new int[] {0,1,2,3,5}))
	  {
		  return;
	  }
	  
	  grid[otherRow][otherCol] = 0;
	  swapParticles(currentRow, currentCol, otherRow, otherCol);

  }
  
  
  private void updateHoney(int currentRow, int currentCol)
  {
	  //give each honey a 50/50 chance to move
	  //simulates a "viscosity"
	  boolean canMove = (Math.random() < 0.5);
	  if (!canMove)
	  {
		  return;
	  }
	  int randomDirection = (int) (Math.random() * 3)-1;
	  int otherRow = currentRow;
	  int otherCol = currentCol;
	  if (randomDirection == 0)
	  {
		  otherRow ++;
	  }
	  otherCol += randomDirection;
	  
	
	  if (!isWithinBounds(otherRow, otherCol))
	  {
		  return;
	  }
	  
	  if (grid[otherRow][otherCol] != EMPTY)
	  {
		  return;
	  }
	  
	  swapParticles(currentRow, currentCol, otherRow, otherCol);
	  
	  
	  
	  
	  
	  
  }
  
  
  private void updateCrumb(int currentRow, int currentCol)
  {
	  int otherRow = currentRow + 1;
	  int otherCol = currentCol;
	  int direction = 0;
	  if (Math.random() < 2)
	  {
		  direction = + (int) (Math.random() * 3) - 1;
	  }

		if (currentRow +1 < grid.length)
		{
			if (isWithinBounds(otherRow, otherCol + direction))
			{
				otherCol += direction;
			}
			if (grid[currentRow + 1][currentCol] == EMPTY || grid[currentRow + 1][currentCol] == HONEY)
			{
				
					swapParticles(currentRow, currentCol, otherRow, otherCol);
			}
		
		}

		
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