package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;


// todo make it an abstract class with package-private constructor?
public interface BgfxAttrib {

    POSITION POSITION = new POSITION(){};
    NORMAL NORMAL = new NORMAL(){};
    TANGENT TANGENT = new TANGENT(){};
    BITANGENT BITANGENT = new BITANGENT(){};
    COLOR0 COLOR0 = new COLOR0(){};
    COLOR1 COLOR1 = new COLOR1(){};
    COLOR2 COLOR2 = new COLOR2(){};
    COLOR3 COLOR3 = new COLOR3(){};
    INDICES INDICES = new INDICES(){};
    WEIGHT WEIGHT = new WEIGHT(){};
    TEXCOORD0 TEXCOORD0 = new TEXCOORD0(){};
    TEXCOORD1 TEXCOORD1 = new TEXCOORD1(){};
    TEXCOORD2 TEXCOORD2 = new TEXCOORD2(){};
    TEXCOORD3 TEXCOORD3 = new TEXCOORD3(){};
    TEXCOORD4 TEXCOORD4 = new TEXCOORD4(){};
    TEXCOORD5 TEXCOORD5 = new TEXCOORD5(){};
    TEXCOORD6 TEXCOORD6 = new TEXCOORD6(){};
    TEXCOORD7 TEXCOORD7 = new TEXCOORD7(){};

    BGFX_ATTRIB representedType();

    interface POSITION extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.POSITION; } }
    interface NORMAL extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.NORMAL; } }
    interface TANGENT extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TANGENT; } }
    interface BITANGENT extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.BITANGENT; } }
    interface COLOR0 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.COLOR0; } }
    interface COLOR1 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.COLOR1; } }
    interface COLOR2 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.COLOR2; } }
    interface COLOR3 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.COLOR3; } }
    interface INDICES extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.INDICES; } }
    interface WEIGHT extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.WEIGHT; } }
    interface TEXCOORD0 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD0; } }
    interface TEXCOORD1 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD1; } }
    interface TEXCOORD2 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD2; } }
    interface TEXCOORD3 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD3; } }
    interface TEXCOORD4 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD4; } }
    interface TEXCOORD5 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD5; } }
    interface TEXCOORD6 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD6; } }
    interface TEXCOORD7 extends BgfxAttrib { default BGFX_ATTRIB representedType(){ return BGFX_ATTRIB.TEXCOORD7; } }
}