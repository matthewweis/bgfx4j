#!/usr/bin/env bash


# AUTOMATICALLY FINDS SHADERS FOLLOWING THE name.defsc, name.vertsc, name.fragsc format in resources/shaders and compiles
# / places them in a subdirectory appropriate for their target


### VARIABLES
INDIR="../src/test/resources/shaders/" # directory containing inputs
OUTDIR="../src/test/resources/shaders/" #directory to put outputs (with intermediate profile directory inbetween)

TARGET="dx9" # directory its saved in
FSPROFILE="fs_3_0"  # profile (see comment code below for possibilities)
VSPROFILE="vs_3_0"  # profile (see comment code below for possibilities)
###

dir=$(pwd)

echo "determining os..."
if [[ "$OSTYPE" == "linux-gnu" ]]; then
        PLATFORM="linux"
        SC="../bgfx/tools/bin/linux/shaderc"
elif [[ "$OSTYPE" == "darwin"* ]]; then
        PLATFORM="osx"
        SC="../bgfx/tools/bin/darwin/shaderc"
elif [[ "$OSTYPE" == "msys" ]]; then
        PLATFORM="windows"
        SC="../bgfx/tools/bin/windows/shaderc"
elif [[ "$OSTYPE" == "win32" ]]; then
        PLATFORM="windows" # I'm not sure this can happen.
        SC="../bgfx/tools/bin/windows/shaderc" # I'm not sure this can happen.
else
        echo "ERROR: invalid OS"
        exit 1
fi


SRC="../bgfx/src/"
COMMON="../bgfx/examples/common/"

# FROM shader.mk, PROFILE TO TARGET MATCHING GUIDE

# 0 (hlsl  - d3d9)
#VS_FLAGS=--platform windows -p vs_3_0 -O 3
#FS_FLAGS=--platform windows -p ps_3_0 -O 3
#SHADER_PATH=shaders/dx9

# 1 (hlsl  - d3d11)
#VS_FLAGS=--platform windows -p vs_5_0 -O 3
#FS_FLAGS=--platform windows -p ps_5_0 -O 3
#CS_FLAGS=--platform windows -p cs_5_0 -O 1
#SHADER_PATH=shaders/dx11

# 2 (essl  - nacl)
#VS_FLAGS=--platform nacl
#FS_FLAGS=--platform nacl
#SHADER_PATH=shaders/essl

# 3 (essl  - android)
#VS_FLAGS=--platform android
#FS_FLAGS=--platform android
#CS_FLAGS=--platform android
#SHADER_PATH=shaders/essl

# 4 (glsl)
#VS_FLAGS=--platform linux -p 120
#FS_FLAGS=--platform linux -p 120
#CS_FLAGS=--platform linux -p 430
#SHADER_PATH=shaders/glsl

# 5 (metal)
#VS_FLAGS=--platform osx -p metal
#FS_FLAGS=--platform osx -p metal
#CS_FLAGS=--platform osx -p metal
#SHADER_PATH=shaders/metal

# 6 (pssl)
#VS_FLAGS=--platform orbis -p pssl
#FS_FLAGS=--platform orbis -p pssl
#CS_FLAGS=--platform orbis -p pssl
#SHADER_PATH=shaders/pssl

# 7 (spriv)
#VS_FLAGS=--platform linux -p spirv
#FS_FLAGS=--platform linux -p spirv
#CS_FLAGS=--platform linux -p spirv
#SHADER_PATH=shaders/spirv

### get os type in proper format
# credit: https://stackoverflow.com/questions/394230/how-to-detect-the-os-from-a-bash-script

### compile tool if needed
if [[ ! -f ${SC} ]]; then
    echo "no shaderc binary detected! compiling..."
    cd ../bgfx/
    Make tools
    cd ${dir}
fi

### create directory
mkdir -p ${OUTDIR}${TARGET}

### run tool on shaders
dext=".defsc"
fext=".fragsc"
vext=".vertsc"

### shader gen
echo "generating shaders..."
for file in ${INDIR}/*.fragsc; do
    baseName=$(basename ${file} ${fext})
    echo "generating $file"

    ${SC} -f ${file} -o ${OUTDIR}${TARGET}/fs_${baseName}.bin --platform ${PLATFORM} -p ${FSPROFILE} --type f --varyingdef ${OUTDIR}${baseName}${dext} -i ${COMMON} -i ${SRC} -O 3
done

for file in ${INDIR}/*.vertsc; do
    baseName=$(basename ${file} ${vext})
    echo "generating $file"

    ${SC} -f ${file} -o ${OUTDIR}${TARGET}/vs_${baseName}.bin --platform ${PLATFORM} -p ${VSPROFILE} --type v --varyingdef ${OUTDIR}${baseName}${dext} -i ${COMMON} -i ${SRC} -O 3
done