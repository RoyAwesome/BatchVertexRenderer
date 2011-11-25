package org.royawesome.renderer;

import java.awt.geom.Rectangle2D;

import org.lwjgl.opengl.GL11;
import org.royawesome.util.Texture2D;

public class SpriteBatch {
	
	BatchVertexRenderer renderer;
	
	boolean isBatching = false;
	
	public SpriteBatch(){
		renderer = BatchVertexRenderer.constructNewBatch(GL11.GL_TRIANGLES);
	}
	
	
	public void begin(){
		if(isBatching) throw new IllegalStateException("Already Batching!");
		renderer.begin();
	}
	
	public void end(){
		if(!isBatching) throw new IllegalStateException("Cannot end batching without a begin");
		renderer.end();
		renderer.render();
	}

	public void drawSprite(Rectangle2D.Float rect, Texture2D texture, int color){
		
		
		
	}
	
	
	
	
}
