package io.github.bloxxxx.etaclient.hypercube.plot.codeblock;

import com.google.gson.JsonObject;
import io.github.bloxxxx.etaclient.hypercube.plot.codeblock.impl.*;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public enum CodeBlockType {

    BRACKET("bracket", null, BracketCodeBlock::fromJson),
    FUNCTION("func", FunctionCodeBlock::fromJson),
    PLAYER_EVENT("event", PlayerEventCodeBlock::fromJson),
    GAME_EVENT("game_event", GameEventCodeBlock::fromJson),
    PROCESS("process", ProcessCodeBlock::fromJson),
    ENTITY_EVENT("entity_event", EntityEventCodeBlock::fromJson),
    PLAYER_ACTION("player_action", PlayerActionCodeBlock::fromJson),
    IF_PLAYER("if_player", IfPlayerCodeBlock::fromJson),
    ENTITY_ACTION("entity_action", EntityActionCodeBlock::fromJson),
    IF_ENTITY("if_entity", IfEntityCodeBlock::fromJson),
    SET_VAR("set_var", SetVarCodeBlock::fromJson),
    IF_VAR("if_var", IfVarCodeBlock::fromJson),
    GAME_ACTION("game_action", GameActionCodeBlock::fromJson),
    IF_GAME("if_game", IfGameCodeBlock::fromJson),
    ELSE("else", ElseCodeBlock::fromJson),
    SELECT_OBJECT("select_obj", SelectObjCodeBlock::fromJson),
    CALL_FUNCTION("call_func", CallFunctionCodeBlock::fromJson),
    CALL_PROCESS("call_process", CallProcessCodeBlock::fromJson),
    CONTROL("control", ControlCodeBlock::fromJson),
    REPEAT("repeat", RepeatCodeBlock::fromJson);

    // attributes: NOT, LS-CANCEL

    // X func (data -> name)
    // X event (action -> name) [LS-CANCEL]
    // X game_event (action -> name) [LS-CANCEL]
    // X process (data -> name)
    // X entity_event (action -> name) [LS-CANCEL]
    // X player_action (action, target)
    // X if_player (action, target) [NOT]
    // X entity_action (action, target)
    // X if_entity (action, target) [NOT]
    // X set_var (action)
    // X if_var (action) [NOT]
    // X game_action (action)
    // X if_game (action) [NOT]
    // X else (/)
    // X select_obj (action, subAction) [NOT]
    // X call_func (data -> name)
    // X call_process (data -> name)
    // X control (action)
    // X repeat (action, subAction) [NOT]











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
            throw new CodeBlockConstructingException(e);
        }
    }


    public static CodeBlockType fromIdAndBlock(String id, @Nullable String block) {
        for (CodeBlockType type : values()) {
            if (type.id.equals(id) && Objects.equals(type.block, block)) return type;
        }
        return null;
    }
}
