package com.bloomscope.bloomscopedesktopapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Static<ObjectType> {
    public static ScrollPane homeScrolPan;
    public static VBox home;
    public static BorderPane homeBorderPan;
    public static Scene scene;
    private static LoginResponse loginResponse;
    private static ParentSignUpResponse parentSignUpResponse;
    private static OrganizationSignUpResponse organizationSignUpResponse;
    public static ScrollPane studentDashboard;

    public String makeHTTPrequest(ObjectType objectType, String post, String url) {
        // create a HttpClient instance
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // create a HttpPost request with your URL
            HttpPost httpPost = new HttpPost(url);
    //      create a StringEntity object with your Login object in JSON format
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(objectType);
            StringEntity requestEntity = new StringEntity(requestJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // execute the request and get the response
            CloseableHttpResponse response = httpclient.execute(httpPost);

            System.out.println(response.getStatusLine());
            // get the response entity and deserialize it using the ObjectMapper class
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
//            OrganizationSignUpResponse organizationSignUpResponse = mapper.readValue(responseJson, OrganizationSignUpResponse.class);

//            // print the response object
//            System.out.println(parentSignUpResponse.getStatus());
//            System.out.println(parentSignUpResponse.getMessage());

            // close the response and HttpClient instances
            response.close();
            httpclient.close();
            return jsonResponse;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception: \n calss: Static \n Method: makeHTTPrequest()");
        }
        return null;
    }

    public void loadPage(String page) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            VBox vbox = (VBox) fxmlLoader.load();

            Static.home.getChildren().clear();

            Static.home.getChildren().addAll(vbox.getChildren());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
