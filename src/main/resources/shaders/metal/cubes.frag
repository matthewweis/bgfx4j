FSH���I      l  #include <metal_stdlib>
#include <simd/simd.h>

using namespace metal;

struct xlatMtlMain_out
{
    float4 bgfx_FragData0 [[color(0)]];
};

struct xlatMtlMain_in
{
    float4 v_color0 [[user(locn0)]];
};

fragment xlatMtlMain_out xlatMtlMain(xlatMtlMain_in in [[stage_in]])
{
    xlatMtlMain_out out = {};
    out.bgfx_FragData0 = in.v_color0;
    return out;
}

    