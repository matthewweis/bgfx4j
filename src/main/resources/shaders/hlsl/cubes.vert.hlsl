uniform float4 gl_HalfPixel;

static float4 gl_Position;
static float3 position;
static float4 v_color0;
static float4 a_color0;

struct SPIRV_Cross_Input
{
    float3 position : TEXCOORD0;
    float4 a_color0 : TEXCOORD1;
};

struct SPIRV_Cross_Output
{
    float4 v_color0 : TEXCOORD0;
    float4 gl_Position : POSITION;
};

void vert_main()
{
    gl_Position = float4(position, 1.0f);
    v_color0 = a_color0;
    gl_Position.x = gl_Position.x - gl_HalfPixel.x * gl_Position.w;
    gl_Position.y = gl_Position.y + gl_HalfPixel.y * gl_Position.w;
}

SPIRV_Cross_Output main(SPIRV_Cross_Input stage_input)
{
    position = stage_input.position;
    a_color0 = stage_input.a_color0;
    vert_main();
    SPIRV_Cross_Output stage_output;
    stage_output.gl_Position = gl_Position;
    stage_output.v_color0 = v_color0;
    return stage_output;
}
