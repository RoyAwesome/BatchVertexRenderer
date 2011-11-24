package org.royawesome.renderer;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import gnu.trove.list.array.*;

public class GL30BatchVertexRenderer extends BatchVertexRenderer {
	int vao;
	int vertexBuffer;
	

	
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
		
		vertexBuffer = ARBVertexBufferObject.glGenBuffersARB();
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertexBuffer);
		
	
	}
	

	
	protected void flush(){
		
		GL30.glBindVertexArray(vao);
		ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertexBuffer);
		
		FloatBuffer vertexBuffer = FloatBuffer.wrap(this.buffer.toArray());
		
		ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vertexBuffer, ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		
		super.flush();
		
	}
	
	/**
	 * Draws this batch
	 */
	public void render(){
		checkRender();
		
		
	}
	
	
}
