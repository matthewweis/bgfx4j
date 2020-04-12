#version 450 core

//layout(location=0) in vec4 v_color;
//layout(location=0) out vec4 color;

in vec4 v_color;
out vec4 color;

void main(void) {
	color = v_color;
}
