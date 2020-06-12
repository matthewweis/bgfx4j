package com.bariumhoof.codegen;

public class TypedDynaimcVertexBufferPrinter2 {

    String s = "public static final class TypedDynamicVertexBuffer3<\n" +
            "            T1 extends BgfxAttribType, N1 extends Num, V1 extends Vec<N1, T1>,\n" +
            "            T2 extends BgfxAttribType, N2 extends Num, V2 extends Vec<N2, T2>,\n" +
            "            T3 extends BgfxAttribType, N3 extends Num, V3 extends Vec<N3, T3>> extends TypedDynamicVertexBuffer {\n" +
            "        private TypedDynamicVertexBuffer3(short handle, short layoutHandle, int numVertices) {\n" +
            "            super(handle, layoutHandle, numVertices);\n" +
            "        }\n" +
            "        @SafeVarargs\n" +
            "        public final void update(int startVertex, @NotNull Vertex.Vertex3<V1, V2, V3>... vertices) {\n" +
            "            assertValidVertexPack(getNumVertices(), startVertex, vertices);\n" +
            "            vertexPack(handle(), startVertex, vertices);\n" +
            "        }\n" +
            "    }";


    private static String printClasses() {
        final StringBuilder sb = new StringBuilder();

        for (int i=4; i <= 18; i++) {
            sb.append("public static final class TypedDynamicVertexBuffer");
            sb.append(i);
            sb.append("<\n");
            // generics
            for (int j=1; j < i; j++) {
                final String genericLine = String.format(
                        "T%d extends BgfxAttribType, N%d extends Num, V%d extends Vec<N%d, T%d>,\n", j, j ,j, j, j);
                sb.append(genericLine);
            }
            sb.append(String.format("T%d extends BgfxAttribType, N%d extends Num, V%d extends Vec<N%d, T%d>>\n", i, i, i, i, i));
            sb.append("extends TypedDynamicVertexBuffer {\n");
            sb.append("        private TypedDynamicVertexBuffer");
            sb.append(i);
            sb.append("(short handle, short layoutHandle, int numVertices) {\n");
            sb.append("            super(handle, layoutHandle, numVertices);\n");
            sb.append("        }\n" +
                    "        @SafeVarargs\n" +
                    "        public final void update(int startVertex, @NotNull Vertex.Vertex");
            sb.append(i);
            sb.append("<");
            classTypeParamsTo(sb, i);
            sb.append(">... vertices) {\n" +
                    "            assertValidVertexPack(getNumVertices(), startVertex, vertices);\n" +
                    "            vertexPack(handle(), startVertex, vertices);\n" +
                    "        }\n" +
                    "    }\n\n");

        }

        return sb.toString();
    }


    private static String printFactoryMethods() {
        final StringBuilder sb = new StringBuilder();
        for (int i=4; i <= 18; i++) {
            sb.append("public static <");
            for (int j=1; j < i; j++) {
                final String genericLine = String.format(
                        "E%d extends BgfxAttrib, T%d extends BgfxAttribType, N%d extends Num, V%d extends Vec<N%d, T%d>,\n",
                        j, j ,j, j, j, j);
                sb.append(genericLine);
            }
            sb.append(String.format("E%d extends BgfxAttrib, T%d extends BgfxAttribType, N%d extends Num, V%d extends Vec<N%d, T%d>>\n", i, i, i, i, i, i));
            sb.append("TypedDynamicVertexBuffer");
            sb.append(i);
            sb.append("<");
            factoryTypeParamsTo1(sb, i);
            sb.append("> create(\n");
            sb.append("            @NotNull VertexLayoutStruct");
            sb.append(i);
            sb.append("<");
            factoryTypeParamsTo2(sb, i);
            sb.append("> vertexLayout,\n");
            sb.append("            int numVertices, @NotNull EnumSet<BGFX_BUFFER> flags){\n");
            sb.append("            Assertions.requirePositive(numVertices);\n");
            sb.append("            final short handle = bgfx_create_dynamic_vertex_buffer(numVertices, vertexLayout.get(), (int) BGFX_BUFFER.flags(flags));\n");
            sb.append("            final short layoutHandle = bgfx_create_vertex_layout(vertexLayout.get());\n");
            sb.append("            return new TypedDynamicVertexBuffer");
            sb.append(i);
            sb.append("<>(handle, layoutHandle, numVertices);\n");
            sb.append("    }\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void classTypeParamsTo(StringBuilder sb, int i) {
        for (int j=1; j <= i; j++) {
            if (j > 1) sb.append(", ");
            final String typeParams = String.format("V%d", j);
            sb.append(typeParams);
        }
    }

    // class
    private static void factoryTypeParamsTo1(StringBuilder sb, int i) {
        for (int j=1; j <= i; j++) {
            if (j > 1) sb.append(", ");
            final String typeParams = String.format("T%d, N%d, V%d", j, j, j);
            sb.append(typeParams);
        }
    }

    // vertex
    private static void factoryTypeParamsTo2(StringBuilder sb, int i) {
        for (int j=1; j <= i; j++) {
            if (j > 1) sb.append(", ");
            final String typeParams = String.format("E%d, N%d, T%d, V%d", j, j, j, j);
            sb.append(typeParams);
        }
    }

    public static void main(String[] args) {
//        System.out.println(printClasses());
        System.out.println(printFactoryMethods());
    }

}
