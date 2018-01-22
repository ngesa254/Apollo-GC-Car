package com.droidmarvin.apollogccar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MotorLogic motorLogic = new MotorLogic();

        CarLogicHttpServer server = new CarLogicHttpServer(8090, this,
                new CarLogicHttpServer().CommandListener (){

                    @Override
                    public void onCommand(String command) {
                        Log.d(TAG, "Command received ["+command+"]");
                        if (command.equals("F"))
                            motorLogic.forward();
                        else if (command.equals("B"))
                            motorLogic.backward();
                        else if (command.equals("S"))
                            motorLogic.stop();
                        else if (command.equals("L"))
                            motorLogic.turnLeft();
                        else if (command.equals("R"))
                            motorLogic.turnRight();
                    }

                });
    }
}
