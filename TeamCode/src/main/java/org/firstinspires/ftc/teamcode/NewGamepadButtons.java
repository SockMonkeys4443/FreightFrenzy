package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class NewGamepadButtons {

    Gamepad gamepad;

    boolean aReleased;
    boolean bReleased;
    boolean xReleased;
    boolean yReleased;

    public NewGamepadButtons(Gamepad gamepad) {
        this.gamepad = gamepad;
        aReleased = true;
        bReleased = true;
        xReleased = true;
        yReleased = true;
    }

    public boolean[] checkButtonValues() {
        boolean[] values = {false, false, false, false};

        if (gamepad.a && aReleased) {
            aReleased = false;
            values[0] = true;
        } else if (!gamepad.a){
            aReleased = true;
        }

        if (gamepad.b && bReleased) {
            bReleased = false;
            values[1] = true;
        } else if (!gamepad.b) {
            bReleased = true;
        }

        if (gamepad.x && xReleased) {
            xReleased = false;
            values[2] = true;
        } else if (!gamepad.x){
            xReleased = true;
        }

        if (gamepad.y && yReleased) {
            yReleased = false;
            values[3] = true;
        } else if (!gamepad.y) {
            yReleased = true;
        }

        return values;
    }

}
