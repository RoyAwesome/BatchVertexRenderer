package org.royawesome.renderer;
import java.nio.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import gnu.trove.list.array.*;

public class GL30BatchVertexRenderer extends BatchVertexRenderer {
	int vao;
	IntBuffer vbos;

	
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
		
		
	}
	

	
	protected void doFlush(){
		
		if(vbos != null) GL15.glDeleteBuffers(vbos);
		
		GL30.glBindVertexArray(vao);
		int buffers = 1;
		if(useColors) buffers++;
		if(useNormals) buffers++;
		if(useTextures) buffers++;
		vbos = BufferUtils.createIntBuffer(buffers);
		GL15.glGenBuffers(vbos);
		
		buffers = 0;
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(buffers));
		
		FloatBuffer vBuffer =  BufferUtils.createFloatBuffer(vertexBuffer.size());
		vBuffer.clear();
		vBuffer.put(vertexBuffer.toArray());
		vBuffer.flip();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuffer, GL15.GL_STATIC_DRAW);
		
		activeShader.enableAttribute("vPosition", 4, GL11.GL_FLOAT, 0);
		
		if(useColors){
			buffers++;
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(buffers));
			
			vBuffer =  BufferUtils.createFloatBuffer(colorBuffer.size());
			vBuffer.clear();
			vBuffer.put(colorBuffer.toArray());
			vBuffer.flip();
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuffer, GL15.GL_STATIC_DRAW);
			
			activeShader.enableAttribute("vColor", 4, GL11.GL_FLOAT, 0);
		}
		if(useNormals){
			buffers++;
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(buffers));
			
			vBuffer =  BufferUtils.createFloatBuffer(normalBuffer.size());
			vBuffer.clear();
			vBuffer.put(normalBuffer.toArray());
			vBuffer.flip();
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuffer, GL15.GL_STATIC_DRAW);
			
			activeShader.enableAttribute("vNormal", 4, GL11.GL_FLOAT, 0);
		}
		if(useTextures){
			buffers++;
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbos.get(buffers));
			
			vBuffer =  BufferUtils.createFloatBuffer(uvBuffer.size());
			vBuffer.clear();
			vBuffer.put(uvBuffer.toArray());
			vBuffer.flip();
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vBuffer, GL15.GL_STATIC_DRAW);
			
			activeShader.enableAttribute("vTexCoord", 4, GL11.GL_FLOAT, 0);
		}
			
		
		
		activeShader.assign();
				
	}
	
	/**
	 * Draws this batch
	 */
	public void doRender(){
		GL30.glBindVertexArray(vao);
		activeShader.assign();
		GL11.glDrawArrays(renderMode, 0, vertexBuffer.size() / 4);
		
	}
	
	
}
