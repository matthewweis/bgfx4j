#!/usr/bin/env bash

### create directory
mkdir -p "../src/main/resources/shaders/glsl-es/"

### cleanup
for file in ../src/main/resources/shaders/glsl-es/*.vert.es.glsl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

for file in ../src/main/resources/shaders/glsl-es/*.frag.es.glsl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

### shader gen
echo "generating shaders..."

for file in ../src/main/resources/shaders/spirv/*.vert.spv; do
  echo "transpiling ${file} to glsl-es"
  baseName=$(basename ${file} ".vert.spv")
  spirv-cross ${file} --version 310 --es --output ../src/main/resources/shaders/glsl-es/${baseName}.vert.es.glsl
done

for file in ../src/main/resources/shaders/spirv/*.frag.spv; do
  echo "transpiling ${file} to glsl-es"
  baseName=$(basename ${file} ".frag.spv")
  spirv-cross ${file} --version 310 --es --output ../src/main/resources/shaders/glsl-es/${baseName}.frag.es.glsl
done