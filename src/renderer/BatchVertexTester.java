package renderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class BatchVertexTester {
	public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(800,600));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}

	// init OpenGL here
	BatchVertexRenderer renderer = new GL11BatchVertexRenderer(GL11.GL_TRIANGLES);
	renderer.enableColors();
	renderer.begin();
		renderer.AddColor(1.0f, 0.0f, 0.0f);
		renderer.AddVertex(.5f, .5f, 0.0f);
		renderer.AddColor(0.0f, 1.0f, 0.0f);
		renderer.AddVertex(.2f, .8f, 0.0f);
		renderer.AddColor(0.0f, 0.0f, 1.0f);
		renderer.AddVertex(.8f, .8f, 0.0f);
	renderer.end();
	
	while (!Display.isCloseRequested()) {
	
	    // render OpenGL here
		renderer.render();
				
	    Display.update();
	}
		
	Display.destroy();
    }
	
    public static void main(String[] argv) {
    	BatchVertexTester displayExample = new BatchVertexTester();
	displayExample.start();
    }
}
