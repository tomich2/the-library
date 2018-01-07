package cz.fi.muni.pa165.filters;

import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.facade.MemberFacade;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authenticated filter.
 * @author xtlamich
 *
 */


@WebFilter(urlPatterns = {
        "/loans/*",
        "/loanItems/*"})
public class AuthenticatedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        MemberDTO member = (MemberDTO) request.getSession().getAttribute("authenticatedUser");

        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/login/login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
