package com.framework.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtility {
    public static String takeFullScreenshot(WebDriver driver, String testcaseName){
        String screenshotPath = System.getProperty("user.dir")+"/test-output/screenshots/"+testcaseName+DateUtility.getStringDate("_ddMMyyyy_HHmmss")+".png";
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshotPath;
    }
}
