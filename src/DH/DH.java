package DH;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class DH {
    private static final String KEY_ALGORITHM = "DH";
    private static final String SELECT_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 512;
    //��Կ
    private static final String PUBLIC_KEY = "DHPublicKey";
    //˽Կ
    private static final String PRIVATE_KEY = "DHPrivateKey";
    
    /**
           * ��ʼ���׷���Կ
     * @return Map �׷���ԿMap
     * @throws Exception
     */
    public Map<String, Object> initKey() throws Exception{
        //ʵ������Կ��������
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //��ʼ����Կ��������
        keyPairGenerator.initialize(KEY_SIZE);
        //������Կ��
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //�׷���Կ
        DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
        //�׷�˽Կ
        DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
        //����Կ�Դ洢��Map��
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /**
     * 	 ��ʼ���ҷ���Կ
     * @param key �׷���Կ
     * @return Map �ҷ���ԿMap
     * @throws Exception
     */
    public Map<String, Object> initKey(byte[] key) throws Exception{
        //�����׷���Կ
        //�Ա���ĸ�ʽ��ʾ��Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //ʵ������Կ����
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //������Կ
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //�ɼ׷���Կ�����ҷ���Կ
        DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
        //ʵ������Կ��������
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //��ʼ����Կ��������
        keyPairGenerator.initialize(dhParameterSpec);
        //������Կ��
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //�ҷ���Կ
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        //�ҷ�˽Լ
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        //����Կ�Դ洢��Map��
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
    
    /**
     * 	���ɱ��ؼ�����Կ
     *
     * @return
     */
    private SecretKeySpec getSecretKey(final byte[] key) {
        //��������ָ���㷨��Կ�������� KeyGenerator ����
        KeyGenerator kg = null;
    
        try {
            kg = KeyGenerator.getInstance(SELECT_ALGORITHM);
    
            //������Ҫ��AES���м��ܣ���AESҪ����Կ����Ϊ 128
            kg.init(128, new SecureRandom(key));
    
            //����һ����Կ
            SecretKey secretKey = kg.generateKey();
    
            return new SecretKeySpec(secretKey.getEncoded(), SELECT_ALGORITHM);// ת��ΪAESר����Կ
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return null;
    }
    /**
     * ����
     * @param data ����������
     * @param key ��Կ
     * @return byte[] ��������
     * @throws Exception
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception{

        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// ����������
    
            byte[] byteContent = data;
    
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// ��ʼ��Ϊ����ģʽ��������
    
            byte[] result = cipher.doFinal(byteContent);// ����
            return result;
//            return Base64Utils.encode(result);//ͨ��Base64ת�뷵��
            
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return null;

    }
    
    /**
     * ����
     * @param data ����������
     * @param key ��Կ
     * @return byte[] ��������
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception{

        try {
            //ʵ����
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
    
            //ʹ����Կ��ʼ��������Ϊ����ģʽ
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
    
            //ִ�в���
            byte[] result = cipher.doFinal(data);
    
            return result;
            
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return null;
    }
    
    /**
     * 	������Կ
     * @param publicKey ��Կ
     * @param privateKey ˽Կ
     * @return byte[] ������Կ
     * @throws Exception
     */
    public byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception{
        //ʵ������Կ����
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //��ʼ����Կ
        //���ڱ�����Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        //������Կ
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //��ʼ��˽Կ
        //��Կ����ת��
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        //����˽Կ
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //ʵ����
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        //��ʼ��
        keyAgree.init(priKey);
        keyAgree.doPhase(pubKey, true);
        //���ɱ�����Կ
        SecretKey secretKey = keyAgree.generateSecret(SELECT_ALGORITHM);
        return secretKey.getEncoded();
    }
    
    /**
     * 	ȡ��˽Կ
     * @param keyMap ��ԿMap
     * @return byte[] ˽Կ
     * @throws Exception
     */
    public byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }
    
    /**
     *	 ȡ�ù�Կ
     * @param keyMap ��ԿMap
     * @return byte[] ��Կ
     * @throws Exception
     */
    public byte[] getPublicKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 	������
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
    }
        
}

