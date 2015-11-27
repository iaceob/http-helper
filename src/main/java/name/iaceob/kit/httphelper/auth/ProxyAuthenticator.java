package name.iaceob.kit.httphelper.auth;


import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by cox on 2015/11/27.
 */
public class ProxyAuthenticator extends Authenticator {

    private String account;
    private String password;
    public ProxyAuthenticator(String account, String password) {
        this.account = account;
        this.password = password;
    }
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return (new PasswordAuthentication(this.account, this.password.toCharArray()));
    }
}
