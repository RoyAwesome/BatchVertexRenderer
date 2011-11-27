attribute vec4 vPosition;  //in
attribute vec4 vColor;   //in
varying vec4 color;   //out

uniform mat4 Projection;

void main()
{
    gl_Position = Projection * vPosition;
    color = vColor;
}
 