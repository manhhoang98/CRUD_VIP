package codegym.config.model.filter;

import codegym.config.model.Account;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/products", "/create","/edit"})
public class Filter_Login extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession httpSession = req.getSession();
        Account account = (Account) httpSession.getAttribute("account");
        if (account != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/login");
        }
    }
}
