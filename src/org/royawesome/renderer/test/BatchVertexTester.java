package org.royawesome.renderer.test;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.ResourceLoader;
import org.royawesome.renderer.*;
import org.royawesome.renderer.shader.*;
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
        Texture tex = null;
    try {
		tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("atlastest64.png"));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	};
	BatchVertexRenderer renderer = BatchVertexRenderer.constructNewBatch(GL11.GL_POLYGON);
	BasicShader shader = null;
	try {
		shader = new BasicShader();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(1);
	}
	shader.setProjectionMatrix(MatrixUtils.createOrthographic(1.0f, -1.0f, 1.0f, -1.0f, 0.0f, 1.0f));
	shader.setViewMatrix(new Matrix4f());
	shader.SetUniform("texture", tex);
	renderer.setShader(shader);
	renderer.enableColors();
	renderer.enableTextures();
	renderer.begin();
		renderer.AddColor(1.0f, 0.0f, 0.0f);
		renderer.AddTexCoord(1.0f, 1.0f);
		renderer.AddVertex(.5f, .5f);
		
		
		renderer.AddColor(0.0f, 1.0f, 0.0f);
		renderer.AddTexCoord(0.0f, 1.0f);
		renderer.AddVertex(-.5f, .5f);
		
		
		renderer.AddColor(0.0f, 0.0f, 1.0f);
		renderer.AddTexCoord(0.0f, 0.0f);
		renderer.AddVertex(-.5f, -.5f);
		
		renderer.AddColor(0.0f, 0.0f, 1.0f);
		renderer.AddTexCoord(1.0f, 0.0f);
		renderer.AddVertex(.5f, -.5f);
		
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
