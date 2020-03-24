uniform float4 gl_HalfPixel;

static float4 gl_Position;
static float2 position;

struct SPIRV_Cross_Input
{
    float2 position : TEXCOORD0;
};

struct SPIRV_Cross_Output
{
    float4 gl_Position : POSITION;
};

void vert_main()
{
    gl_Position = float4(position, 0.0f, 1.0f);
    gl_Position.x = gl_Position.x - gl_HalfPixel.x * gl_Position.w;
    gl_Position.y = gl_Position.y + gl_HalfPixel.y * gl_Position.w;
}

SPIRV_Cross_Output main(SPIRV_Cross_Input stage_input)
{
    position = stage_input.position;
    vert_main();
    SPIRV_Cross_Output stage_output;
    stage_output.gl_Position = gl_Position;
    return stage_output;
}
