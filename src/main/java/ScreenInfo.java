/*
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: ScreenInfo.java
 */

/**
 * Data class that holds static information about the program, mainly related to the window size
 */
public class ScreenInfo {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
}
