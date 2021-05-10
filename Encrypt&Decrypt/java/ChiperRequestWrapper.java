## java Encrypt & Decrypt logic

public class ChiperRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, Object> paramMap;
	@SuppressWarnings("unchecked")
	public ChiperRequestWrapper (HttpServletRequest request) {
		super(request);
		this.paramMap = new HashMap<String, Object>(request.getParameterMap());
	}

	public void checkEnc(HttpServletRequest request) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    // in this case , use jsession id to key
		HttpSession httpSession = request.getSession(true);
    // in javascript, throw parameter "enc" to know encrypt data
		if (!JavaUtil.NVL(JavaUtil.request().getParameterMap().get("enc"), "").equals("")) {
      // key is 16byte 
			String jid = httpSession.getId().substring(0, 16);
			AESEncryption aes = AESEncryption.getInstance(jid);
			@SuppressWarnings("unchecked")
			Enumeration<String> params = request.getParameterNames();
      // encrypt each parameter
			while (params.hasMoreElements()) {
				String name = (String)params.nextElement();
				if (!JavaUtil.NVL(request.getParameter(name), "").equals("")) setParameter(name, aes.AES_Decode(request.getParameter(name)));
			}
			setParameter("enc", "Y");
		}
	}

	@Override
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if (values != null && values.length > 0) {
			return values[0];
		}
		return null;
	}

	@Override
	public Map<String, Object> getParameterMap() {
		for (int i = 0; i < paramMap.size(); i++) {}
		return paramMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(paramMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return (String[])paramMap.get(name);
	}

	public void setParameter(String name, String value) {
		String[] oneParam = {value};
		setParameter(name, oneParam);
	}

	public void setParameter(String name, String[] values) {
		paramMap.put(name, values);
	}
}

