package me.addo6544.frost.auth;

public class AuthResult {
    private final long result;
    private final String token;

    public AuthResult(long result, String token){
        this.result = result;
        this.token = token;
    }

    public long getResult() {
        return result;
    }

    public String getToken() {
        return token;
    }
}
