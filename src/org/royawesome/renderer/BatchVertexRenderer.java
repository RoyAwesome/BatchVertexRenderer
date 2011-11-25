package org.royawesome.renderer;
import java.io.FileNotFoundException;

import gnu.trove.list.array.TFloatArrayList;
import org.lwjgl.util.vector.*;
import org.royawesome.renderer.shader.EmptyShader;
import org.royawesome.renderer.shader.Shader;

/**
 * 
 * @author RoyAwesome
 *
 */
public abstract class BatchVertexRenderer {

	
	boolean batching = false;
	boolean flushed = false;
	
	int renderMode;
	
	//Using FloatArrayList because I need O(1) access time
	//and fast ToArray()
	TFloatArrayList vertexBuffer = new TFloatArrayList();
	TFloatArrayList colorBuffer = new TFloatArrayList();
	TFloatArrayList normalBuffer = new TFloatArrayList();
	
	
	boolean useColors = false;
	boolean useNormals = false;
	
	Shader activeShader = null;
	
	public BatchVertexRenderer(int mode){
		renderMode = mode;
	}
	
	/**
	 * Begin batching render calls
	 */
	public void begin(){
		if(batching) throw new IllegalStateException("Already Batching!");
		batching = true;
		flushed = false;		
		vertexBuffer.clear();
		colorBuffer.clear();
		
	}
	/**
	 * Ends batching and flushes cache to the GPU
	 */
	public void end(){
		if(!batching) throw new IllegalStateException("Not Batching!");
		batching = false;
		doFlush();
	}
	
	/**
	 * Flushes the contents of the cache to the GPU
	 * 
	 */
	public final void flush(){
		//Call the overriden flush
		doFlush();
		
		//clean up after flush
		postFlush();
		
	}
	
	protected abstract void doFlush();
	
	protected void postFlush(){
		
		flushed = true;
	}
	
	/**
	 * The act of drawing.  The Batch will check if it's possible to render
	 * as well as setup for rendering.  If it's possible to render, it will call doRender()
	 * 
	 *  
	 */
	protected abstract void doRender();
	
	
	/**
	 * Renders the batch. 
	 */
	public final void render(){
		checkRender();
		
		doRender();
		
		
	}
	
	protected void checkRender(){
		if(batching) throw new IllegalStateException("Cannot Render While Batching");
		if(!flushed) throw new IllegalStateException("Cannon Render Without Flushing the Batch");		
	}
	
	
	public void AddVertex(float x, float y, float z){
		vertexBuffer.add(x);
		vertexBuffer.add(y);
		vertexBuffer.add(z);
		vertexBuffer.add(1.0f);
	}
	public void AddVertex(float x, float y, float z, float w){
		vertexBuffer.add(x);
		vertexBuffer.add(y);
		vertexBuffer.add(z);
		vertexBuffer.add(w);
	}

	public void AddVertex(float x, float y){
		vertexBuffer.add(x);
		vertexBuffer.add(y);
		vertexBuffer.add(1.0f);
		vertexBuffer.add(1.0f);
	}
	
	public void AddVertex(Vector3f vertex){
		AddVertex(vertex.x, vertex.y, vertex.z);
	}
	public void AddVertex(Vector2f vertex){
		AddVertex(vertex.x, vertex.y);
	}
	public void AddVertex(Vector4f vertex){
		AddVertex(vertex.x, vertex.y, vertex.z, vertex.w);
	}
	
	public void AddColor(float r, float g, float b){
		colorBuffer.add(r);
		colorBuffer.add(g);
		colorBuffer.add(b);
		colorBuffer.add(1.0f);
	}
	public void AddColor(float r, float g, float b, float a){
		colorBuffer.add(r);
		colorBuffer.add(g);
		colorBuffer.add(b);
		colorBuffer.add(a);
	}
	
	public void AddNormal(float x, float y, float z){
		normalBuffer.add(x);
		normalBuffer.add(y);
		normalBuffer.add(z);
		normalBuffer.add(1.0f);
	}
	public void AddNormal(float x, float y, float z, float w){
		normalBuffer.add(x);
		normalBuffer.add(y);
		normalBuffer.add(z);
		normalBuffer.add(w);
	}

	public void AddNormal(float x, float y){
		normalBuffer.add(x);
		normalBuffer.add(y);
		normalBuffer.add(1.0f);
		normalBuffer.add(1.0f);
	}
	
	public void AddNormal(Vector3f vertex){
		AddVertex(vertex.x, vertex.y, vertex.z);
	}
	public void AddNormal(Vector2f vertex){
		AddVertex(vertex.x, vertex.y);
	}
	public void AddNormal(Vector4f vertex){
		AddVertex(vertex.x, vertex.y, vertex.z, vertex.w);
	}
	
	public void setShader(Shader shader){
		if(shader == null){		
			try {
				activeShader = new EmptyShader();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else activeShader = shader;
	}
	
	
	public void enableColors(){
		useColors = true;
	}
	
	public void enableNormals(){
		useNormals = true;
	}
	
}
