package com.bariumhoof.bgfx4j.enums;

import static org.lwjgl.bgfx.BGFX.*;

public enum BGFX_PCI_ID {
    NONE(BGFX_PCI_ID_NONE),
    SOFTWARE_RASTERIZER(BGFX_PCI_ID_SOFTWARE_RASTERIZER),
    AMD(BGFX_PCI_ID_AMD),
    INTEL(BGFX_PCI_ID_INTEL),
    NVIDIA(BGFX_PCI_ID_NVIDIA);

    public final short VALUE;

    BGFX_PCI_ID(short value) {
        this.VALUE = value;
    }
}