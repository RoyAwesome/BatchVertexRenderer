package org.royawesome.renderer.shader;

import java.io.FileNotFoundException;

import org.lwjgl.util.vector.Matrix4f;

public class BasicShader extends Shader {
	
	
	
	public BasicShader() throws FileNotFoundException {
		super("vtest.glsl", "ftest.glsl");
		
	}
	
	public void assign(boolean compatabilityMode){
		if(!variables.containsKey("Projection"))throw new IllegalStateException("Basic Shader must have a projection matrix assigned");
		if(!variables.containsKey("View")) throw new IllegalStateException("Basic Shader must have a view matrix assigned");
		
	}
	
	public void setViewMatrix(Matrix4f mat){
		SetUniform("View", mat);
	}
	
	public void setProjectionMatrix(Matrix4f mat){
		SetUniform("Projection", mat);
	}
}
