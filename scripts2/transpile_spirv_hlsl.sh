#!/usr/bin/env bash

### create directory
mkdir -p "../src/main/resources/shaders/hlsl/"

### cleanup
for file in ../src/main/resources/shaders/hlsl/*.vert.hlsl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

for file in ../src/main/resources/shaders/hlsl/*.frag.hlsl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

### shader gen
echo "generating shaders..."

for file in ../src/main/resources/shaders/spirv/*.vert.spv; do
  echo "transpiling ${file} to hlsl"
  baseName=$(basename ${file} ".vert.spv")
  spirv-cross ${file} --hlsl --output ../src/main/resources/shaders/hlsl/${baseName}.vert.hlsl
done

for file in ../src/main/resources/shaders/spirv/*.frag.spv; do
  echo "transpiling ${file} to hlsl"
  baseName=$(basename ${file} ".frag.spv")
  spirv-cross ${file} --hlsl --output ../src/main/resources/shaders/hlsl/${baseName}.frag.hlsl
done