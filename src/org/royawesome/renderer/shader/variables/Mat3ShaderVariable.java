package org.royawesome.renderer.shader.variables;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix3f;
public class Mat3ShaderVariable extends ShaderVariable {

	Matrix3f value;
	public Mat3ShaderVariable(int program, String name, Matrix3f value) {
		super(program, name);
		this.value = value;
	}

	@Override
	public void assign() {
		FloatBuffer buff = FloatBuffer.allocate(3*3);
		value.load(buff);
		buff.flip();
		
		GL20.glUniformMatrix3(location, false, buff);

	}

}
