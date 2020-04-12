package com.bariumhoof.bgfx4j.shaders;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.resource.Resources;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;

import static org.lwjgl.bgfx.BGFX.bgfx_destroy_shader;
import static org.lwjgl.bgfx.BGFX.bgfx_set_shader_name;

@ToString
@EqualsAndHashCode
public class VertexShader implements Disposable, Handle {

    final short handle;

    private VertexShader(@NotNull String name, short handle) {
        this.handle = handle;
        setName(name);
    }

    public void setName(@NotNull String name) {
        bgfx_set_shader_name(handle, name);
    }

    @NotNull
    public static VertexShader load(@NotNull URL url) throws IOException {
        final String defaultName = FilenameUtils.getBaseName(url.toString());
        return new VertexShader(defaultName, Resources.loadShader(url));
    }

    @NotNull
    public static VertexShader load(@NotNull String name, @NotNull URL url) throws IOException {
        return new VertexShader(name, Resources.loadShader(url));
    }

    @Nullable
    public static VertexShader loadOrNull(@NotNull URL url) {
        try {
            return load(url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static VertexShader loadOrNull(@NotNull String name, @NotNull URL url) {
        try {
            return load(name, url);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void dispose() {
        bgfx_destroy_shader(handle);
    }

    @Override
    public short handle() {
        return handle;
    }

}
