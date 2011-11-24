package org.royawesome.renderer.test;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.royawesome.renderer.*;
import org.royawesome.renderer.shader.Shader;


public class BatchVertexTester {
	
	static boolean GL30mode = true;
	
	public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(800,600));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}

	// init OpenGL here
    
	BatchVertexRenderer renderer = (GL30mode)? new GL30BatchVertexRenderer(GL11.GL_TRIANGLES) : new GL11BatchVertexRenderer(GL11.GL_TRIANGLES);
	try {
		renderer.setShader(new Shader("vtest.glsl", "ftest.glsl"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	renderer.enableColors();
	renderer.begin();
		renderer.AddColor(1.0f, 0.0f, 0.0f);
		renderer.AddVertex(.5f, .5f, 0.0f);
		renderer.AddColor(0.0f, 1.0f, 0.0f);
		renderer.AddVertex(.2f, .8f, 0.0f);
		renderer.AddColor(0.0f, 0.0f, 1.0f);
		renderer.AddVertex(.8f, .8f, 0.0f);
	renderer.end();
	
	while (!Display.isCloseRequested()) {
	
	    // render OpenGL here
		renderer.render();
				
	    Display.update();
	}
		
	Display.destroy();
    }
	
    public static void main(String[] argv) {
    	BatchVertexTester displayExample = new BatchVertexTester();
	displayExample.start();
    }
}
