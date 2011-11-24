package org.royawesome.renderer.shader.variables;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;


public class AttributeShaderVariable extends ShaderVariable {

	int size;
	int type;
	long offset;
	
	public AttributeShaderVariable(int program, String name, int size, int type, long offset) {
		super(program, name);
		this.location = GL20.glGetAttribLocation(program, name);
		
		
		if(GL11.glGetError() == GL11.GL_INVALID_OPERATION){
			String error = GL20.glGetProgramInfoLog(program, 255);
			System.out.println(error);
		}
		
		this.size = size;
		this.type = type;
		this.offset = offset;
		
		
	}

	@Override
	public void assign() {
		System.out.printf("Location %d, Size %d, type %d, offset %d\n", location, size, type, offset);
		GL20.glEnableVertexAttribArray(location);
		GL20.glVertexAttribPointer(location, size, type, false, 0, offset);	
		

	}

}
