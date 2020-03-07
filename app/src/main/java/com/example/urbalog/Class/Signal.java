package com.example.urbalog.Class;

import java.io.Serializable;

public enum Signal implements Serializable {
    NEXT_TURN,
    CANCEL_NEXT_TURN,
    CHECK_GOALS,
    GAME_OVER,
    UPDATE_PLAYER,
    RETURN_PLAYER;
}
