package cors;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CorsResponseFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        response.getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, Content-Type");
        
    }

}
