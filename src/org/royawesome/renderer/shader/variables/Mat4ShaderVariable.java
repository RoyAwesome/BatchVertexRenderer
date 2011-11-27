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

	@Override
	public void assign() {
		FloatBuffer buff = BufferUtils.createFloatBuffer(4*4);
		value.load(buff);
		buff.flip();
		
		GL20.glUniformMatrix3(location, false, buff);

	}

}
