package org.royawesome.renderer.shader.variables;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;


public class Mat4ShaderVariable extends ShaderVariable {

	Matrix4f value;
	public Mat4ShaderVariable(int program, String name, Matrix4f value) {
		super(program, name);
		this.value = value;
		
	}

	public Matrix4f get(){
		return value;
	}
	
	@Override
	public void assign() {
		FloatBuffer buff = BufferUtils.createFloatBuffer(4*4);
		value.store(buff);
		buff.flip();
		
		GL20.glUniformMatrix4(location, false, buff);

	}

}
