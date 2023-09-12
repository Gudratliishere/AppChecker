package com.gudratli;

import org.ini4j.Ini;

import java.io.IOException;
import java.net.URL;

/**
 * @author : Dunay Gudratli
 * @mailto : d.qudretli@gmail.com
 * @since : 12.09.2023
 **/
public class ResourceUtil {
    public static final String APP_NAME = getAppName();
    public static final String APP_PATH = getAppPath();


    private static String getAppName() {
        Ini ini = getIni();

        assert ini != null;
        return ini.get("header", "appName");
    }

    private static String getAppPath() {
        Ini ini = getIni();

        assert ini != null;
        return ini.get("header", "appPath");
    }

    private static Ini getIni() {
        URL url = ResourceUtil.class.getClassLoader().getResource("app-info.ini");

        try {
            assert url != null;
            return new Ini(url);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
