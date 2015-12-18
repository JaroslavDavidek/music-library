package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.UserAuthenticateDTO;
import cz.fi.muni.pa165.api.layer.facade.UserFacade;
import java.net.MalformedURLException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URISyntaxException;
import java.net.URL;
import javax.servlet.http.Cookie;

/**
 *
 * @author Jergus Fasanek
 */
@Controller
@RequestMapping("/")
public class AuthenticateController {
    
    @Autowired
    private UserFacade userFacade;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpServletResponse response;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new UserAuthenticateDTO());
        return "login";
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(
            //DO NOT CHANGE the order of first two parameters
            @Valid @ModelAttribute("user") UserAuthenticateDTO formBean,
            @RequestParam(value = "error", required = false) String error,
            BindingResult bindingResult,
            Model model,
            @RequestParam(defaultValue = "/home") String redirectTo,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        return "/login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("user", new UserAuthenticateDTO());
        return "error";
    }
    
    private void createCookie(HttpServletRequest pHttpRequest, HttpServletResponse pHttpResponse, String pCookieName, String pCookieValue) {
        try {
            Cookie cookie = new Cookie(pCookieName, pCookieValue);
            URL url = new URL(pHttpRequest.getRequestURL().toString());
            cookie.setDomain(url.getHost());
            cookie.setPath("/*");
            cookie.setMaxAge(-1);

            pHttpResponse.addCookie(cookie);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
