package com.bariumhoof.bgfx4j.layout;

import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;

public interface BgfxAttribType {

    UINT8 UINT8 = new UINT8(){};
    UINT10 UINT10 = new UINT10(){};
    INT16 INT16 = new INT16(){};
    HALF HALF = new HALF(){};
    FLOAT FLOAT = new FLOAT(){};

    BGFX_ATTRIB_TYPE representedType();

    interface UINT8 extends BgfxAttribType { default BGFX_ATTRIB_TYPE representedType(){ return BGFX_ATTRIB_TYPE.UINT8; } }
    interface UINT10 extends BgfxAttribType { default BGFX_ATTRIB_TYPE representedType(){ return BGFX_ATTRIB_TYPE.UINT10; } }
    interface INT16 extends BgfxAttribType { default BGFX_ATTRIB_TYPE representedType(){ return BGFX_ATTRIB_TYPE.INT16; } }
    interface HALF extends BgfxAttribType { default BGFX_ATTRIB_TYPE representedType(){ return BGFX_ATTRIB_TYPE.HALF; } }
    interface FLOAT extends BgfxAttribType { default BGFX_ATTRIB_TYPE representedType(){ return BGFX_ATTRIB_TYPE.FLOAT; } }
}








