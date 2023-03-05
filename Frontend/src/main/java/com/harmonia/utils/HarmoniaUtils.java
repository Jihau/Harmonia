package com.harmonia.utils;

import com.harmonia.HarmoniaApplication;
import com.harmonia.po.UserPO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Date;

public class HarmoniaUtils {

    public static HttpHeaders generateRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static UserPO simulateUser() {
        return new UserPO(4, "psoliz20320",
                "percysolizr@gmail.com",
                "",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQksVdR-HGuX9xo_u6KGBAwJPm4WN_1ivEMVkbEaD7vARYxV71r6T1nCPcxOLAjvMRXiZQ&usqp=CAU",
                "My bio", new Date());
    }

    public static void loadJavaFxView(String title, String viewName, Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HarmoniaApplication.class.getResource(viewName));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene((root), 1280, 720);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
