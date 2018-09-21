package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonNotFoundExceptionMapper extends Exception implements ExceptionMapper<PersonNotFoundExceptionMapper> {

    public static final boolean DEBUG = false;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String message;

    public PersonNotFoundExceptionMapper() {
    }

    public PersonNotFoundExceptionMapper(String message) {
        super(message);
    }

    @Override
    public Response toResponse(PersonNotFoundExceptionMapper exception) {
        ExceptionDTO edto = new ExceptionDTO(exception, 404, DEBUG, message);
        return Response.status(edto.getCode()).entity(gson.toJson(edto)).type(MediaType.APPLICATION_JSON).build();
    }

}
