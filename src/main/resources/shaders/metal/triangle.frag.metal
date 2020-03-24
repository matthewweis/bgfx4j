#include <metal_stdlib>
#include <simd/simd.h>

using namespace metal;

struct main0_out
{
    float4 color [[color(0)]];
};

fragment main0_out main0()
{
    main0_out out = {};
    out.color = float4(0.20000000298023223876953125, 0.4000000059604644775390625, 0.60000002384185791015625, 1.0);
    return out;
}

