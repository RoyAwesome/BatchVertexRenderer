package org.royawesome.renderer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import gnu.trove.list.array.*;

public class GL30BatchVertexRenderer extends BatchVertexRenderer {
	int vao;
	int vbo;
	

	
	//Using FloatArrayList because I need O(1) access time
	//and fast ToArray()
	TFloatArrayList buffer = new TFloatArrayList();
	
	
	/**
	 * Batch Renderer using OpenGL 3.0 mode.
	 * 
	 * @param renderMode 
	 * Mode to render in
	 */
	public GL30BatchVertexRenderer(int renderMode){
		super(renderMode);
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		vbo = GL15.glGenBuffers();
		//GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		
	}
	

	
	protected void flush(){
		
		buffer.clear();
		buffer.addAll(vertexBuffer);
		if(useColors) buffer.addAll(colorBuffer);
		
		GL30.glBindVertexArray(vao);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		FloatBuffer vBuffer =  BufferUtils.createFloatBuffer(buffer.size());
		vBuffer.put(buffer.toArray());
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuffer, GL15.GL_STATIC_DRAW);
		
		
		activeShader.enableAttribute("vPosition", 3, GL11.GL_FLOAT, 0);
		if(useColors) activeShader.enableAttribute("vColor", 3, GL11.GL_FLOAT, vertexBuffer.size());
		
		activeShader.assign();
		
		super.flush();
		
	}
	
	/**
	 * Draws this batch
	 */
	public void render(){
		checkRender();
		GL30.glBindVertexArray(vao);
		activeShader.assign();
		GL11.glDrawArrays(renderMode, 0, vertexBuffer.size());
		
	}
	
	
}
