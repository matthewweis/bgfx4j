static float4 color;

struct SPIRV_Cross_Output
{
    float4 color : COLOR0;
};

void frag_main()
{
    color = float4(0.20000000298023223876953125f, 0.4000000059604644775390625f, 0.60000002384185791015625f, 1.0f);
}

SPIRV_Cross_Output main()
{
    frag_main();
    SPIRV_Cross_Output stage_output;
    stage_output.color = float4(color);
    return stage_output;
}
