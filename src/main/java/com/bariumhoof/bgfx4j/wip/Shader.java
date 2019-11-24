package com.bariumhoof.bgfx4j.wip;

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
public class Shader implements Disposable, Handle {

    final short handle;
    @NotNull private String name;

    private Shader(@NotNull String name, short handle) {
        this.handle = handle;
        this.name = name;
        setName(name);
    }

    public void setName(@NotNull String name) {
        bgfx_set_shader_name(handle, name);
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public static Shader load(@NotNull URL url) throws IOException {
        final String defaultName = FilenameUtils.getBaseName(url.toString());
        return new Shader(defaultName, Resources.loadShader(url));
    }

    @NotNull
    public static Shader load(@NotNull String name, @NotNull URL url) throws IOException {
        return new Shader(name, Resources.loadShader(url));
    }

    @Nullable
    public static Shader loadOrNull(@NotNull URL url) {
        try {
            return load(url);
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static Shader loadOrNull(@NotNull String name, @NotNull URL url) {
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

    public @Nullable String name() {
        return name;
    }

}
