## AES Encryption util

public class AESEncryption {
	private static volatile AESEncryption INSTANCE;

	static String secretKey = null; // 32bit

	static String IV = ""; // 16bit
	public static AESEncryption getInstance(String jid) {
		secretKey = jid.substring(0, 16);
		if (INSTANCE == null) {
			synchronized (AESEncryption.class) {
				if (INSTANCE == null) INSTANCE = new AESEncryption();
			}
		}
		return INSTANCE;
	}

	private AESEncryption () {
    // key and iv is same 
    // need to high level of security, use random hash value.
		secretKey = secretKey.substring(0, 16);
		IV = secretKey.substring(0, 16);
	}

	public static String decrypt(String ciphertext) {
		try {
			final int keySize = 256;
			final int ivSize = 128;
			// 텍스트를 BASE64 형식으로 디코드 한다.
			byte[] ctBytes = Base64.decodeBase64(ciphertext.getBytes("UTF-8"));
			// 솔트를 구한다. (생략된 8비트는 Salted__ 시작되는 문자열이다.)
			byte[] saltBytes = Arrays.copyOfRange(ctBytes, 8, 16);
			System.out.println(Hex.encodeHexString(saltBytes));
			// 암호화된 테스트를 구한다.( 솔트값 이후가 암호화된 텍스트 값이다.)
			byte[] ciphertextBytes = Arrays.copyOfRange(ctBytes, 16, ctBytes.length);
			// 비밀번호와 솔트에서 키와 IV값을 가져온다.
			byte[] key = new byte[keySize / 8];
			byte[] iv = new byte[ivSize / 8];
			EvpKDF(secretKey.getBytes("UTF-8"), keySize, ivSize, saltBytes, key, iv);
			// 복호화
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
			byte[] recoveredPlaintextBytes = cipher.doFinal(ciphertextBytes);
			return new String(recoveredPlaintextBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
		return EvpKDF(password, keySize, ivSize, salt, 1, "MD5", resultKey, resultIv);
	}

	private static byte[] EvpKDF(byte[] password, int keySize, int ivSize, byte[] salt, int iterations, String hashAlgorithm, byte[] resultKey, byte[] resultIv) throws NoSuchAlgorithmException {
		keySize = keySize / 32;
		ivSize = ivSize / 32;
		int targetKeySize = keySize + ivSize;
		byte[] derivedBytes = new byte[targetKeySize * 4];
		int numberOfDerivedWords = 0;
		byte[] block = null;
		MessageDigest hasher = MessageDigest.getInstance(hashAlgorithm);
		while (numberOfDerivedWords < targetKeySize) {
			if (block != null) {
				hasher.update(block);
			}
			hasher.update(password);
			// Salting
			block = hasher.digest(salt);
			hasher.reset();
			// Iterations : 키 스트레칭(key stretching)
			for (int i = 1; i < iterations; i++) {
				block = hasher.digest(block);
				hasher.reset();
			}
			System.arraycopy(block, 0, derivedBytes, numberOfDerivedWords * 4, Math.min(block.length, (targetKeySize - numberOfDerivedWords) * 4));
			numberOfDerivedWords += block.length / 4;
		}
		System.arraycopy(derivedBytes, 0, resultKey, 0, keySize * 4);
		System.arraycopy(derivedBytes, keySize * 4, resultIv, 0, ivSize * 4);
		return derivedBytes; // key + iv
	}

	// 암호화
	public String AES_Encode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		return enStr;
	}

	// 복호화
	public String AES_Decode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));
		byte[] byteStr = Base64.decodeBase64(str.getBytes());
		return new String(c.doFinal(byteStr), "UTF-8");
	}
}
