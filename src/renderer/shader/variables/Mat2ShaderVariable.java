package renderer.shader.variables;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix2f;

public class Mat2ShaderVariable extends ShaderVariable {
	Matrix2f value;

	public Mat2ShaderVariable(int program, String name, Matrix2f value) {
		super(program, name);
		this.value = value;
	}

	@Override
	public void assign() {
		FloatBuffer buff = FloatBuffer.allocate(2);
		value.load(buff);
		buff.flip();
		
		GL20.glUniformMatrix2(location, false, buff);
	}

}
