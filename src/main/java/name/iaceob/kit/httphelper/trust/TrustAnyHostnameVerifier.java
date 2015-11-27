package name.iaceob.kit.httphelper.trust;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by cox on 2015/11/27.
 */
public class TrustAnyHostnameVerifier implements HostnameVerifier {
    public TrustAnyHostnameVerifier() {}

    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
