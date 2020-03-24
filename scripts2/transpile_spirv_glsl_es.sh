#!/usr/bin/env bash

### create directory
mkdir -p "../src/main/resources/shaders/glsl/"

### cleanup
for file in ../src/main/resources/shaders/glsl/*.vert.glsl; do
  echo "removing ${file}"
  rm ${file}
done

for file in ../src/main/resources/shaders/glsl/*.frag.glsl; do
  echo "removing ${file}"
  rm ${file}
done

### shader gen
echo "generating shaders..."

for file in ../src/main/resources/shaders/spirv/*.vert.spv; do
  echo "transpiling ${file} to glsl"
  baseName=$(basename ${file} ".vert.spv")
  spirv-cross ${file} --version 450 --no-es --output ../src/main/resources/shaders/glsl/${baseName}.vert.glsl
done

for file in ../src/main/resources/shaders/spirv/*.frag.spv; do
  echo "transpiling ${file} to glsl"
  baseName=$(basename ${file} ".frag.spv")
  spirv-cross ${file} --version 450 --no-es --output ../src/main/resources/shaders/glsl/${baseName}.vert.glsl
done