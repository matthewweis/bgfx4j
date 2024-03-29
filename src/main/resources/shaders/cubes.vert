#version 450 core

//struct TheStruct {
//	mat4 modelviewProj;
//};

//layout(location=0) in vec3 position;
//layout(location=1) in vec4 a_color0;
//layout(location=0) out vec4 v_color0;

//VSH    ���I u_modelViewProj     attribute vec4 a_color0;
//attribute vec3 a_position;
//varying vec4 v_color0;
//uniform mat4 u_modelViewProj;

//attribute vec3 position;
//attribute vec4 a_color0;
//varying vec4 v_color0;
//uniform mat4 u_modelViewProj;

in vec3 position;
in vec4 a_color0;
out vec4 v_color0;

//uniform mat4 modelViewProj;
//layout(set=0, location=2) uniform mat4 modelviewProj;

void main(void) {
	//	gl_Position = vec4(position, 1.0);
//	gl_Position = mul(modelViewProj, vec4(position, 1.0) );
	gl_Position = modelviewProj * vec4(position, 1.0);
	v_color0 = a_color0;
}
