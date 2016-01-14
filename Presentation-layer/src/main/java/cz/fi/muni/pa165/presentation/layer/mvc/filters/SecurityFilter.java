package cz.fi.muni.pa165.presentation.layer.mvc.filters;

import cz.fi.muni.pa165.api.layer.dto.UserAuthenticateDTO;
import cz.fi.muni.pa165.api.layer.dto.UserDTO;
import cz.fi.muni.pa165.api.layer.facade.UserFacade;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author JaroslavDavidek
 */
public class SecurityFilter implements Filter{

    public void init(FilterConfig fc) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        String logname = creds[0];
        String password = creds[1];

        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext()).getBean(UserFacade.class);
        UserDTO matchingUser = userFacade.findUserByEmail(logname);
        
        //no user with email
        if (matchingUser == null) {
            response401(response);
            return;
        }
        
        //user is not an admin
        if (!userFacade.isAdmin(matchingUser)) {
            response401(response);
            return;
        }

        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setUserId(matchingUser.getId());
        userAuthenticateDTO.setPassword(password);

        if (!userFacade.authenticate(userAuthenticateDTO)) { //invalid credentials
            response401(response);
            return;
        }
        request.setAttribute("authenticatedUser", matchingUser);
        fc.doFilter(request, response);
    }

    public void destroy() {
    }
    
    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }
    
    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("401 Unauthorized access");
    }
}
