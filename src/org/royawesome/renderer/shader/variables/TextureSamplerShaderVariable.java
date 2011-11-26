package org.royawesome.renderer.shader.variables;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;

public class TextureSamplerShaderVariable extends ShaderVariable {
	int textureID;
	int textureNumber;
	
	
	public TextureSamplerShaderVariable(int program, String name, Texture texture) {
		super(program, name);
		textureID = texture.getTextureID();
		this.textureNumber = 0;
	}

	@Override
	public void assign() {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + textureNumber);
		GL30.glUniform1ui(location, textureID);
	}

}
