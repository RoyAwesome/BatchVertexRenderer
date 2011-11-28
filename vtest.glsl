attribute vec4 vPosition;  //in
attribute vec4 vColor;   //in
attribute vec2 vTexCoord;


varying vec4 color;   //out
varying vec2 uvcoord;

uniform mat4 Projection;

void main()
{

    gl_Position = Projection * vPosition;
	//gl_Position = vPosition;
	uvcoord = vTexCoord;
	color = vColor;
	
}
 