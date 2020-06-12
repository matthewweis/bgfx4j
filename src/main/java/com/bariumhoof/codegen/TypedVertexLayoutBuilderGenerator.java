package com.bariumhoof.codegen;

import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;
import com.bariumhoof.bgfx4j.layout.BgfxAttrib;
import com.bariumhoof.bgfx4j.layout.BgfxAttribType;
import com.bariumhoof.bgfx4j.layout.Num;
import com.bariumhoof.bgfx4j.layout.Vec;
import com.squareup.javapoet.*;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TypedVertexLayoutBuilderGenerator {

    // change to true for file output
    final static boolean GEN_FILES = false;

    static final List<String> attribs = List.of("POSITION", "NORMAL", "TANGENT", "BITANGENT",
            "COLOR0", "COLOR1", "COLOR2", "COLOR3",
            "INDICES", "WEIGHT",
            "TEXCOORD0", "TEXCOORD1", "TEXCOORD2", "TEXCOORD3", "TEXCOORD4",
            "TEXCOORD5", "TEXCOORD6", "TEXCOORD7");

    public static void main(String[] args) throws IOException {

        int from = 0;
        int to = 5;
        String op = "+";

//        final MethodSpec spec = MethodSpec.methodBuilder("myMethod")
//                .returns(int.class)
//                .addStatement("int result = 0")
//                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
//                .addStatement("result = result $L i", op)
//                .endControlFlow()
//                .addStatement("return result")
//                .build();


        for (int i=3; i <= 18; i++) {

            final int last = i - 1;
            final int next = i + 1;


            //E1 extends BgfxAttrib, N1 extends Num, T1 extends BgfxAttribType, V1 extends Vec<N1, T1>
            // private final VertexLayoutStructBuilder1<E1,N1,T1,V1> last;

            final TypeSpec.Builder builder = TypeSpec.classBuilder("TypedVertexLayoutBuilder" + i);
            builder.addModifiers(Modifier.PUBLIC);

            final List<TypeVariableName> types1ToN = new ArrayList<>();
            for (int j=1; j < i; j++) {
                final TypeVariableName n = TypeVariableName.get("N" + j).withBounds(Num.class);
                final TypeVariableName e = TypeVariableName.get("E" + j).withBounds(BgfxAttrib.class);
                final TypeVariableName t = TypeVariableName.get("T" + j).withBounds(BgfxAttribType.class);

                final ParameterizedTypeName parameterizedVec = ParameterizedTypeName.get(ClassName.get(Vec.class), n, t);
                final TypeVariableName v = TypeVariableName.get("V" + j).withBounds(parameterizedVec);

                builder.addTypeVariable(e);
                builder.addTypeVariable(n);
                builder.addTypeVariable(t);
                builder.addTypeVariable(v);

                types1ToN.add(e);
                types1ToN.add(n);
                types1ToN.add(t);
                types1ToN.add(v);
            }

            final List<TypeVariableName> nonParams1ToN = types1ToN.stream()
                    .map(it -> TypeVariableName.get(it.name))
                    .collect(Collectors.toUnmodifiableList());

            final TypeVariableName[] types1ToNArray = types1ToN.toArray(new TypeVariableName[0]);
            final ParameterizedTypeName lastBuilder =
                    ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStructBuilder" + last),
                            types1ToNArray);

            builder.addField(FieldSpec.builder(lastBuilder, "last", Modifier.PRIVATE, Modifier.FINAL).build());

            builder.addMethod(MethodSpec.constructorBuilder()
                    .addParameter(lastBuilder, "last")
                    .addStatement("this.last = last")
                    .build());

            builder.addMethod(MethodSpec
                    .methodBuilder("initialStage")
                    .returns(ClassName.bestGuess("InitialStage"))
                    .addStatement("return new InitialStage()")
                    .build());


            builder.addType(TypeSpec
                    .classBuilder("InitialStage")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(MethodSpec.constructorBuilder().addModifiers(Modifier.PROTECTED).build())
                    .addMethods(
                            attribs.stream().map(attrib -> MethodSpec.methodBuilder(attrib.toLowerCase())
                                    .returns(ParameterizedTypeName.get(ClassName.bestGuess("AttribStage"), ClassName.bestGuess(attrib)))
                                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                                    .addStatement("return new AttribStage<>(" + attrib + "." + attrib + ")")
                                    .addAnnotation(NotNull.class)
                                    .build()).collect(Collectors.toList()))
                    .build());

            final TypeVariableName n = TypeVariableName.get("N").withBounds(Num.class);
            final TypeVariableName e = TypeVariableName.get("E").withBounds(BgfxAttrib.class);
            final TypeVariableName t = TypeVariableName.get("T").withBounds(BgfxAttribType.class);
            final ParameterizedTypeName parameterizedVec = ParameterizedTypeName.get(ClassName.get(Vec.class), n, t);
            final TypeVariableName v = TypeVariableName.get("V").withBounds(parameterizedVec);


            final TypeVariableName[] finalMethodReturnParams = new TypeVariableName[nonParams1ToN.size()+4];
            for (int ii=0; ii < nonParams1ToN.size(); ii++) {
                finalMethodReturnParams[ii] = nonParams1ToN.get(ii);
            }
            finalMethodReturnParams[finalMethodReturnParams.length - 4] = TypeVariableName.get(e.name);
            finalMethodReturnParams[finalMethodReturnParams.length - 3] = TypeVariableName.get(n.name);
            finalMethodReturnParams[finalMethodReturnParams.length - 2] = TypeVariableName.get(t.name);
            finalMethodReturnParams[finalMethodReturnParams.length - 1] = TypeVariableName.get(v.name);

            final int current_i = i;

            final TypeSpec lastInnerClass = TypeSpec.classBuilder("AttribTypeStageDefaultOpts")
                    .addModifiers(Modifier.PUBLIC)
                    .addTypeVariable(e)
                    .addTypeVariable(n)
                    .addTypeVariable(t)
                    .addTypeVariable(v)
                    .addField(e, "attrib", Modifier.FINAL)
                    .addField(n, "num", Modifier.FINAL)
                    .addField(t, "type", Modifier.FINAL)
                    .addField(Boolean.TYPE, "normalized", Modifier.FINAL)
                    .addField(Boolean.TYPE, "asInt", Modifier.FINAL)
                    .addMethod(MethodSpec.constructorBuilder()
                            .addParameter(e, "attrib")
                            .addParameter(n, "num")
                            .addParameter(t, "type")
                            .addStatement("this(attrib, num, type, false, false)")
                            .build())
                    .addMethod(MethodSpec.constructorBuilder()
                            .addParameter(e, "attrib")
                            .addParameter(n, "num")
                            .addParameter(t, "type")
                            .addParameter(Boolean.TYPE, "normalized")
                            .addParameter(Boolean.TYPE, "asInt")
                            .addStatement("this.attrib = attrib")
                            .addStatement("this.num = num")
                            .addStatement("this.type = type")
                            .addStatement("this.normalized = normalized")
                            .addStatement("this.asInt = asInt")
                            .build())
                    .addMethod(MethodSpec.methodBuilder("newLast")
                            .addModifiers(Modifier.FINAL)
                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStructBuilder" + i),
                                    finalMethodReturnParams))
                            .addStatement("return new VertexLayoutStructBuilder" + i + "<>(last, attrib, num, type, normalized, asInt)")
                            .build())
                    .addMethod(MethodSpec.methodBuilder("build")
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStruct" + i),
                                    finalMethodReturnParams))
                            .addStatement("return build(Capabilities.getRendererType())")
                            .build())
                    .addMethod(MethodSpec.methodBuilder("build")
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .returns(ClassName.bestGuess("VertexLayoutStruct" + i))
                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStruct" + i),
                                    finalMethodReturnParams))
//                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStruct" + i),
//                                    Stream.of(finalMethodReturnParams)
//                                        .filter(it -> !it.name.endsWith(Integer.toString(current_i)))
//                                            .toArray(TypeName[]::new)))
                            .addParameter(ParameterSpec.builder(ClassName.get(BGFX_RENDERER_TYPE.class), "rendererType")
                                    .addAnnotation(NotNull.class)
                                    .build())
                            .addStatement("final VertexLayoutStructBuilder<?,?,?,?>[] builders = VertexLayoutStructBuilder.createBuildersArray(" + i + ", newLast())")
                            .addStatement("final BGFXVertexLayout layout = VertexLayoutStructBuilder.createLayout(rendererType, builders)")
                            .addStatement("return new VertexLayoutStruct" + i + "<>(layout)")
                            .build())
                    .addMethod(MethodSpec.methodBuilder("then")
                            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("TypedVertexLayoutBuilder"+next),
                                    Stream.of(finalMethodReturnParams)
                                            .filter(it -> !it.name.endsWith(Integer.toString(current_i)))
                                            .toArray(TypeName[]::new)).nestedClass("InitialStage"))
//                            .addTypeVariables(nonParams1ToN)
//                            .addTypeVariable(TypeVariableName.get(e.name))
//                            .addTypeVariable(TypeVariableName.get(n.name))
//                            .addTypeVariable(TypeVariableName.get(t.name))
//                            .addTypeVariable(TypeVariableName.get(v.name))
//                            .returns(ParameterizedTypeName.get(ClassName.bestGuess("VertexLayoutStruct" + i),
//                                    Stream.of(finalMethodReturnParams)
//                                            .filter(it -> !it.name.endsWith(Integer.toString(current_i)))
//                                            .toArray(TypeName[]::new)))
                            .addStatement("final TypedVertexLayoutBuilder"+next+"<" +
                                    IntStream.range(1,i).mapToObj(j -> "E"+j+",N"+j+",T"+j+",V"+j)
                                            .collect(Collectors.joining(",")) +
                                    ",E,N,T,V> builder = new TypedVertexLayoutBuilder"+next+"<>(newLast())")
                            .addStatement("return builder.initialStage()")
                            .build())
                    .build();

            final TypeSpec firstPart = builder.build();

            if (GEN_FILES) {
                final Path path = Path.of("TypedVertexLayoutBuilder" + i+".java");
                final Path file = Files.createFile(path);
                final String innerMinusClosingBracket = firstPart.toString()
                        .substring(0, firstPart.toString().lastIndexOf('}'));

                Files.write(file, List.of(imports, innerMinusClosingBracket,
                        thenMiddle, lastInnerClass.toString(), "\n}"));
            }
        }
    }

    static final String imports =
            "package com.bariumhoof.bgfx4j.layout;\n" +
            "\n" +
            "import com.bariumhoof.Capabilities;\n" +
            "import com.bariumhoof.bgfx4j.enums.BGFX_RENDERER_TYPE;\n" +
            "import com.bariumhoof.bgfx4j.layout.BgfxAttrib.*;\n" +
            "import com.bariumhoof.bgfx4j.layout.BgfxAttribType.*;\n" +
            "import com.bariumhoof.bgfx4j.layout.Num.FOUR;\n" +
            "import com.bariumhoof.bgfx4j.layout.Num.ONE;\n" +
            "import com.bariumhoof.bgfx4j.layout.Num.THREE;\n" +
            "import com.bariumhoof.bgfx4j.layout.Num.TWO;\n" +
            "import com.bariumhoof.bgfx4j.layout.Vec.*;\n" +
            "import org.jetbrains.annotations.NotNull;\n" +
            "import org.lwjgl.bgfx.BGFXVertexLayout;";

    static final String thenMiddle = "public class AttribStage<E extends BgfxAttrib> {\n" +
            "        final E attrib;\n" +
            "\n" +
            "        protected AttribStage(E attrib) {\n" +
            "            this.attrib = attrib;\n" +
            "        }\n" +
            "\n" +
            "        // vec 1\n" +
            "        public AttribTypeStageFixedPointOptQs<E,ONE,UINT8,UINT8_Vec1> uint8_vec1() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib,ONE.ONE,UINT8.UINT8);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,ONE,INT16,INT16_Vec1> int16_vec1() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib,ONE.ONE,INT16.INT16);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,ONE,HALF,HALF_Vec1> half_vec1() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, ONE.ONE, HALF.HALF);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,ONE,FLOAT,FLOAT_Vec1> float32_vec1() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, ONE.ONE, FLOAT.FLOAT);\n" +
            "        }\n" +
            "\n" +
            "        // vec 2\n" +
            "        public AttribTypeStageFixedPointOptQs<E,TWO,UINT8,UINT8_Vec2> uint8_vec2() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib, TWO.TWO,UINT8.UINT8);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,TWO,INT16,INT16_Vec2> int16_vec2() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib,TWO.TWO,INT16.INT16);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,TWO,HALF,HALF_Vec2> half_vec2() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, TWO.TWO, HALF.HALF);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,TWO,FLOAT,FLOAT_Vec2> float32_vec2() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, TWO.TWO, FLOAT.FLOAT);\n" +
            "        }\n" +
            "\n" +
            "        // vec 3\n" +
            "        public AttribTypeStageFixedPointOptQs<E,THREE,UINT8,UINT8_Vec3> uint8_vec3() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,UINT8.UINT8);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,THREE,UINT10,UINT10_Vec3> uint10_vec3() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,UINT10.UINT10);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,THREE,INT16,INT16_Vec3> int16_vec3() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib, THREE.THREE,INT16.INT16);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,THREE,HALF,HALF_Vec3> half_vec3() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, THREE.THREE, HALF.HALF);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,THREE,FLOAT,FLOAT_Vec3> float32_vec3() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, THREE.THREE, FLOAT.FLOAT);\n" +
            "        }\n" +
            "\n" +
            "        // vec 4\n" +
            "        public AttribTypeStageFixedPointOptQs<E,FOUR,UINT8,UINT8_Vec4> uint8_vec4() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib, FOUR.FOUR,UINT8.UINT8);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,FOUR,UINT10,UINT10_Vec4> uint10_vec4() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib,FOUR.FOUR,UINT10.UINT10);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQs<E,FOUR,INT16,INT16_Vec4> int16_vec4() {\n" +
            "            return new AttribTypeStageFixedPointOptQs<>(attrib,FOUR.FOUR,INT16.INT16);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,FOUR,HALF,HALF_Vec4> half_vec4() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, FOUR.FOUR, HALF.HALF);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,FOUR,FLOAT,FLOAT_Vec4> float32_vec4() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, FOUR.FOUR, FLOAT.FLOAT);\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    // asks BOTH optional questions at once, or allows both to be skipped (defaulting to false)\n" +
            "    public class AttribTypeStageFixedPointOptQs<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N,T>> extends AttribTypeStageDefaultOpts<E,N,T,V> {\n" +
            "\n" +
            "        protected AttribTypeStageFixedPointOptQs(E attrib, N num, T type) {\n" +
            "            super(attrib, num, type);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQ1T<E,N,T,V> normalized() {\n" +
            "            return new AttribTypeStageFixedPointOptQ1T<>(attrib, num, type);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageFixedPointOptQ2T<E,N,T,V> asInt() {\n" +
            "            return new AttribTypeStageFixedPointOptQ2T<>(attrib, num, type);\n" +
            "        }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "    public class AttribTypeStageFixedPointOptQ1T<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N,T>> extends AttribTypeStageDefaultOpts<E,N,T,V> {\n" +
            "        protected AttribTypeStageFixedPointOptQ1T(E attrib, N num, T type) {\n" +
            "            super(attrib, num, type, true, false);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,N,T,V> asInt() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, num, type, true, true);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    public class AttribTypeStageFixedPointOptQ2T<E extends BgfxAttrib, N extends Num, T extends BgfxAttribType, V extends Vec<N,T>> extends AttribTypeStageDefaultOpts<E,N,T,V> {\n" +
            "        protected AttribTypeStageFixedPointOptQ2T(E attrib, N num, T type) {\n" +
            "            super(attrib, num, type, false, true);\n" +
            "        }\n" +
            "\n" +
            "        public AttribTypeStageDefaultOpts<E,N,T,V> normalized() {\n" +
            "            return new AttribTypeStageDefaultOpts<>(attrib, num, type, true, true);\n" +
            "        }\n" +
            "    }";

}
