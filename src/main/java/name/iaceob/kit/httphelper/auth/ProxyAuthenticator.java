package name.iaceob.kit.httphelper.auth;


import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by cox on 2015/11/27.
 */
public class ProxyAuthenticator extends Authenticator {

    private String auth;
    private String passwd;
    public ProxyAuthenticator(String auth, String passwd) {
        this.auth = auth;
        this.passwd = passwd;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return (new PasswordAuthentication(this.auth, this.passwd.toCharArray()));
    }
}
