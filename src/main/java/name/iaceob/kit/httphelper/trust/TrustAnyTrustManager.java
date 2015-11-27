package name.iaceob.kit.httphelper.trust;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by cox on 2015/11/27.
 */
public class TrustAnyTrustManager implements X509TrustManager {
    public TrustAnyTrustManager() {}

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }
}
