package encryption.util;

import javax.security.auth.x500.X500Principal;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.time.LocalDate;

/**
 * @author hezhidong
 * @date 2022/1/20 下午3:26
 * @description 证书工具类
 */
public class CertUtil {
    private String dname = "C=CN,ST=GuangDong,L=GuangZhou,CN=coco";

    private String CA_SHA = "SHA512withRSA";

    public CertUtil(String dname) {
        this.dname = dname;
    }

    public CertUtil() {
    }

    static {
        if (Security.getProperty(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private KeyPairGenerator kpg = null;

    private KeyPair keyPair = null;

    public KeyPair getKeyPair() {
        return keyPair;
    }

    /** 生成X509证书 */
    public X509Certificate genCert(PublicKey publicKey, PrivateKey privateKey) {
        X509Certificate cert = null;

        try {
            X509V3CertificateGenerator certGenerator = new X509V3CertificateGenerator();
            SecureRandom random = new SecureRandom();

            BigInteger serialNumber = BigIntegers.createRandomInRange(BigInteger.ONE, BigInteger.valueOf(Long.MAX_VALUE), random);
            // 序列号
            certGenerator.setSerialNumber(serialNumber);
            // 颁发者证书
            X500Principal issuerDN = new X500Principal(dname);
            // 使用者证书，在自签证书中，颁发者证书和使用者证书为同一个
            X500Principal subjectDN = issuerDN;
            // 设置颁发者
            certGenerator.setIssuerDN(issuerDN);
            // 设置有效期1个月
            LocalDate start = LocalDate.now();
            LocalDate end = start.plusMonths(1);
            certGenerator.setNotBefore(DateUtil.localDate2Date(start));
            certGenerator.setNotAfter(DateUtil.localDate2Date(end));
            // 设置使用者
            certGenerator.setSubjectDN(subjectDN);
            // 公钥
            certGenerator.setPublicKey(publicKey);
            // 签名算法
            certGenerator.setSignatureAlgorithm(CA_SHA);
            cert = certGenerator.generateX509Certificate(privateKey, "BC");
        } catch (SignatureException | NoSuchProviderException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cert;
    }
}
