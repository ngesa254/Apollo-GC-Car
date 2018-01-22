package com.droidmarvin.apollogccar;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class MotorLogic {

    private Gpio pin1Motor1;
    private Gpio pin2Motor1;

    private Gpio pin1Motor2;
    private Gpio pin2Motor2;

    public void MotorController() {

        PeripheralManagerService service = new PeripheralManagerService();

        try {
            // Right
            pin1Motor1 = service.openGpio("BCM17");
            pin2Motor1 = service.openGpio("BCM27");
            // Left
            pin1Motor2 = service.openGpio("BCM23");
            pin2Motor2 = service.openGpio("BCM24");
            // Configuration
            pin1Motor1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            pin2Motor1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            pin1Motor2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            pin2Motor2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Control functions

    public void forward() {
        setPinValues(true, false, false, true);
    }
    public void backward() {
        setPinValues(false, true, true, false);
    }
    public void stop(){
        setPinValues(false, false,false,false);
    }
    public void turnLeft() {
        setPinValues(false, false, false, true);
    }
    public void turnRight() {
        setPinValues(true, false, false, false);
    }

    private void setPinValues(boolean p11, boolean p12,
                              boolean p21, boolean p22 ) {
        try {
            pin1Motor1.setValue(p11);
            pin2Motor1.setValue(p12);
            pin1Motor2.setValue(p21);
            pin2Motor2.setValue(p22);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
