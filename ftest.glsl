varying vec4 color;  //in
varying vec2 uvcoord;

uniform sampler2D texture;

void main()
{
    gl_FragColor =  texture2D(texture, uvcoord);
}
