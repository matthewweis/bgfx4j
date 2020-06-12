package com.bariumhoof.codegen;

import static java.lang.String.format;

public class VertexPrinter {

    public static void main(String[] args) {
        System.out.println(create());
    }

    public static String create() {
        final StringBuilder sb = new StringBuilder();
        for (int i=4; i <= 18; i++) {
            sb.append("@Value(staticConstructor = \"of\")\n");

            sb.append("    class Vertex");
            sb.append(i);
            sb.append("<");

            for (int j=1; j <= i; j++) {
                if (j > 1) sb.append(", ");
                sb.append(format("T%d extends Vec<?,?>", j));
            }
            sb.append("> implements Vertex {\n");
            for (int j=1; j <= i; j++) {
                sb.append(format("        @NotNull T%d t%d;\n", j, j));
            }
            sb.append("\n");
            sb.append("        @Override\n");
            sb.append("        public int size() {\n");
            sb.append(format("            return %d;\n", i));
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        @Override\n");
            sb.append("        public Vec<?,?>[] array() {\n");
            sb.append("            return new Vec<?,?>[] { ");
            for (int j=1; j <= i; j++) {
                if (j > 1) sb.append(", ");
                sb.append(format("t%d", j));
            }
            sb.append(" };\n");
            sb.append("        }\n");
            sb.append("    }\n\n");
        }
        return sb.toString();
    }

}
