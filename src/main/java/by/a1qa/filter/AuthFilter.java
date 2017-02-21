package by.a1qa.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static by.a1qa.helpers.CommonData.AQA_JIRA_CLIENT_SESSION_ATTR;

import static by.a1qa.constants.PagesMap.PAGES_MAP;

/**
 * Created by tbegu_000 on 09.02.2017.
 */
public class AuthFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            LOGGER.debug("Session MaxInactiveInterval: " +
                    ((HttpServletRequest) request).getSession().getMaxInactiveInterval() / 60.0 +
                    " | Session creation time: " +
                    new Date(((HttpServletRequest) request).getSession().getCreationTime()) +
                    " | Session last accessed time: " +
                    new Date(((HttpServletRequest) request).getSession().getLastAccessedTime()));
            String uri = ((HttpServletRequest) request).getRequestURI();
            if (PAGES_MAP.containsKey(uri)) {
                verifyAccess(request, response, chain, uri, PAGES_MAP.get(uri));
            }
            else if (PAGES_MAP.containsKey(uri.split("/")[1])) {
                verifyAccess(request, response, chain, uri, PAGES_MAP.get(uri.split("/")[1]));
            }
            else {
                LOGGER.info("Page not found: " + uri);
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
            request.getRequestDispatcher("/WEB-INF/pages/error500.jsp")
                    .forward(request, response);
        }
    }

    private void verifyAccess(ServletRequest request,
                              ServletResponse response,
                              FilterChain chain,
                              String uri,
                              boolean access) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR) == null
                && !access) {
            LOGGER.info("Access Denied: " + uri);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "");
        }
        else if (((HttpServletRequest) request).getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR) != null
                && access) {
            request.getRequestDispatcher("/reportController/reports")
                    .forward(request, response);
        }
        else {
            chain.doFilter(request, response);
        }
    }
}