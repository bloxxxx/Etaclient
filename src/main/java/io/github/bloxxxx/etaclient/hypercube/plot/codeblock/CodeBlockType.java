package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl.BracketCodeBlock;
import io.github.bloxxxx.etaclient.hypercube.plot.varitem.VarItemConstructingException;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

//TODO: Finish
public enum CodeBlockType {

    BRACKET("bracket", null, BracketCodeBlock::fromJson);

    // attributes: NOT, LS-CANCEL

    // func (data -> name)
    // event (action -> name) [LS-CANCEL]
    // game_event (action -> name) [LS-CANCEL]
    // process (data -> name)
    // entity_event (action -> name) [LS-CANCEL]
    // player_action (action, target)
    // if_player (action, target) [NOT]
    // entity_action (action, target)
    // if_entity (action, target) [NOT]
    // set_var (action)
    // if_var (action) [NOT]
    // game_action (action)
    // if_game (action) [NOT]
    // else (/)
    // select_obj (action, subAction [NOT]
    // call_func (data -> name)
    // call_process (data -> name)
    // control (action)
    // repeat (action, subAction) [NOT]











    public final String id;
    public final String block;
    private final CodeBlockConstructor constructor;
    CodeBlockType(String id, @Nullable String block, CodeBlockConstructor constructor) {
        this.id = id;
        this.block = block;
        this.constructor = constructor;
    }

    CodeBlockType(String block, CodeBlockConstructor constructor) {
        this("block", block, constructor);
    }

    public CodeBlock construct(JsonObject data) {
        try {
            return constructor.construct(data);
        } catch (NullPointerException | IllegalStateException | UnsupportedOperationException e) {
            throw new VarItemConstructingException(e);
        }
    }


    public static CodeBlockType fromIdAndBlock(String id, @Nullable String block) {
        for (CodeBlockType type : values()) {
            if (type.id.equals(id) && Objects.equals(type.block, block)) return type;
        }
        return null;
    }
}
