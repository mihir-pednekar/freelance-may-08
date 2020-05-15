
package AuthFilter;

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
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
                        
			String reqURI = reqt.getRequestURI();
			if (reqURI.contains("/index.xhtml")
					|| reqURI.contains("faces/providerRegistration.xhtml")
					|| reqURI.contains("faces/freelancerRegistration.xhtml")
					|| reqURI.contains("javax.faces.resource")){
                            chain.doFilter(request, response);
                        }
                        else if((ses != null && ses.getAttribute("user_id") != null && ses.getAttribute("user_role") != null))
                        {
                            String role = (String) ses.getAttribute("user_role");
                            if(!role.toLowerCase().equals("admin")
                                    && (reqURI.contains("faces/adminHome.xhtml")
                                    || reqURI.contains("faces/jobs.xhtml")
                                    || reqURI.contains("faces/users.xhtml")
                                    || reqURI.contains("faces/freelancer_admin.xhtml")
                                    || reqURI.contains("faces/provider_admin.xhtml"))){
                                resp.sendRedirect(reqt.getContextPath() + "/faces/unauthorized.xhtml");
                            }
                            if(!role.toLowerCase().equals("freelancer")
                                    && (reqURI.contains("faces/freelancerHome.xhtml")
                                    || reqURI.contains("faces/freelancerProfile.xhtml")
                                    || reqURI.contains("faces/freelancerJobs.xhtml"))){
                                resp.sendRedirect(reqt.getContextPath() + "/faces/unauthorized.xhtml");
                            }
                            if(!role.toLowerCase().equals("provider")
                                    && (reqURI.contains("faces/providerHome.xhtml")
                                    || reqURI.contains("faces/assignJob.xhtml")
                                    || reqURI.contains("faces/providerProfile.xhtml")
                                    || reqURI.contains("faces/viewfreelancer.xhtml")
                                    || reqURI.contains("faces/jobRegistration.xhtml")
                                    || reqURI.contains("faces/providerJobs.xhtml"))){
                                resp.sendRedirect(reqt.getContextPath() + "/faces/unauthorized.xhtml");
                            }
                            else{
                                chain.doFilter(request, response);
                            }
                        }
			else
				resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
