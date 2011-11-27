package org.royawesome.renderer.test;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.royawesome.renderer.*;
import org.royawesome.renderer.shader.BasicShader;
import org.royawesome.renderer.shader.Shader;
import org.royawesome.util.MatrixUtils;


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
    
	BatchVertexRenderer renderer = BatchVertexRenderer.constructNewBatch(GL11.GL_TRIANGLES);
	BasicShader shader = null;
	try {
		shader = new BasicShader();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	shader.setProjectionMatrix(new Matrix4f());
	shader.setViewMatrix(new Matrix4f());
	renderer.setShader(shader);
	renderer.enableColors();
	renderer.begin();
		renderer.AddColor(1.0f, 0.0f, 0.0f);
		renderer.AddVertex(1.0f, 1.0f);
		renderer.AddColor(1.0f, 1.0f, 0.0f);
		renderer.AddVertex(-1.0f, 1.0f);
		renderer.AddColor(0.0f,1.0f, 1.0f);
		renderer.AddVertex(-1.0f, -1.0f);
		
	renderer.end();
	
	
	GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	GL11.glEnable(GL11.GL_DEPTH_TEST);
	while (!Display.isCloseRequested()) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
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
