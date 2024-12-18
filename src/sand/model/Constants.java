package sand.model;
import java.awt.Color;

import sand.model.particles.Particle;
import sand.model.particles.Sand;
public class Constants
{
	//Example Particles
	public static final Sand exampleSand = new Sand();

	//Store constants related to the superclass Particle (can be used for subclasses)
	public static class ParticleConstants
	{
		public static final Color defaultParticleColor = new Color(0,0,0);
	}
	
	
	
		//Store constants related to SandDisplay
		public static class SandDisplayConstants
		{
			public static final int SCREENSIZE = 100;
			public static final Particle[] PARTICLETYPES = new Particle[] {exampleSand};

		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
