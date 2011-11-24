package renderer;
import org.lwjgl.opengl.*;

public class GL11BatchVertexRenderer extends BatchVertexRenderer {
	
	int displayList;
	

	public GL11BatchVertexRenderer(int mode){
		super(mode);
		displayList = GL11.glGenLists(1);
		
	}
	

	protected void flush(){
		super.flush();
		if( vertexBuffer.size() % 3 != 0) throw new IllegalStateException("Vertex Stride Mismatch (How did this happen?)");
		if( useColors){
			if(colorBuffer.size() % 3 != 0) throw new IllegalStateException("Color Stride Mismatch (colorBuffer size should be divisible by 3)");
			if(vertexBuffer.size() != colorBuffer.size()) throw new IllegalStateException("Vertex Buffer Size and Color Buffer Size do not match");
			
		}
			
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		GL11.glBegin(renderMode);
		for(int i = 0; i < vertexBuffer.size(); i+= 3){
			if(useColors) GL11.glColor3f(colorBuffer.get(i), colorBuffer.get(i+1), colorBuffer.get(i+2));
			GL11.glVertex3f(vertexBuffer.get(i), vertexBuffer.get(i+1), vertexBuffer.get(i+2));
		}
		GL11.glEnd();
		GL11.glEndList();
		
	}
	
	@Override
	public void render() {
		checkRender();
		
		GL11.glCallList(displayList);
		
		
	}
	
	

}
