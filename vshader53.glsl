attribute  vec4 vPosition;
attribute  vec3 vNormal;
attribute  vec4 vColor;
attribute  vec2 vTexCoord;


varying vec4 color;
varying vec2 uv;


uniform mat4 View;
uniform mat4 Projection;




void main()
{
    // Transform vertex  position into eye coordinates
    
	color = vColor;
    gl_Position = Projection * ModelView * vPosition;

   
}