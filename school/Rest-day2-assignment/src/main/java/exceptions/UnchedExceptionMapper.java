package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnchedExceptionMapper implements ExceptionMapper<Throwable>{

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UnchedExceptionMapper() {
    }
    
    @Override
    public Response toResponse(Throwable exception) {
        boolean debug = PersonNotFoundExceptionMapper.DEBUG;
        ExceptionDTO dTO = new ExceptionDTO(exception, 400, debug, "Internal Server Problem. We are sorry for the inconvenience");
        return Response.status(dTO.getCode()).entity(gson.toJson(dTO)).type(MediaType.APPLICATION_JSON).build();
    }
}
