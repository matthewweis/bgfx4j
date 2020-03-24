#!/usr/bin/env bash

### create directory
mkdir -p "../src/main/resources/shaders/spirv/"

### cleanup
for file in ../src/main/resources/shaders/spirv/*.vert.spv; do
  echo "removing ${file}"
  rm ${file}
done

for file in ../src/main/resources/shaders/spirv/*.frag.spv; do
  echo "removing ${file}"
  rm ${file}
done

### shader gen
echo "generating shaders..."

for file in ../src/main/resources/shaders/*.vert; do
  echo "compiling ${file}"
  baseName=$(basename ${file} ".vert")
  glSlangValidator -V ${file} -o ../src/main/resources/shaders/spirv/${baseName}.vert.spv
done

for file in ../src/main/resources/shaders/*.frag; do
  echo "compiling ${file}"
  baseName=$(basename ${file} ".frag")
  glSlangValidator -V ${file} -o ../src/main/resources/shaders/spirv/${baseName}.frag.spv
done