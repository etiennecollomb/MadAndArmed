package com.geekmecrazy.madandarmed.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.geekmecrazy.madandarmed.MadAndArmed;
import com.badlogic.gdx.physics.bullet.softbody.btEigen;

public class DesktopLauncher {
    public static void main (String[] arg) {

        int CONF_USED = 0;

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "";

        /**
        On Android, you can find those screen sizes:
        xlarge screens are at least 960dp x 720dp
        large screens are at least 640dp x 480dp
        normal screens are at least 470dp x 320dp
        small screens are at least 426dp x 320dp

         HTC M8 1080 x 1920
         */
        int phoneScreenWidth = 1920;
        int phoneScreenHeight = 1080;
        int windowsSize = 75; // en %

        int windowWidth = phoneScreenWidth / 100 * windowsSize;
        int windowHeight = phoneScreenHeight / 100 * windowsSize;

        int posX = 50; //50 (premier ecran) -550 (second ecran)
        int posY = 200;

        //my phone screen resolution
        config.width = windowWidth;
        config.height = windowHeight;

        //my desktop position (second screen)
        config.x = posX;
        config.y = posY;

        new LwjglApplication(new MadAndArmed(), config);
    }
}
