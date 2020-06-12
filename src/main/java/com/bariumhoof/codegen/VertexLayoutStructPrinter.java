package com.bariumhoof.codegen;

public class VertexLayoutStructPrinter {


    private static final int NUM_STAGES = 18;

    private static final String current = "\\+";
    private static final String next = "\\$";
    private static final String last = "-";
    private static final String lastExpansion = "E%, N%, T%, V%";
    private static final String nextExpansion = "E\\^, N\\^, T\\^, V\\^";
    private static final String expansion1 = "E\\*, N\\*, T\\*, V\\*";
    private static final String expansion2 = "E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
                                           //"E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>"

    // if a codegen w/ understanding of java was used, then could loop i=1 to N and just know to drop expressions
    // with "-" for i=1 and "$" for i=N (also applies to expansions)

    public static void main(String[] args) {

        System.out.println(header);
        System.out.println();
        System.out.println(templateBase);
        System.out.println();
        System.out.println(layoutStructBase);
        System.out.println();

        final int upperBound = NUM_STAGES + 1;

        for (int i=2; i < upperBound; i++) {
            final String current_n = Integer.toString(i);
            final String next_n = Integer.toString(i+1);
            final String last_n = Integer.toString(i-1);

            final StringBuilder lastExpansion_n = new StringBuilder();
            for (int j=1; j < i; j++) {
                if (j > 1) {
                    lastExpansion_n.append(", ");
                }
                lastExpansion_n.append(String.format("E%d, N%d, T%d, V%d", j, j, j, j));
            }

            final StringBuilder nextExpansion_n = new StringBuilder();
            for (int j=1; j <= i+1; j++) {
                if (j > 1) {
                    nextExpansion_n.append(", ");
                }
                nextExpansion_n.append(String.format("E%d, N%d, T%d, V%d", j, j, j, j));
            }

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
                                                // "E\\* extends BgfxAttrib, N\\* extends Num, T\\* extends BgfxAttribType, V\\* extends Vec<N\\*, T\\*>";
                expansion2_n.append(String.format("E%d extends BgfxAttrib, N%d extends Num, T%d extends BgfxAttribType, V%d extends Vec<N%d, T%d>", j, j, j, j, j, j));
            }

            System.out.println();
            System.out.println(template
                    .replaceAll(current, current_n)
                    .replaceAll(next, next_n)
                    .replaceAll(last, last_n)
                    .replaceAll(lastExpansion, lastExpansion_n.toString())
                    .replaceAll(nextExpansion, nextExpansion_n.toString())
                    .replaceAll(expansion1, expansion1_n.toString())
                    .replaceAll(expansion2, expansion2_n.toString()));

        }

        for (int i=2; i < upperBound; i++) {
            final String current_n = Integer.toString(i);

            final StringBuilder expansion2_n = new StringBuilder();
            for (int j=1; j <= i; j++) {
                if (j > 1) {
                    if (j % 2 == 0) {
                        expansion2_n.append(",\n");
                    } else {
                        expansion2_n.append(", ");
                    }
                }
                expansion2_n.append(String.format("E%d extends BgfxAttrib, N%d extends Num, T%d extends BgfxAttribType, V%d extends Vec<N%d, T%d>", j, j, j, j, j, j));
            }

//            System.err.println("made ex2: " + expansion2_n.toString());

            System.out.println();
            System.out.println(layoutStructTemplate
                    .replaceAll(current, current_n)
                    .replaceAll(expansion2, expansion2_n.toString()));

        }

        System.out.println(tail);
    }

    private static final String header = "package com.bariumhoof.bgfx4j.layout;\n" +
            "\n" +
            "import com.bariumhoof.Capabilities;\n" +
            "import com.bariumhoof.assertions.Assertions;\n" +
            "import com.bariumhoof.bgfx4j.enums.BGFX_ATTRIB;\n" +
            "import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;\n" +
            "import com.bariumhoof.bgfx4j.layout.Vec.*;\n" +
            "import org.jetbrains.annotations.NotNull;\n" +
            "import org.jetbrains.annotations.Nullable;\n" +
            "import org.lwjgl.bgfx.BGFXVertexLayout;\n" +
            "\n" +
            "import java.util.EnumSet;\n" +
            "import java.util.Set;\n" +
            "\n" +
            "import static org.lwjgl.bgfx.BGFX.*;\n" +
            "\n" +
            "public abstract class VertexLayoutStruct {\n" +
            "\n" +
            "    private final BGFXVertexLayout layout;\n" +
            "    public final BGFXVertexLayout get() {\n" +
            "        return layout;\n" +
            "    }\n" +
            "    protected VertexLayoutStruct(BGFXVertexLayout layout) {\n" +
            "        this.layout = layout;\n" +
            "    }\n" +
            "\n" +
            "    abstract static class Builder<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N, T>> {\n" +
            "        protected Builder(E attrib, N num, T type, boolean normalized, boolean asInt) {\n" +
            "            this.attrib = attrib;\n" +
            "            this.num = num;\n" +
            "            this.type = type;\n" +
            "            this.normalized = normalized;\n" +
            "            this.asInt = asInt;\n" +
            "            errorOnDuplicateAttribute();\n" +
            "        }\n" +
            "\n" +
            "        @Nullable abstract Builder<?, ?, ?, ?> previousBuilder();\n" +
            "        final E attrib;\n" +
            "        final N num;\n" +
            "        final T type;\n" +
            "        final boolean normalized;\n" +
            "        final boolean asInt;\n" +
            "\n" +
            "        void errorOnDuplicateAttribute() {\n" +
            "            // todo add to build() at end or check as building -- dont check all per step\n" +
            "            Set<BGFX_ATTRIB> attribs = EnumSet.noneOf(BGFX_ATTRIB.class);\n" +
            "            Builder<?, ?, ?, ?> builder = this;\n" +
            "            while (builder != null) {\n" +
            "                final BGFX_ATTRIB next = builder.attrib.representedType();\n" +
            "                if (attribs.contains(next)) {\n" +
            "                    throw new IllegalStateException(\"VertexLayoutStruct cannot build: duplicate attribute \" + attrib);\n" +
            "                }\n" +
            "                attribs.add(next);\n" +
            "                builder = builder.previousBuilder();\n" +
            "            }\n" +
            "        }\n" +
            "    }";

    private static final String templateBase = "public static class Builder1<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>> extends Builder<E1, N1, T1, V1> {\n" +
            "\n" +
            "        protected Builder1(E1 attrib, N1 num, T1 type, boolean normalized, boolean asInt) {\n" +
            "            super(attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        public VertexLayoutStruct1<E1, N1, T1, V1> build() {\n" +
            "            return build(Capabilities.getRendererType());\n" +
            "        }\n" +
            "\n" +
            "        public VertexLayoutStruct1<E1, N1, T1, V1> build(@NotNull BGFX_RENDERER_TYPE rendererType) {\n" +
            "            final Builder<?, ?, ?, ?>[] builders = createBuildersArray(2, this);\n" +
            "            final BGFXVertexLayout layout = createLayout(rendererType, builders);\n" +
            "            return new VertexLayoutStruct1<>(layout);\n" +
            "        }\n" +
            "\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.ONE, BgfxAttribType.UINT8, UINT8_Vec1> thenUse(E2 attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.TWO, BgfxAttribType.UINT8, UINT8_Vec2> thenUse(E2 attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.THREE, BgfxAttribType.UINT8, UINT8_Vec3> thenUse(E2 attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.FOUR, BgfxAttribType.UINT8, UINT8_Vec4> thenUse(E2 attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.ONE, BgfxAttribType.UINT10, UINT10_Vec1> thenUse(E2 attrib, Num.ONE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.TWO, BgfxAttribType.UINT10, UINT10_Vec2> thenUse(E2 attrib, Num.TWO num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.THREE, BgfxAttribType.UINT10, UINT10_Vec3> thenUse(E2 attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.FOUR, BgfxAttribType.UINT10, UINT10_Vec4> thenUse(E2 attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.ONE, BgfxAttribType.INT16, INT16_Vec1> thenUse(E2 attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.TWO, BgfxAttribType.INT16, INT16_Vec2> thenUse(E2 attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.THREE, BgfxAttribType.INT16, INT16_Vec3> thenUse(E2 attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.FOUR, BgfxAttribType.INT16, INT16_Vec4> thenUse(E2 attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.ONE, BgfxAttribType.HALF, HALF_Vec1> thenUse(E2 attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.TWO, BgfxAttribType.HALF, HALF_Vec2> thenUse(E2 attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.THREE, BgfxAttribType.HALF, HALF_Vec3> thenUse(E2 attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.FOUR, BgfxAttribType.HALF, HALF_Vec4> thenUse(E2 attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.ONE, BgfxAttribType.FLOAT, FLOAT_Vec1> thenUse(E2 attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.TWO, BgfxAttribType.FLOAT, FLOAT_Vec2> thenUse(E2 attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.THREE, BgfxAttribType.FLOAT, FLOAT_Vec3> thenUse(E2 attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E2 extends BgfxAttrib> Builder2<E1, N1, T1, V1, E2, Num.FOUR, BgfxAttribType.FLOAT, FLOAT_Vec4> thenUse(E2 attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder2<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        @Nullable\n" +
            "        Builder<?, ?, ?, ?> previousBuilder() { return null; }\n" +
            "    }";

    // + means current number
    // $ means next number
    // - means last number
    // * means grow pattern up to current number
    // % means grow pattern up to last number
    // ^ means grow pattern up to next number
    private static final String template = "public static class Builder+<E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> extends Builder<E+, N+, T+, V+> {\n" +
            "        private final Builder-<E%, N%, T%, V%> last;\n" +
            "\n" +
            "        protected Builder+(Builder-<E%, N%, T%, V%> last, E+ attrib, N+ num, T+ type, boolean normalized, boolean asInt) {\n" +
            "            super(attrib, num, type, normalized, asInt);\n" +
            "            this.last = last;\n" +
            "        }\n" +
            "\n" +
            "        public VertexLayoutStruct+<E*, N*, T*, V*> build() {\n" +
            "            return build(Capabilities.getRendererType());\n" +
            "        }\n" +
            "\n" +
            "        public VertexLayoutStruct+<E*, N*, T*, V*> build(@NotNull BGFX_RENDERER_TYPE rendererType) {\n" +
            "            final Builder<?, ?, ?, ?>[] builders = createBuildersArray(+, this);\n" +
            "            final BGFXVertexLayout layout = createLayout(rendererType, builders);\n" +
            "            return new VertexLayoutStruct+<>(layout);\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        @Nullable Builder<E-, N-, T-, V-> previousBuilder() {\n" +
            "            return last;\n" +
            "        }\n" +
            "\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.ONE, BgfxAttribType.UINT8, UINT8_Vec1> thenUse(E$ attrib, Num.ONE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.TWO, BgfxAttribType.UINT8, UINT8_Vec2> thenUse(E$ attrib, Num.TWO num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.THREE, BgfxAttribType.UINT8, UINT8_Vec3> thenUse(E$ attrib, Num.THREE num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.FOUR, BgfxAttribType.UINT8, UINT8_Vec4> thenUse(E$ attrib, Num.FOUR num, BgfxAttribType.UINT8 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.ONE, BgfxAttribType.UINT10, UINT10_Vec1> thenUse(E$ attrib, Num.ONE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.TWO, BgfxAttribType.UINT10, UINT10_Vec2> thenUse(E$ attrib, Num.TWO num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.THREE, BgfxAttribType.UINT10, UINT10_Vec3> thenUse(E$ attrib, Num.THREE num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.FOUR, BgfxAttribType.UINT10, UINT10_Vec4> thenUse(E$ attrib, Num.FOUR num, BgfxAttribType.UINT10 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.ONE, BgfxAttribType.INT16, INT16_Vec1> thenUse(E$ attrib, Num.ONE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.TWO, BgfxAttribType.INT16, INT16_Vec2> thenUse(E$ attrib, Num.TWO num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.THREE, BgfxAttribType.INT16, INT16_Vec3> thenUse(E$ attrib, Num.THREE num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.FOUR, BgfxAttribType.INT16, INT16_Vec4> thenUse(E$ attrib, Num.FOUR num, BgfxAttribType.INT16 type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.ONE, BgfxAttribType.HALF, HALF_Vec1> thenUse(E$ attrib, Num.ONE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.TWO, BgfxAttribType.HALF, HALF_Vec2> thenUse(E$ attrib, Num.TWO num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.THREE, BgfxAttribType.HALF, HALF_Vec3> thenUse(E$ attrib, Num.THREE num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.FOUR, BgfxAttribType.HALF, HALF_Vec4> thenUse(E$ attrib, Num.FOUR num, BgfxAttribType.HALF type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.ONE, BgfxAttribType.FLOAT, FLOAT_Vec1> thenUse(E$ attrib, Num.ONE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.TWO, BgfxAttribType.FLOAT, FLOAT_Vec2> thenUse(E$ attrib, Num.TWO num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.THREE, BgfxAttribType.FLOAT, FLOAT_Vec3> thenUse(E$ attrib, Num.THREE num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "        public <E$ extends BgfxAttrib> Builder$<E*, N*, T*, V*, E$, Num.FOUR, BgfxAttribType.FLOAT, FLOAT_Vec4> thenUse(E$ attrib, Num.FOUR num, BgfxAttribType.FLOAT type, boolean normalized, boolean asInt) {\n" +
            "            return new Builder$<>(this, attrib, num, type, normalized, asInt);\n" +
            "        }\n" +
            "    }\n" +
            "\n";


    private static final String layoutStructBase = "public static class VertexLayoutStruct1<E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>> extends VertexLayoutStruct {\n" +
            "        protected VertexLayoutStruct1(BGFXVertexLayout layout) {\n" +
            "            super(layout);\n" +
            "        }\n" +
            "    }";

    private static final String layoutStructTemplate = "   public static class VertexLayoutStruct+<E* extends BgfxAttrib, N* extends Num, T* extends BgfxAttribType, V* extends Vec<N*, T*>> extends VertexLayoutStruct {\n" +
            "        protected VertexLayoutStruct+(BGFXVertexLayout layout) {\n" +
            "            super(layout);\n" +
            "        }\n" +
            "    }";

    private static final String tail = "private static Builder<?, ?, ?, ?>[] createBuildersArray(int size, Builder<?, ?, ?, ?> lastBuilder) {\n" +
            "        Builder<?, ?, ?, ?>[] builders = new Builder<?, ?, ?, ?>[size];\n" +
            "\n" +
            "        Builder<?, ?, ?, ?> b = lastBuilder;\n" +
            "        for (int i=size-1; i >= 0; i--) {\n" +
            "            builders[i] = b.previousBuilder();\n" +
            "            b = b.previousBuilder();\n" +
            "        }\n" +
            "\n" +
            "        return builders;\n" +
            "    }\n" +
            "\n" +
            "    @NotNull\n" +
            "    private static BGFXVertexLayout createLayout(@NotNull BGFX_RENDERER_TYPE rendererType, Builder<?, ?, ?, ?>[] builders) {\n" +
            "        final BGFXVertexLayout layout = BGFXVertexLayout.calloc();\n" +
            "\n" +
            "        bgfx_vertex_layout_begin(layout, rendererType.VALUE);\n" +
            "        Builder<?, ?, ?, ?> lastBuilder = null;\n" +
            "        for (int i=0; i < builders.length; i++) {\n" +
            "            lastBuilder = builders[i];\n" +
            "            enforceKhronosSpec(lastBuilder.num, lastBuilder.type, lastBuilder.normalized, lastBuilder.asInt);\n" +
            "            bgfx_vertex_layout_add(layout, lastBuilder.attrib.representedType().VALUE, lastBuilder.num.value(), lastBuilder.type.representedType().VALUE, lastBuilder.normalized, lastBuilder.asInt);\n" +
            "        }\n" +
            "        bgfx_vertex_layout_end(layout);\n" +
            "        return layout;\n" +
            "    }\n" +
            "\n" +
            "    private static <E extends BgfxAttrib, N extends Num, T extends BgfxAttribType> void enforceKhronosSpec(N num, T type, boolean normalized, boolean asInt) {\n" +
            "        // https://www.khronos.org/opengl/wiki/Vertex_Specification#Component_type\n" +
            "\n" +
            "        switch (type.representedType()) {\n" +
            "            case UINT10: // 32 bits as 3 or 4. All collectively form into a 32 bit value\n" +
            "                // calls must be 3 or 4 https://www.khronos.org/registry/OpenGL/extensions/OES/OES_vertex_type_10_10_10_2.txt\n" +
            "                Assertions.require(num == Num.THREE || num == Num.FOUR);\n" +
            "                return;\n" +
            "            case HALF: // 16 bit float\n" +
            "            case FLOAT: // 32 bits\n" +
            "                Assertions.require(!normalized && !asInt); // must be false for all floating point types\n" +
            "                return;\n" +
            "            default: throw new IllegalStateException(\"Pattern match must be exhaustive\");\n" +
            "        }\n" +
            "    }\n" +
            "}";

}
