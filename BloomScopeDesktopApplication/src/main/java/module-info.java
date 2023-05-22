module com.bloomscope.bloomscopedesktopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens com.bloomscope.bloomscopedesktopapplication to javafx.fxml;
    exports com.bloomscope.bloomscopedesktopapplication;

    opens com.bloomscope.bloomscopedesktopapplication.organization_dashboard to javafx.fxml;
    exports com.bloomscope.bloomscopedesktopapplication.organization_dashboard to javafx.fxml; //if(Caused by: java.lang.IllegalAccessException: )

    opens com.bloomscope.bloomscopedesktopapplication.parent_dashboard to javafx.fxml;
    exports com.bloomscope.bloomscopedesktopapplication.parent_dashboard to javafx.fxml; //if(Caused by: java.lang.IllegalAccessException: )

    opens com.bloomscope.bloomscopedesktopapplication.student_dashboard to javafx.fxml;
    exports com.bloomscope.bloomscopedesktopapplication.student_dashboard to javafx.fxml; //if(Caused by: java.lang.IllegalAccessException: )


}