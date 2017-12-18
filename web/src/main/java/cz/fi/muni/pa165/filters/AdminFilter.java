package cz.fi.muni.pa165.filters;

import cz.fi.muni.pa165.dto.MemberDTO;
import cz.fi.muni.pa165.facade.MemberFacade;
import cz.fi.muni.pa165.library.persistance.entity.Member;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/member/list", "/books/add", "/member/show"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        Object emailObject = request.getSession().getAttribute("authenticatedEmail");
        if (emailObject == null) {
            response.sendRedirect("/pa165/login/login");
            return;
        }
        String email = (String) emailObject;
        MemberFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(MemberFacade.class);
        MemberDTO memberDTO = userFacade.findByEmail(email);
        if (memberDTO == null) {
            response.sendRedirect("/pa165/login/login");
            return;
        }
        if (!memberDTO.isIsAdmin()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Only admin can view this page</body></html>");
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