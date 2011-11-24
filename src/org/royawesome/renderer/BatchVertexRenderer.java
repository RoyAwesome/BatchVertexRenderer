package org.royawesome.renderer;
import gnu.trove.list.array.TFloatArrayList;
import org.lwjgl.util.vector.Vector3f;

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
	
	
	boolean useColors = false;
	
	
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
		flush();
	}
	
	protected void flush(){
		
		flushed = true;
	}
	
	/**
	 * Draws this batch
	 */
	public abstract void render();
	
	protected void checkRender(){
		if(batching) throw new IllegalStateException("Cannot Render While Batching");
		if(!flushed) throw new IllegalStateException("Cannon Render Without Flushing the Batch");		
	}
	
	
	public void AddVertex(float x, float y, float z){
		vertexBuffer.add(x);
		vertexBuffer.add(y);
		vertexBuffer.add(z);
	}
	
	public void AddVertex(Vector3f vertex){
		AddVertex(vertex.x, vertex.y, vertex.z);
	}
	
	public void AddColor(float r, float g, float b){
		colorBuffer.add(r);
		colorBuffer.add(g);
		colorBuffer.add(b);
	}
	
	public void enableColors(){
		useColors = true;
	}
	
}
