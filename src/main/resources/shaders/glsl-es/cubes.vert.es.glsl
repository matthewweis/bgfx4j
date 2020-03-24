#version 310 es

layout(location = 0) in vec3 position;
layout(location = 0) out vec4 v_color0;
layout(location = 1) in vec4 a_color0;

void main()
{
    gl_Position = vec4(position, 1.0);
    v_color0 = a_color0;
}

