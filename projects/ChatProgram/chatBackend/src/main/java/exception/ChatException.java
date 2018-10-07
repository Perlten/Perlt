package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ChatException extends Exception implements javax.ws.rs.ext.ExceptionMapper<ChatException> {

    public static final boolean DEBUG = true;

    private String errorMessage;
    private int errorCode;

    public ChatException(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public Response toResponse(ChatException exception) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ExceptionDTO dto = new ExceptionDTO(exception, exception.getErrorCode(), DEBUG, exception.getErrorMessage());
        String json = gson.toJson(dto);

        return Response.status(dto.getCode()).entity(json).type(MediaType.APPLICATION_JSON).build();
    }
    
}
