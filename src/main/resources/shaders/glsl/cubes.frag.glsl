#version 450

layout(location = 0) out vec4 color;
layout(location = 0) in vec4 v_color;

void main()
{
    color = v_color;
}

