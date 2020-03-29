package com.example.urbalog.Class;

import java.io.Serializable;

public enum Signal implements Serializable {
    // Host sig :
    NEW_CONNECTION,

    // Player sig :
    MARKET_RECEIVED,
    BET_RECEIVED,
    GAME_RECEIVED,
    ROLE_RECEIVED,
    RESTART_GAME,
    START_GAME,

    NEXT_TURN,
    CANCEL_NEXT_TURN,
    CHECK_GOALS,
    GAME_OVER,
    UPDATE_PLAYER,
    RETURN_PLAYER,
    BACKUP_PLAYER;
}
