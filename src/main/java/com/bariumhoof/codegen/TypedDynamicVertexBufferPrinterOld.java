package com.bariumhoof.codegen;

import java.util.function.Supplier;

public class TypedDynamicVertexBufferPrinterOld {

    // todo fix update functions!

    private static final int NUM_STAGES = 4;

//    private static final String current = "\\$";
//    private static final String expansion1 = "E\\*, N\\*, T\\*, V\\*";
//    private static final String expansion2 = "E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
//    private static final String expansion3 = "T\\*\\[\\] t\\*";
//    private static final String expansion4 = "changeTo_t\\*";
//    private static final String expansion5 = "E\\*, N\\*, T\\*, V\\* extends Vec<N\\*, T\\*>";

//    private static final String current = "\\$";
//    private static final String expansion2 = "E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
//    private static final String expansion3 = "T\\*\\[\\] t\\*";
//    private static final String expansion4 = "changeTo_t\\*";
////    private static final String expansion5 = "E\\*, N\\*, T\\*, V\\* extends Vec<N\\*, T\\*>";
//    private static final String expansion6 = "<E\\* extends BgfxAttrib>";
//    private static final String expansion7 = "<E\\*, Num";

    private static final String current = "\\$";
//    private static final String expansion1 = "V\\*";
    // E\\* extends BgfxAttrib, T\\* extends BgfxAttribType, N\\* extends Num, V\\* extends Vec<N\\*, T\\*>
    //    private static final String expansion5 = "E\\*, N\\*, T\\*, V\\* extends Vec<N\\*, T\\*>";
//    private static final String expansion7 = "<E\\*, Num";

    // T extends BgfxAttribType, N extends Num, V extends Vec<N, T>

    // these1
    private static final String expansion1 = "E\\*, N\\*, T\\*, V\\*";
    private static final String expansion2 = "<E\\* extends BgfxAttrib, T\\* extends BgfxAttribType, N\\* extends Num, V\\* extends Vec<N\\*, T\\*>>";
    private static final String expansion3 = "V\\*\\[\\] v\\*";
    private static final String expansion4 = "changeTo_v\\*";
//    private static final String expansion5 = "T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
    private static final String expansion6 = "<E\\* extends BgfxAttrib>";

    /*
        private static final String current = "\\+";
    private static final String next = "\\$";
    private static final String last = "-";
    private static final String lastExpansion = "E%, N%, T%, V%";
    private static final String nextExpansion = "E\\^, N\\^, T\\^, V\\^";
    private static final String expansion1 = "E\\*, N\\*, T\\*, V\\*";
    private static final String expansion2 = "E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
     */

    // if a codegen w/ understanding of java was used, then could loop i=1 to N and just know to drop expressions
    // with "-" for i=1 and "$" for i=N (also applies to expansions)

    public static void main(String[] args) {

        System.out.println(header);
        System.out.println();

        final int upperBound = NUM_STAGES + 1;

        for (int i=1; i < upperBound; i++) {
            final String current_n = Integer.toString(i);

            final StringBuilder expansion1_n = new StringBuilder();
            for (int j=1; j <= i; j++) {
                if (j > 1) {
                    expansion1_n.append(", ");
                }
                expansion1_n.append(String.format("E%d, N%d, T%d, V%d", j, j, j, j));
            }

            final StringBuilder expansion2_n = new StringBuilder();
            for (int j=1; j <= i; j++) {
                if (j > 1) {
                    if (j % 2 == 0) {
                        expansion2_n.append(",\n");
                    } else {
                        expansion2_n.append(", ");
                    }
                }
                expansion2_n.append(String.format("<E%d extends BgfxAttrib, T%d extends BgfxAttribType, N%d extends Num, V%d extends Vec<N%d, T%d>>", j, j, j, j, j, j));
            }

            final StringBuilder expansion3_n = new StringBuilder();
            for (int j=1; j <= i; j++) {
                if (j > 1) {
                    if (j % 2 == 0) {
                        expansion3_n.append(",\n");
                    } else {
                        expansion3_n.append(", ");
                    }
                }
                expansion3_n.append(String.format("V%d[] v%d", j, j));
            }

            final StringBuilder expansion4_n = new StringBuilder();
            for (int j=1; j <= i; j++) {
                if (j > 1) {
                    if (j % 2 == 0) {
                        expansion4_n.append(",\n");
                    } else {
                        expansion4_n.append(", ");
                    }
                }
                expansion4_n.append(String.format("v%d", j));
            }

            System.out.println();

            System.out.println(factoryMethodsTemplate
                    .replaceAll(current, current_n)
                    .replaceAll(expansion6,String.format("<E%d extends BgfxAttrib>", i)));
//                    .replaceAll(expansion7, String.format("<E%d, Num", i)));

            System.out.println(classesTemplate
                    .replaceAll(current, current_n)
                    .replaceAll(expansion1, expansion1_n.toString())
                    .replaceAll(expansion2, expansion2_n.toString())
                    .replaceAll(expansion3, expansion3_n.toString())
                    .replaceAll(expansion4, expansion4_n.toString()));

        }

        System.out.println(tail);
    }

    private static final String header =
            "package com.bariumhoof.bgfx4j.layout;\n" +
            "\n" +
            "import com.bariumhoof.assertions.Assertions;\n" +
            "import com.bariumhoof.bgfx4j.Disposable;\n" +
            "import com.bariumhoof.bgfx4j.Handle;\n" +
            "import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB_TYPE;\n" +
            "import com.bariumhoof.bgfx4j.enums.BGFX_BUFFER;\n" +
            "import com.bariumhoof.bgfx4j.layout.Vec.*;\n" +
            "import com.bariumhoof.bgfx4j.layout.VertexLayoutStruct.*;\n" +
            "import lombok.Getter;\n" +
            "import org.jetbrains.annotations.NotNull;\n" +
            "import org.lwjgl.bgfx.BGFXMemory;\n" +
            "import org.lwjgl.system.MemoryStack;\n" +
            "\n" +
            "import java.nio.ByteBuffer;\n" +
            "import java.util.EnumSet;\n" +
            "\n" +
            "import static org.lwjgl.bgfx.BGFX.*;\n" +
            "\n" +
            "// todo autogen for 1 to 18\n" +
            "// todo fix for half and uint10\n" +
            "// todo look into efficiencies of mem system, copy vs ref, and putting things on the stack\n" +
            "public abstract class TypedDynamicVertexBuffer implements Disposable, Handle {\n" +
            "\n" +
            "\n" +
            "    private final short handle;\n" +
            "    private final short layoutHandle;\n" +
            "\n" +
            "    public short layoutHandle() {\n" +
            "        return layoutHandle;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void dispose() {\n" +
            "        bgfx_destroy_dynamic_vertex_buffer(handle);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public short handle() {\n" +
            "        return handle;\n" +
            "    }\n" +
            "\n" +
            "    @Getter\n" +
            "    private final int numVertices;\n" +
            "\n" +
            "    private TypedDynamicVertexBuffer(short handle, short layoutHandle, int numVertices) {\n" +
            "        this.handle = handle;\n" +
            "        this.layoutHandle = layoutHandle;\n" +
            "        this.numVertices = numVertices;\n" +
            "    }" +
            "\n";

    static final String factoryTemplateTemplate = "public static <E* extends BgfxAttrib> TypedDynamicVertexBuffer$<E*, $R1$, $R2$, $R3$> create(\n" +
            "        @NotNull VertexLayoutStruct$<E*, $R1$, $R2$, $R3$> vertexLayout,\n" +
            "        int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {\n" +
            "    Assertions.requirePositive(numVertices);\n" +
            "    final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n" +
            "    final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n" +
            "    return new TypedDynamicVertexBuffer$<>(handle, layoutHandle, numVertices);\n" +
            "}\n";

    static final String[] factoryTemplateTemplateReplacements = {
            "BgfxAttribType.UINT8, Num.ONE, UINT8_Vec1\n",
            "BgfxAttribType.UINT8, Num.TWO, UINT8_Vec2\n",
            "BgfxAttribType.UINT8, Num.THREE, UINT8_Vec3\n",
            "BgfxAttribType.UINT8, Num.FOUR, UINT8_Vec4\n",
            "BgfxAttribType.UINT10, Num.ONE, UINT10_Vec1\n",
            "BgfxAttribType.UINT10, Num.TWO, UINT10_Vec2\n",
            "BgfxAttribType.UINT10, Num.THREE, UINT10_Vec3\n",
            "BgfxAttribType.UINT10, Num.FOUR, UINT10_Vec4\n",
            "BgfxAttribType.INT16, Num.ONE, INT16_Vec1\n",
            "BgfxAttribType.INT16, Num.TWO, INT16_Vec2\n",
            "BgfxAttribType.INT16, Num.THREE, INT16_Vec3\n",
            "BgfxAttribType.INT16, Num.FOUR, INT16_Vec4\n",
            "BgfxAttribType.HALF, Num.ONE, HALF_Vec1\n",
            "BgfxAttribType.HALF, Num.TWO, HALF_Vec2\n",
            "BgfxAttribType.HALF, Num.THREE, HALF_Vec3\n",
            "BgfxAttribType.HALF, Num.FOUR, HALF_Vec4\n",
            "BgfxAttribType.FLOAT, Num.ONE, FLOAT_Vec1\n",
            "BgfxAttribType.FLOAT, Num.TWO, FLOAT_Vec2\n",
            "BgfxAttribType.FLOAT, Num.THREE, FLOAT_Vec3\n",
            "BgfxAttribType.FLOAT, Num.FOUR, FLOAT_Vec4" };

    public static final String factoryMethodsTemplate = ((Supplier<String>)(() -> {
        final StringBuilder sb = new StringBuilder();
        for (String line : factoryTemplateTemplateReplacements) {
            final String[] strings = line.trim().split(", ");
            sb.append(factoryTemplateTemplate.replaceAll("\\$R1\\$", strings[0])
                    .replaceAll("\\$R2\\$", strings[1])
                    .replaceAll("\\$R3\\$", strings[2]));
        }
        return sb.toString();
    })).get();

//            "public static <E* extends BgfxAttrib> TypedDynamicVertexBuffer$<E*, Num.One, BgfxAttribType.UINT8, UINT_Vec1> create(\n" +
//            "        @NotNull VertexLayoutStruct$<E*, Num.One, BgfxAttribType.UINT8, UINT_Vec1> vertexLayout,\n" +
//            "        int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {\n" +
//            "    Assertions.requirePositive(numVertices);\n" +
//            "    final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n" +
//            "    final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n" +
//            "    return new TypedDynamicVertexBuffer$<>(handle, layoutHandle, numVertices);\n" +
//            "}\n" +
//            "public static <E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> TypedDynamicVertexBuffer$<E*, N*, T*, V*> create(\n" +
//            "        @NotNull VertexLayoutStruct$<E*, N*, T*, V*> vertexLayout,\n" +
//            "        int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {\n" +
//            "    Assertions.requirePositive(numVertices);\n" +
//            "    final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n" +
//            "    final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n" +
//            "    return new TypedDynamicVertexBuffer$<>(handle, layoutHandle, numVertices);\n" +
//            "}\n" +
//            "public static <E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> TypedDynamicVertexBuffer$<E*, N*, T*, V*> create(\n" +
//            "        @NotNull VertexLayoutStruct$<E*, N*, T*, V*> vertexLayout,\n" +
//            "        int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {\n" +
//            "    Assertions.requirePositive(numVertices);\n" +
//            "    final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n" +
//            "    final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n" +
//            "    return new TypedDynamicVertexBuffer$<>(handle, layoutHandle, numVertices);\n" +
//            "}\n" +
//            "public static <E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> TypedDynamicVertexBuffer$<E*, N*, T*, V*> create(\n" +
//            "        @NotNull VertexLayoutStruct$<E*, N*, T*, V*> vertexLayout,\n" +
//            "        int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags) {\n" +
//            "    Assertions.requirePositive(numVertices);\n" +
//            "    final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n" +
//            "    final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n" +
//            "    return new TypedDynamicVertexBuffer$<>(handle, layoutHandle, numVertices);\n" +
//            "}\n";

    public static final String classesTemplate =
            "public static class TypedDynamicVertexBuffer$<E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> extends TypedDynamicVertexBuffer {\n" +
            "\n" +
            "        private static final int cardinality = $;\n" +
            "\n" +
            "        public TypedDynamicVertexBuffer$(short handle, short layoutHandle, int numVertices) {\n" +
            "            super(handle, layoutHandle, numVertices);\n" +
            "        }\n" +
            "\n" +
            "        public void update(int startVertex, @NotNull V*[] v*) {\n" +
            "            // collect inputs into array\n" +
            "            final BgfxAttribType[][] arrays = new BgfxAttribType[][] {\n" +
            "                    changeTo_v*" +
            "\n" +
            "            };\n" +
            "\n" +
            "            Assertions.requireNonEmpty(arrays[0]);\n" +
            "            for (int i=1; i < cardinality; i++) {\n" +
            "                Assertions.requireEqualLength(arrays[0], arrays[i]);\n" +
            "            }\n" +
            "\n" +
            "            int requiredBytes = 0;\n" +
            "            for (int i=0; i < arrays.length; i++) {\n" +
            "                requiredBytes += computeBytes(arrays[i][0].representedType(), arrays[i][0].number(), arrays[i].length);\n" +
            "            }\n" +
            "\n" +
            "            try (final MemoryStack stack = MemoryStack.stackPush()) {\n" +
            "                // todo, some might be too big to store on stack!\n" +
            "                final ByteBuffer bytes = stack.malloc(requiredBytes);\n" +
            "\n" +
            "                final int vertexCount = arrays[0].length;\n" +
            "                final int vertexStrideBytes = computeStrideBytes(arrays);\n" +
            "\n" +
            "                // see for visual of terms: https://i.stack.imgur.com/dvT7N.png\n" +
            "                int startOffsetBytes = 0;\n" +
            "                for (int i=0; i < arrays.length; i++) {\n" +
            "                    int pos = startOffsetBytes;\n" +
            "                    for (int vertex=0; vertex < vertexCount; vertex++) {\n" +
            "                        final BgfxAttribType v = arrays[i][vertex];\n" +
            "//                    v.apply().get()\n" +
            "\n" +
            "//                    bytes.reset();\n" +
            "                        bytes.position(pos);\n" +
            "\n" +
            "                        final Number[] values = v.apply().get();\n" +
            "\n" +
            "                        // todo fix for types that dont align with java types (uint10, half)\n" +
            "                        for (Number value : values) {\n" +
            "                            if (value instanceof Byte) {\n" +
            "                                bytes.put((byte)value);\n" +
            "                            } else if (value instanceof Short) {\n" +
            "                                bytes.putShort((short)value);\n" +
            "                            } else if (value instanceof Integer) {\n" +
            "                                throw new IllegalArgumentException(\"Vertex spec does not accept 32-bit integers\");\n" +
            "                            } else if (value instanceof Long) {\n" +
            "                                throw new IllegalArgumentException(\"Vertex spec does not accept longs\");\n" +
            "                            } else if (value instanceof Float) {\n" +
            "                                bytes.putFloat((float)value);\n" +
            "                            } else if (value instanceof Double) {\n" +
            "                                throw new IllegalArgumentException(\"Vertex spec does not accept doubles\");\n" +
            "                            }\n" +
            "                        }\n" +
            "                        pos += vertexStrideBytes;\n" +
            "                    }\n" +
            "                    final BgfxAttribType sample = arrays[i][0];\n" +
            "                    switch (sample.representedType()) {\n" +
            "                        case UINT8:\n" +
            "                            startOffsetBytes += sample.number().value(); // 1 byte per field\n" +
            "                            break;\n" +
            "                        case UINT10:\n" +
            "                            startOffsetBytes += 4; // see Khronos spec explained below -- always 4 bytes\n" +
            "                            break;\n" +
            "                        case HALF: // todo will cause error cus it takes sizes for half but gets float\n" +
            "                        case INT16:\n" +
            "                            startOffsetBytes += sample.number().value() * 2;\n" +
            "                            break;\n" +
            "                        case FLOAT:\n" +
            "                            startOffsetBytes += sample.number().value() * 4;\n" +
            "                            break;\n" +
            "                        default:\n" +
            "                            throw new IllegalStateException(\"Pattern matching must be exhaustive\");\n" +
            "                    }\n" +
            "                }\n" +
            "\n" +
            "                final BGFXMemory memory = bgfx_copy(bytes);\n" +
            "                bgfx_update_dynamic_vertex_buffer(super.handle(), startVertex, memory);\n" +
            "            }\n" +
            "        }\n" +
            "    }";

    public static final String tail = "private static int computeStrideBytes(Vec<?,?>[][] arrays) {\n" +
            "        int total = 0;\n" +
            "        for (Vec<?,?>[] array : arrays) {\n" +
            "            final Vec<?,?> sample = array[0];\n" +
            "            final BGFX_ATTRIB_TYPE type = sample.type().representedType();\n" +
            "            final Num num = sample.number();\n" +
            "            switch (type) {\n" +
            "                case UINT8:\n" +
            "                    total += num.value();\n" +
            "                    break;\n" +
            "                case UINT10:\n" +
            "                    total += 4;\n" +
            "                    break;\n" +
            "                case HALF:\n" +
            "                case INT16:\n" +
            "                    total += num.value() * 2; // two bytes (floating point or a short)\n" +
            "                    break;\n" +
            "                case FLOAT:\n" +
            "                    total += num.value() * 4; // four bytes (float)\n" +
            "                    break;\n" +
            "                default:\n" +
            "                    throw new IllegalStateException(\"Pattern matching must be exhaustive\");\n" +
            "            }\n" +
            "        }\n" +
            "        return total;\n" +
            "    }\n" +
            "\n" +
            "    private static int computeBytes(BGFX_ATTRIB_TYPE type, Num num, int arraySize) {\n" +
            "        switch (type) {\n" +
            "            case UINT8:\n" +
            "                return num.value() * arraySize;\n" +
            "            case UINT10:\n" +
            "                // UINT10 xyzw where each gets 10/10/10/2 bits respectively.\n" +
            "                // By the Khronos spec, the Num will be 3 or 4 if the type is UINT10, and if Num = 3 then\n" +
            "                //  those two bits just go unused.\n" +
            "\n" +
            "                // Thus, we have 4 bytes (32 bits) per array slot regardless of size of each array element\n" +
            "                // and we just multiply the array size by 4 bytes\n" +
            "                return arraySize * 4;\n" +
            "            case HALF:\n" +
            "            case INT16:\n" +
            "                return num.value() * arraySize * 2; // two bytes (floating point or a short)\n" +
            "            case FLOAT:\n" +
            "                return num.value() * arraySize * 4; // four bytes (float)\n" +
            "            default:\n" +
            "                throw new IllegalStateException(\"Pattern matching must be exhaustive\");\n" +
            "        }\n" +
            "    }";

}
