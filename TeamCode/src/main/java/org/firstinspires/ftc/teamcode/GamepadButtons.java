package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadButtons {

    public enum Button {
        A, B, X, Y
    }

    Gamepad gamepad;
    private boolean aWasPressed;
    private boolean bWasPressed;
    private boolean xWasPressed;
    private boolean yWasPressed;

    private boolean aStatus, bStatus, xStatus, yStatus;

    public GamepadButtons(Gamepad gamepad) {
        this.gamepad = gamepad;
        aWasPressed = false;
        bWasPressed = false;
        xWasPressed = false;
        yWasPressed = false;
    }

    //tells you whether a button is being held or not at any moment
    private boolean buttonHeld(Button button) {
        switch (button) {
            case A: return gamepad.a;
            case B: return gamepad.b;
            case X: return gamepad.x;
            case Y: return gamepad.y;
            default: return false; //should never trigger
        }
    }

    //tells you whether a button was held the last time a check was made
    private boolean buttonWasHeld(Button button) {
        switch (button) {
            case A: return aWasPressed;
            case B: return bWasPressed;
            case X: return xWasPressed;
            case Y: return yWasPressed;
            default: return false; //should never trigger
        }
    }

    private void setWasHeld(Button button, boolean held) {
        switch (button) {
            case A: aWasPressed = held;
            case B: bWasPressed = held;
            case X: xWasPressed = held;
            case Y: yWasPressed = held;
        }
    }

    private void setStatus(Button button, boolean status) {
        switch (button) {
            case A: aStatus = status;
            case B: bStatus = status;
            case X: xStatus = status;
            case Y: yStatus = status;
        }
    }

    public boolean getStatus(Button button) {
        switch (button) {
            case A: return aStatus;
            case B: return bStatus;
            case X: return xStatus;
            case Y: return yStatus;
            default: return false; //should never trigger
        }
    }

    //tells you if a button has been pressed since the last time you checked, so should only trigger one time per button press
    private boolean buttonPressed(Button button) {
        //if the button is held down, and wasnt held down the last time we checked
        if(buttonHeld(button) && !buttonWasHeld(button)) {
            setWasHeld(button, true);
            return true;
        }
        else return false;
    }

    //needs to be called by the teleop constantly
    public void update() {
        for(Button button : Button.values()) {
            if (!buttonHeld(button)) setWasHeld(button, false);
            setStatus(button, buttonPressed(button));
        }
    }
}
