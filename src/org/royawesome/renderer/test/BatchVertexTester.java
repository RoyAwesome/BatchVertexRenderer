package org.royawesome.renderer.test;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.royawesome.renderer.*;
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
	Shader shader = null;
	try {
		shader = new Shader("vshader53.glsl", "fshader53.glsl");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	shader.SetUniform("Projection", MatrixUtils.createOrthographic(1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 2.0f));
	Matrix4f view = new Matrix4f();
	view.setIdentity();
	shader.SetUniform("ModelView", view);
	renderer.setShader(shader);
	renderer.enableColors();
	renderer.begin();
		renderer.AddColor(1.0f, 0.0f, 0.0f);
		renderer.AddVertex(.3f, .5f);
		renderer.AddColor(0.0f, 1.0f, 0.0f);
		renderer.AddVertex(-.8f, -.5f);
		renderer.AddColor(0.0f, 0.0f, 1.0f);
		renderer.AddVertex(.2f, -.5f);
		
	renderer.end();
	
	
	GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	while (!Display.isCloseRequested()) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
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
