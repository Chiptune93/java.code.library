## Java Filter

public class aesEncFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ChiperRequestWrapper requestWrapper = new ChiperRequestWrapper((HttpServletRequest)request);
		
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String)params.nextElement();
		}
		try {
			requestWrapper.checkEnc((HttpServletRequest)request);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
      // Exception Error Page
			responseWrapper.sendRedirect("/error_500.jsp");
		}
		chain.doFilter(requestWrapper, responseWrapper);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}

