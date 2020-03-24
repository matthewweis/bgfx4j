

The compile_shaders scripts located in this file automatically do the following:

(setup) Run scripts from the bgfx4j/scripts directory...
(1) Script will check if correct tools are built in bgfx4j/bgfx git submodule, if not it will build them
(2) Script will find all file triplets of the format: <name>.defsc, <name>.fragsc, <name>.vertsc and compile them to shaders
(3) The compiled shaders will be placed as vs_<name>.bin and fs_<name>.bin in their correct target subdirectory.


Input files are found ONLY in the directory indicated by the "INDIR" variable at the top of the script.
Output files are found ONLY in the directory indicated by the "OUTDIR" variable at the top of the script.