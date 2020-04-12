#!/usr/bin/env bash

# compile shaders into spirv intermediate language using glslangValidator
./compile_shaders_spirv.sh

# transpile generated spirv into shaders of other languages
./transpile_spirv_glsl.sh
./transpile_spirv_glsl_es.sh
./transpile_spirv_hlsl.sh
./transpile_spirv_metal.sh