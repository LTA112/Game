package Filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class URLFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public URLFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("URLFilter: DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("URLFilter: DoAfterProcessing");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {if (debug) {
        log("URLFilter: doFilter()");
    }

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String url = httpRequest.getRequestURI();
    HttpSession session = httpRequest.getSession(false);

    doBeforeProcessing(request, response);
    
        
    // Handle redirections based on the requested URL
    if (url.endsWith("/FlappyShork")) {
        if (session != null && session.getAttribute("accountId") != null) {
            // Redirect logged-in users to the home page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork/Home");
        } else {
            // Forward non-logged-in users to index.jsp
            httpRequest.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/Home")) {
        if (session == null || session.getAttribute("accountId") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork");
        } else {
            httpRequest.getRequestDispatcher("/Home.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/Rank")) {
        if (session != null && session.getAttribute("accountId") != null) {
            httpRequest.getRequestDispatcher("/Rank.jsp").forward(request, response);
        } else {
            httpRequest.getRequestDispatcher("/Rank.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/Home/Rank")) {
        if (session == null || session.getAttribute("accountId") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork/Rank");
        } else {
            httpRequest.getRequestDispatcher("/Rank.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/guestGame")) {
        if (session != null && session.getAttribute("accountId") != null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork/Home/Game");
        } else {
            httpRequest.getRequestDispatcher("/guestGameWindow.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/Home/Game")) {
        if (session == null || session.getAttribute("accountId") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork/guestGame");
        } else {
            httpRequest.getRequestDispatcher("/gameWindow.jsp").forward(request, response);
        }
    } else if (url.endsWith("/FlappyShork/adminDashboard")) {
        if (session != null && session.getAttribute("admin") != null) {
            // Forward to admin dashboard if session has admin attribute
            httpRequest.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
        } else {
            // Redirect to login or error page if not authorized
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/FlappyShork");
        }
    } else {
        // Pass request to the next filter or servlet
        chain.doFilter(request, response);
    }

    doAfterProcessing(request, response);
}

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("URLFilter: Initializing filter");
            }
        }
    }

    @Override
    public void destroy() {
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    private void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
