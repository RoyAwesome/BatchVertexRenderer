package renderer.shader.variables;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class Vec3ShaderVariable extends ShaderVariable {
	Vector3f value;
	public Vec3ShaderVariable(int program, String name, Vector3f value) {
		super(program, name);
		this.value = value;
	}

	@Override
	public void assign() {
		GL20.glUniform3f(location, value.x, value.y, value.z);

	}

}
