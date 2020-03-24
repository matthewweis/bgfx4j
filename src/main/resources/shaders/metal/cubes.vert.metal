#include <metal_stdlib>
#include <simd/simd.h>

using namespace metal;

struct main0_out
{
    float4 v_color0 [[user(locn0)]];
    float4 gl_Position [[position]];
};

struct main0_in
{
    float3 position [[attribute(0)]];
    float4 a_color0 [[attribute(1)]];
};

vertex main0_out main0(main0_in in [[stage_in]])
{
    main0_out out = {};
    out.gl_Position = float4(in.position, 1.0);
    out.v_color0 = in.a_color0;
    return out;
}

