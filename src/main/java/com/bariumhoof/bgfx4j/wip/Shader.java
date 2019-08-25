package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.resource.Resources;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFX;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static org.lwjgl.bgfx.BGFX.*;

public class Shader implements Disposable, Handle {

    final short handle;
    final @Nullable String name;

    public Shader(@NotNull URL url) throws IOException {
        this(null, url);
    }

    private Shader(@Nullable String name, @NotNull URL url) throws IOException {
        this.handle = Resources.loadShader(url);
        this.name = name;
        if (Objects.nonNull(name)) {
            bgfx_set_shader_name(handle, name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shader shader = (Shader) o;
        return handle == shader.handle &&
                Objects.equals(name, shader.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handle, name);
    }

    @Override
    public String toString() {
        return "Shader{" +
                "handle=" + handle +
                ", name='" + name + '\'' +
                '}';
    }
}
