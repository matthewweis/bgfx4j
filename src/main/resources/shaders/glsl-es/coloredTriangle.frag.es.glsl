#version 310 es
precision mediump float;
precision highp int;

layout(location = 0) out highp vec4 color;
layout(location = 0) in highp vec3 outColor;

void main()
{
    color = vec4(outColor, 1.0);
}

