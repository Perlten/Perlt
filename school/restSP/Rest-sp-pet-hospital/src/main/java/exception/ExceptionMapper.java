package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable exception) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ExceptionDTO dto = new ExceptionDTO(exception, 400, true, exception.getMessage());
        String json = gson.toJson(dto);
        
        return Response.status(dto.getCode()).entity(json).type(MediaType.APPLICATION_JSON).build();
    }

}
