package io.github.bloxxxx.etaclient.hypercube.plot.varitem;

import com.google.gson.JsonObject;

public interface VarItemConstructor {
    VarItem construct(JsonObject data);
}
