package sand.model.particles;
import sand.controller.*;
import sand.model.Constants;
import sand.model.Constants.ParticleConstants;

import java.awt.Color;

public abstract class Particle
{
	protected int row;
	protected int column;
	
	protected Color particleColor;
	
	
	
	public Particle()
	{
		this.row = -1;
		this.column = -1;
		this.particleColor = Constants.ParticleConstants.defaultParticleColor;
	}
	
	public Particle(int row, int column, Color color)
	{
		this.row = row;
		this.column = column;
		this.particleColor = color;
	}
	
	
	
	
	public Color getParticleColor()
	{
		return this.particleColor;
	}
	
	public int[] getLocation()
	{
		return new int[] {row, column};
	}
	
	public void setLocation(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	
	public abstract void update(SandLab lab);	
	
	
	
	
	
	
	
	
	
	
	
	

}
