static float4 color;
static float3 outColor;

struct SPIRV_Cross_Input
{
    float3 outColor : TEXCOORD0;
};

struct SPIRV_Cross_Output
{
    float4 color : COLOR0;
};

void frag_main()
{
    color = float4(outColor, 1.0f);
}

SPIRV_Cross_Output main(SPIRV_Cross_Input stage_input)
{
    outColor = stage_input.outColor;
    frag_main();
    SPIRV_Cross_Output stage_output;
    stage_output.color = float4(color);
    return stage_output;
}
