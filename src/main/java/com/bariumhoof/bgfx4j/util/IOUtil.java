///**
// * Copy of lwjgl's:
// * https://raw.githubusercontent.com/LWJGL/lwjgl3/master/modules/samples/src/test/java/org/lwjgl/demo/util/IOUtil.java
// *
// * While playing around with shader c. Original licence posted below.
// *
// * Other than the package name, nothing has been changed.
// */
///*
// * Copyright LWJGL. All rights reserved.
// * License terms: https://www.lwjgl.org/license
// */
//package com.bariumhoof.bgfx4j.util;
//
//import org.lwjgl.*;
//
//import java.io.*;
//import java.nio.*;
//import java.nio.channels.*;
//import java.nio.file.*;
//
//import static org.lwjgl.BufferUtils.*;
//
//public final class IOUtil {
//
//    private IOUtil() {
//    }
//
//    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
//        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
//        buffer.flip();
//        newBuffer.put(buffer);
//        return newBuffer;
//    }
//
//    /**
//     * Reads the specified resource and returns the raw data as a ByteBuffer.
//     *
//     * @param resource   the resource to read
//     * @param bufferSize the initial buffer size
//     *
//     * @return the resource data
//     *
//     * @throws IOException if an IO error occurs
//     */
//    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
//        ByteBuffer buffer;
//
//        Path path = Paths.get(resource);
//        if (Files.isReadable(path)) {
//            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
//                buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);
//                while (fc.read(buffer) != -1) {
//                    ;
//                }
//            }
//        } else {
//            try (
//                    InputStream source = IOUtil.class.getClassLoader().getResourceAsStream(resource);
//                    ReadableByteChannel rbc = Channels.newChannel(source)
//            ) {
//                buffer = createByteBuffer(bufferSize);
//
//                while (true) {
//                    int bytes = rbc.read(buffer);
//                    if (bytes == -1) {
//                        break;
//                    }
//                    if (buffer.remaining() == 0) {
//                        buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
//                    }
//                }
//            }
//        }
//
//        buffer.flip();
//        return buffer;
//    }
//
//}