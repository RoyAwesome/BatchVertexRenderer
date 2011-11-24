attribute vec4 vPosition;  //in
attribute vec4 vColor;   //in
varying vec4 color;   //out

void main()
{
    gl_Position = vPosition;
    color = vColor;
}
 