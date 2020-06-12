/**
 * This class is originally from the lwjgl3 demos, but has had Mesh's constructor modified to work for BGFX instead of
 * opengl. Also the package name was modified to match new filepath.
 *
 * https://github.com/LWJGL/lwjgl3-demos/blob/ca5a636fa5c614d8f6e257727408cb6060d40345/src/org/lwjgl/demo/opengl/assimp/WavefrontObjDemo.java#L374
 */
/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package com.bariumhoof.bgfx4j.examples.utils;

import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.assimp.Assimp.*;

public class Model {

    public AIScene scene;
    public List<Mesh> meshes;
    public List<Material> materials;

    public Model(AIScene scene) {

        this.scene = scene;

        int meshCount = scene.mNumMeshes();
        PointerBuffer meshesBuffer = scene.mMeshes();
        meshes = new ArrayList<>();
        for (int i = 0; i < meshCount; ++i) {
            meshes.add(new Mesh(AIMesh.create(meshesBuffer.get(i))));
        }

        int materialCount = scene.mNumMaterials();
        PointerBuffer materialsBuffer = scene.mMaterials();
        materials = new ArrayList<>();
        for (int i = 0; i < materialCount; ++i) {
            materials.add(new Material(AIMaterial.create(materialsBuffer.get(i))));
        }
    }

    public void free() {
        aiReleaseImport(scene);
        scene = null;
        meshes = null;
        materials = null;
    }

    public static void main(String[] args) {

    }

    public static class Mesh {

        public AIMesh mesh;
        public int vertexArrayBuffer;
        public int normalArrayBuffer;
        public int elementArrayBuffer;
        public int elementCount;

        public Mesh(AIMesh mesh) {
            this.mesh = mesh;

//            final VertexLayout layout = VertexLayout.builder()
//                    .beginWith(BGFX_ATTRIB.POSITION, 3, BGFX_ATTRIB_TYPE.FLOAT, false, false)
//                    .thenUse(BGFX_ATTRIB.NORMAL, 3, BGFX_ATTRIB_TYPE.FLOAT, false, false)
////                    .thenUse(BGFX_ATTRIB.COLOR0, 4, BGFX_ATTRIB_TYPE.UINT8, true, false)
//                    .build();

            final int numVertices = mesh.mNumVertices();
            final IntBuffer numUVComponents = mesh.mNumUVComponents();
            final int numFaces = mesh.mNumFaces();

//            mesh.mNormals();




            // todo need to reduce overhead on creating these (way too many overloads)
            // instead use factory for first, second, third, etc. arg
            // user will have to pass same
//            final var vb = TypedDynamicVertexBuffer.create(build, 0, EnumSet.noneOf(BGFX_BUFFER.class));

            /*
            In general we have:

            A type safe builder is a structured,behavioral factory
            A

            (1) A Consumer of a type T of subtypes
            (2) A Constructor of T and of subtypes
            (3) A builder of a factory of consumer of t (1)
            (4) A

            its important that exceptions can be handled too, like for example uint10

            enum Num
            enum Attrib
            enum AttribType

            product type Vec of Num * AttribType
            product type stage of Attrib * Vec

            sequence type of Stage, Stage, ... with no duplicates


             */

            // we can drop N and E info

//            final var build = TypedVertexLayout
//                    .beginWith(POSITION, THREE, FLOAT, false, false)
//                    .thenUse(NORMAL, THREE, FLOAT, false, false)
//                    .thenUse(COLOR0, FOUR, UINT8, true, false)
//                    .build();
//
//            final var buffer = TypedDynamicVertexBuffer.create(build, 0, EnumSet.noneOf(BGFX_BUFFER.class));
//            // todo add "begin edit" which returns a type that takes edits, then can call "commit edits"
//
//            // generic update (accepts arrays)
//            buffer.update(0,
//                    vertex(vec(0.0f, 0.0f, 0.0f),
//                            vec(0.0f, 0.0f, 0.0f),
//                            vec((byte)0, (byte)0, (byte)0, (byte)0)),
//                    vertex(vec(1.0f, 2.0f, 0.0f),
//                            vec(0.0f, 0.0f, 0.0f),
//                            vec((byte)0, (byte)0, (byte)0, (byte)0))
//            );

//            TypedDynamicVertexBuffer.create()
//            Vec.FLOAT_Vec3.


//            final var buffer =
//                    TypedDynamicVertexBuffer.create(build, 3, EnumSet.noneOf(BGFX_BUFFER.class));
//
//            final FLOAT_3[] values = { () -> Args.of(3.0f, 2.0f, 2.0f) };

//            buffer.update(0, values);

//            vertexArrayBuffer = glGenBuffersARB();
//            glBindBufferARB(GL_ARRAY_BUFFER_ARB, vertexArrayBuffer);
            AIVector3D.Buffer vertices = mesh.mVertices();

            for (AIVector3D vertex : vertices) {
//                vertex.
            }
//
////            BGFX.bgfx_make_ref(vertices);
//
//            nglBufferDataARB(GL_ARRAY_BUFFER_ARB, AIVector3D.SIZEOF * vertices.remaining(),
//                    vertices.address(), GL_STATIC_DRAW_ARB);
//
//            normalArrayBuffer = glGenBuffersARB();
//            glBindBufferARB(GL_ARRAY_BUFFER_ARB, normalArrayBuffer);
            AIVector3D.Buffer normals = mesh.mNormals();
//            nglBufferDataARB(GL_ARRAY_BUFFER_ARB, AIVector3D.SIZEOF * normals.remaining(),
//                    normals.address(), GL_STATIC_DRAW_ARB);
//
//            int faceCount = mesh.mNumFaces();
//            elementCount = faceCount * 3;
//            IntBuffer elementArrayBufferData = BufferUtils.createIntBuffer(elementCount);
//            AIFace.Buffer facesBuffer = mesh.mFaces();
//            for (int i = 0; i < faceCount; ++i) {
//                AIFace face = facesBuffer.get(i);
//                if (face.mNumIndices() != 3) {
//                    throw new IllegalStateException("AIFace.mNumIndices() != 3");
//                }
//                elementArrayBufferData.put(face.mIndices());
//            }
//            elementArrayBufferData.flip();
//            elementArrayBuffer = glGenBuffersARB();
//            glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, elementArrayBuffer);
//            glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, elementArrayBufferData,
//                    GL_STATIC_DRAW_ARB);
        }
    }

    public static class Material {

        public AIMaterial mMaterial;
        public AIColor4D mAmbientColor;
        public AIColor4D mDiffuseColor;
        public AIColor4D mSpecularColor;

        public Material(AIMaterial material) {

            mMaterial = material;

            mAmbientColor = AIColor4D.create();
            if (aiGetMaterialColor(mMaterial, AI_MATKEY_COLOR_AMBIENT,
                    aiTextureType_NONE, 0, mAmbientColor) != 0) {
                throw new IllegalStateException(aiGetErrorString());
            }
            mDiffuseColor = AIColor4D.create();
            if (aiGetMaterialColor(mMaterial, AI_MATKEY_COLOR_DIFFUSE,
                    aiTextureType_NONE, 0, mDiffuseColor) != 0) {
                throw new IllegalStateException(aiGetErrorString());
            }
            mSpecularColor = AIColor4D.create();
            if (aiGetMaterialColor(mMaterial, AI_MATKEY_COLOR_SPECULAR,
                    aiTextureType_NONE, 0, mSpecularColor) != 0) {
                throw new IllegalStateException(aiGetErrorString());
            }
        }
    }
}