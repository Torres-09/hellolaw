package com.hellolaw.auth.provider;

public interface AuthProviderFinder {
	AuthProvider findAuthProvider(String socialType);
}