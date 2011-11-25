package org.royawesome.renderer;
import org.lwjgl.opengl.*;

public class GL11BatchVertexRenderer extends BatchVertexRenderer {
	
	int displayList;
	

	public GL11BatchVertexRenderer(int mode){
		super(mode);
		displayList = GL11.glGenLists(1);
		
	}
	

	protected void doFlush(){
		if( vertexBuffer.size() % 4 != 0) throw new IllegalStateException("Vertex Size Mismatch (How did this happen?)");
		if( useColors){
			if(colorBuffer.size() % 4 != 0) throw new IllegalStateException("Color Size Mismatch (How did this happen?)");
			if(vertexBuffer.size() != colorBuffer.size()) throw new IllegalStateException("Vertex Buffer Size and Color Buffer Size do not match");
			
		}
		
			
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		GL11.glBegin(renderMode);
		for(int i = 0; i < vertexBuffer.size() / 4; i+= 1){
			int index = i *4;
			if(useColors) GL11.glColor3f(colorBuffer.get(index), colorBuffer.get(index+1), colorBuffer.get(index+2));
			if(useNormals) GL11.glNormal3f(normalBuffer.get(index), normalBuffer.get(index + 1), normalBuffer.get(index + 2));
			GL11.glVertex4f(vertexBuffer.get(index), vertexBuffer.get(index+1), vertexBuffer.get(index+2), vertexBuffer.get(index+3));
		}
		GL11.glEnd();
		GL11.glEndList();
		
	}
	
	
	
	@Override
	public void doRender() {
		
		
		GL11.glCallList(displayList);
		
		
	}
	
	

}
