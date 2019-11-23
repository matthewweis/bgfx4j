package com.bariumhoof.bgfx4j.wip;

import com.bariumhoof.bgfx4j.Disposable;
import com.bariumhoof.bgfx4j.Handle;
import com.bariumhoof.bgfx4j.resource.Resources;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.bgfx.BGFX;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static org.lwjgl.bgfx.BGFX.*;

@ToString
@EqualsAndHashCode
public class Shader implements Disposable, Handle {

    final short handle;
    final @Nullable String name;

    private Shader(@NotNull URL url) throws IOException {
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

}
