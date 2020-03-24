#version 310 es
precision mediump float;
precision highp int;

layout(location = 0) out highp vec4 color;
layout(location = 0) in highp vec4 v_color;

void main()
{
    color = v_color;
}

