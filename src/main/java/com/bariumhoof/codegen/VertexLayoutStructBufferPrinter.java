//package com.bariumhoof.codegen;
//
//public class VertexLayoutStructBufferPrinter {
//
//    private static final int NUM_STAGES = 18;
//
//    private static final String current = "\\+";
//    private static final String next = "\\$";
//    private static final String last = "\\-";
//    private static final String lastExpansion = "E\\%, T\\%";
//    private static final String nextExpansion = "E\\^, T\\^";
//    private static final String expansion1 = "E\\*, T\\*";
//    private static final String expansion2 = "E\\* extends BGFX_ATTRIB, T\\* extends BGFX_ATTRIB_TYPE";
//
//    public static final String template = "/** The struct size in bytes. */\n" +
//            "    public static final int SIZEOF;\n" +
//            "\n" +
//            "    /** The struct alignment in bytes. */\n" +
//            "    public static final int ALIGNOF;\n" +
//            "\n" +
//            "    /** The struct member offsets. */\n" +
//            "    public static final int\n" +
//            "        X,\n" +
//            "        Y,\n" +
//            "        Z;\n" +
//            "\n" +
//            "    static {\n" +
//            "        Layout layout = __struct(\n" +
//            "            __member(4),\n" +
//            "            __member(4),\n" +
//            "            __member(4)\n" +
//            "        );\n" +
//            "\n" +
//            "        SIZEOF = layout.getSize();\n" +
//            "        ALIGNOF = layout.getAlignment();\n" +
//            "\n" +
//            "        X = layout.offsetof(0);\n" +
//            "        Y = layout.offsetof(1);\n" +
//            "        Z = layout.offsetof(2);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Creates a {@code AIVector3D} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be\n" +
//            "     * visible to the struct instance and vice versa.\n" +
//            "     *\n" +
//            "     * <p>The created instance holds a strong reference to the container object.</p>\n" +
//            "     */\n" +
//            "    public AIVector3D(ByteBuffer container) {\n" +
//            "        super(memAddress(container), __checkContainer(container, SIZEOF));\n" +
//            "    }\n" +
//            "\n" +
//            "    @Override\n" +
//            "    public int sizeof() { return SIZEOF; }\n" +
//            "\n" +
//            "    /** Returns the value of the {@code x} field. */\n" +
//            "    public float x() { return nx(address()); }\n" +
//            "    /** Returns the value of the {@code y} field. */\n" +
//            "    public float y() { return ny(address()); }\n" +
//            "    /** Returns the value of the {@code z} field. */\n" +
//            "    public float z() { return nz(address()); }\n" +
//            "\n" +
//            "    /** Sets the specified value to the {@code x} field. */\n" +
//            "    public AIVector3D x(float value) { nx(address(), value); return this; }\n" +
//            "    /** Sets the specified value to the {@code y} field. */\n" +
//            "    public AIVector3D y(float value) { ny(address(), value); return this; }\n" +
//            "    /** Sets the specified value to the {@code z} field. */\n" +
//            "    public AIVector3D z(float value) { nz(address(), value); return this; }\n" +
//            "\n" +
//            "    /** Initializes this struct with the specified values. */\n" +
//            "    public AIVector3D set(\n" +
//            "        float x,\n" +
//            "        float y,\n" +
//            "        float z\n" +
//            "    ) {\n" +
//            "        x(x);\n" +
//            "        y(y);\n" +
//            "        z(z);\n" +
//            "\n" +
//            "        return this;\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Copies the specified struct data to this struct.\n" +
//            "     *\n" +
//            "     * @param src the source struct\n" +
//            "     *\n" +
//            "     * @return this struct\n" +
//            "     */\n" +
//            "    public AIVector3D set(AIVector3D src) {\n" +
//            "        memCopy(src.address(), address(), SIZEOF);\n" +
//            "        return this;\n" +
//            "    }\n" +
//            "\n" +
//            "    // -----------------------------------\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */\n" +
//            "    public static AIVector3D malloc() {\n" +
//            "        return wrap(AIVector3D.class, nmemAllocChecked(SIZEOF));\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */\n" +
//            "    public static AIVector3D calloc() {\n" +
//            "        return wrap(AIVector3D.class, nmemCallocChecked(1, SIZEOF));\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance allocated with {@link BufferUtils}. */\n" +
//            "    public static AIVector3D create() {\n" +
//            "        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);\n" +
//            "        return wrap(AIVector3D.class, memAddress(container), container);\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance for the specified memory address. */\n" +
//            "    public static AIVector3D create(long address) {\n" +
//            "        return wrap(AIVector3D.class, address);\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */\n" +
//            "    @Nullable\n" +
//            "    public static AIVector3D createSafe(long address) {\n" +
//            "        return address == NULL ? null : wrap(AIVector3D.class, address);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.\n" +
//            "     *\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer malloc(int capacity) {\n" +
//            "        return wrap(Buffer.class, nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.\n" +
//            "     *\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer calloc(int capacity) {\n" +
//            "        return wrap(Buffer.class, nmemCallocChecked(capacity, SIZEOF), capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated with {@link BufferUtils}.\n" +
//            "     *\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer create(int capacity) {\n" +
//            "        ByteBuffer container = __create(capacity, SIZEOF);\n" +
//            "        return wrap(Buffer.class, memAddress(container), capacity, container);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Create a {@link AIVector3D.Buffer} instance at the specified memory.\n" +
//            "     *\n" +
//            "     * @param address  the memory address\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer create(long address, int capacity) {\n" +
//            "        return wrap(Buffer.class, address, capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */\n" +
//            "    @Nullable\n" +
//            "    public static AIVector3D.Buffer createSafe(long address, int capacity) {\n" +
//            "        return address == NULL ? null : wrap(Buffer.class, address, capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    // -----------------------------------\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance allocated on the thread-local {@link MemoryStack}. */\n" +
//            "    public static AIVector3D mallocStack() {\n" +
//            "        return mallocStack(stackGet());\n" +
//            "    }\n" +
//            "\n" +
//            "    /** Returns a new {@code AIVector3D} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero. */\n" +
//            "    public static AIVector3D callocStack() {\n" +
//            "        return callocStack(stackGet());\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@code AIVector3D} instance allocated on the specified {@link MemoryStack}.\n" +
//            "     *\n" +
//            "     * @param stack the stack from which to allocate\n" +
//            "     */\n" +
//            "    public static AIVector3D mallocStack(MemoryStack stack) {\n" +
//            "        return wrap(AIVector3D.class, stack.nmalloc(ALIGNOF, SIZEOF));\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@code AIVector3D} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.\n" +
//            "     *\n" +
//            "     * @param stack the stack from which to allocate\n" +
//            "     */\n" +
//            "    public static AIVector3D callocStack(MemoryStack stack) {\n" +
//            "        return wrap(AIVector3D.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated on the thread-local {@link MemoryStack}.\n" +
//            "     *\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer mallocStack(int capacity) {\n" +
//            "        return mallocStack(capacity, stackGet());\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero.\n" +
//            "     *\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer callocStack(int capacity) {\n" +
//            "        return callocStack(capacity, stackGet());\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated on the specified {@link MemoryStack}.\n" +
//            "     *\n" +
//            "     * @param stack the stack from which to allocate\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer mallocStack(int capacity, MemoryStack stack) {\n" +
//            "        return wrap(Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    /**\n" +
//            "     * Returns a new {@link AIVector3D.Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.\n" +
//            "     *\n" +
//            "     * @param stack the stack from which to allocate\n" +
//            "     * @param capacity the buffer capacity\n" +
//            "     */\n" +
//            "    public static AIVector3D.Buffer callocStack(int capacity, MemoryStack stack) {\n" +
//            "        return wrap(Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);\n" +
//            "    }\n" +
//            "\n" +
//            "    // -----------------------------------\n" +
//            "\n" +
//            "    /** Unsafe version of {@link #x}. */\n" +
//            "    public static float nx(long struct) { return UNSAFE.getFloat(null, struct + AIVector3D.X); }\n" +
//            "    /** Unsafe version of {@link #y}. */\n" +
//            "    public static float ny(long struct) { return UNSAFE.getFloat(null, struct + AIVector3D.Y); }\n" +
//            "    /** Unsafe version of {@link #z}. */\n" +
//            "    public static float nz(long struct) { return UNSAFE.getFloat(null, struct + AIVector3D.Z); }\n" +
//            "\n" +
//            "    /** Unsafe version of {@link #x(float) x}. */\n" +
//            "    public static void nx(long struct, float value) { UNSAFE.putFloat(null, struct + AIVector3D.X, value); }\n" +
//            "    /** Unsafe version of {@link #y(float) y}. */\n" +
//            "    public static void ny(long struct, float value) { UNSAFE.putFloat(null, struct + AIVector3D.Y, value); }\n" +
//            "    /** Unsafe version of {@link #z(float) z}. */\n" +
//            "    public static void nz(long struct, float value) { UNSAFE.putFloat(null, struct + AIVector3D.Z, value); }\n" +
//            "\n" +
//            "    // -----------------------------------\n" +
//            "\n" +
//            "    /** An array of {@link AIVector3D} structs. */\n" +
//            "    public static class Buffer extends StructBuffer<AIVector3D, Buffer> implements NativeResource {\n" +
//            "\n" +
//            "        private static final AIVector3D ELEMENT_FACTORY = AIVector3D.create(-1L);\n" +
//            "\n" +
//            "        /**\n" +
//            "         * Creates a new {@code AIVector3D.Buffer} instance backed by the specified container.\n" +
//            "         *\n" +
//            "         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values\n" +
//            "         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided\n" +
//            "         * by {@link AIVector3D#SIZEOF}, and its mark will be undefined.\n" +
//            "         *\n" +
//            "         * <p>The created buffer instance holds a strong reference to the container object.</p>\n" +
//            "         */\n" +
//            "        public Buffer(ByteBuffer container) {\n" +
//            "            super(container, container.remaining() / SIZEOF);\n" +
//            "        }\n" +
//            "\n" +
//            "        public Buffer(long address, int cap) {\n" +
//            "            super(address, null, -1, 0, cap, cap);\n" +
//            "        }\n" +
//            "\n" +
//            "        Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {\n" +
//            "            super(address, container, mark, pos, lim, cap);\n" +
//            "        }\n" +
//            "\n" +
//            "        @Override\n" +
//            "        protected Buffer self() {\n" +
//            "            return this;\n" +
//            "        }\n" +
//            "\n" +
//            "        @Override\n" +
//            "        protected AIVector3D getElementFactory() {\n" +
//            "            return ELEMENT_FACTORY;\n" +
//            "        }\n" +
//            "\n" +
//            "        /** Returns the value of the {@code x} field. */\n" +
//            "        public float x() { return AIVector3D.nx(address()); }\n" +
//            "        /** Returns the value of the {@code y} field. */\n" +
//            "        public float y() { return AIVector3D.ny(address()); }\n" +
//            "        /** Returns the value of the {@code z} field. */\n" +
//            "        public float z() { return AIVector3D.nz(address()); }\n" +
//            "\n" +
//            "        /** Sets the specified value to the {@code x} field. */\n" +
//            "        public AIVector3D.Buffer x(float value) { AIVector3D.nx(address(), value); return this; }\n" +
//            "        /** Sets the specified value to the {@code y} field. */\n" +
//            "        public AIVector3D.Buffer y(float value) { AIVector3D.ny(address(), value); return this; }\n" +
//            "        /** Sets the specified value to the {@code z} field. */\n" +
//            "        public AIVector3D.Buffer z(float value) { AIVector3D.nz(address(), value); return this; }\n" +
//            "\n" +
//            "    }"
//}
