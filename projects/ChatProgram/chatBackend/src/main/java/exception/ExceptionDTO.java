package exception;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionDTO {
    private int code;
    private String message, stacktrace;

    public ExceptionDTO(Throwable ex, int code, boolean debug, String message) {
        this.code = code;
        this.message = message;
        if(debug){
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            System.out.println("fail:" + sw.toString());
            this.stacktrace = sw.toString();
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStacktrace() {
        return stacktrace;
    }
}
