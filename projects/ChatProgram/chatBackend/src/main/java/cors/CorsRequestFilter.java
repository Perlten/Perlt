package cors;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class CorsRequestFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        if(request.getRequest().getMethod().equals("OPTIONS")){
            request.abortWith(Response.ok().build());
        }
    }

}
