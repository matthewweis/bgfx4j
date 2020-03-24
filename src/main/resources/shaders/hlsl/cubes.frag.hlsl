static float4 color;
static float4 v_color;

struct SPIRV_Cross_Input
{
    float4 v_color : TEXCOORD0;
};

struct SPIRV_Cross_Output
{
    float4 color : COLOR0;
};

void frag_main()
{
    color = v_color;
}

SPIRV_Cross_Output main(SPIRV_Cross_Input stage_input)
{
    v_color = stage_input.v_color;
    frag_main();
    SPIRV_Cross_Output stage_output;
    stage_output.color = float4(color);
    return stage_output;
}
