package renderer.shader.variables;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

public class Vec2ShaderVariable extends ShaderVariable {
	Vector2f value;
	public Vec2ShaderVariable(int program, String name, Vector2f value){
		super(program, name);
		this.value = value;
	}

	@Override
	public void assign() {
		GL20.glUniform2f(location, value.x, value.y);

	}



}
