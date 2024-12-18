package sand.model.particles;
import java.awt.Color;

import sand.controller.SandLab;
public class Empty extends Particle
{
	public Empty(int row, int column, Color particleColor)
	{
		super(row, column, particleColor);
	}

	public Empty()
	{
		super();
	}
	
	@Override
	public void update(SandLab lab)
	{
		
	}

	
	
}
