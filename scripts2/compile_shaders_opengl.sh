#!/usr/bin/env bash

### create directory
mkdir -p "../src/main/resources/shaders/bgfx-metal-bin/"

### cleanup
for file in ../src/main/resources/shaders/bgfx-metal-bin/*.vert.gl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

for file in ../src/main/resources/shaders/bgfx-metal-bin/*.frag.gl; do
  if [ -f ${file} ]; then
    echo "removing ${file}"
    rm ${file}
  fi
done

### shader gen
echo "generating shaders..."

for file in ../src/main/resources/shaders/*.vert; do
  echo "compiling ${file}"
  baseName=$(basename ${file} ".vert")
  glSlangValidator -G ${file} -o ../src/main/resources/shaders/bgfx-metal-bin/${baseName}.vert.gl
done

for file in ../src/main/resources/shaders/*.frag; do
  echo "compiling ${file}"
  baseName=$(basename ${file} ".frag")
  glSlangValidator -G ${file} -o ../src/main/resources/shaders/bgfx-metal-bin/${baseName}.frag.gl
done