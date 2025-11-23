package com.erval.argos.m2m.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.m2m.internal.auth.server.InMemoryInternalClientStore;
import com.m2m.internal.auth.server.InternalClientStore;
import com.m2m.internal.auth.server.InternalOAuthService;
import com.m2m.internal.auth.server.InternalTokenIssuer;
import com.m2m.internal.auth.server.RsaInternalTokenIssuer;
import com.m2m.internal.auth.server.key.PemKeyLoader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * Wires internal M2M authentication components (keys, clients, token issuer).
 * <p>
 * Exposes beans for OAuth service and token issuance backed by local key
 * material and client configuration.
 */
@Configuration
@EnableConfigurationProperties(InternalClientProperties.class)
public class InternalAuthConfig {

    private static final String PUBLIC_KEY_PATH = "keys/internal-oauth-public.pem";
    private static final String PRIVATE_KEY_PATH = "keys/internal-oauth-private.pem";

    @Value("${internal.oauth.server.issuer}")
    private String issuer;

    /**
     * Loads RSA key pair from classpath PEM files for signing and validation.
     *
     * @return RSA key pair for internal token issuance
     * @throws IllegalStateException when PEM files cannot be read or parsed
     */
    @Bean
    public KeyPair internalKeyPair() {
        String publicPem = readPemFromClasspath(PUBLIC_KEY_PATH);
        String privatePem = readPemFromClasspath(PRIVATE_KEY_PATH);
        return PemKeyLoader.loadRsaKeyPair(publicPem, privatePem);
    }

    /**
     * Creates the token issuer using the configured key pair and issuer value.
     *
     * @param internalKeyPair RSA key pair used to sign tokens
     * @return token issuer for internal OAuth
     */
    @Bean
    public InternalTokenIssuer internalTokenIssuer(KeyPair internalKeyPair) {
        long ttlSeconds = 600;
        return new RsaInternalTokenIssuer(internalKeyPair, issuer, "",ttlSeconds);
    }

    /**
     * Builds the in-memory client store from configuration properties.
     *
     * @param props client properties loaded from configuration
     * @return client store to back OAuth validation
     */
    @Bean
    public InternalClientStore internalClientStore(InternalClientProperties props) {
        Map<String, InMemoryInternalClientStore.Client> map =
            props.clients().entrySet().stream()
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    e -> toClient(e.getKey(),e.getValue())
                ));
        return new InMemoryInternalClientStore(map);
    }

    /**
     * Provides the OAuth service that issues and validates internal tokens.
     *
     * @param store  client store to validate client credentials
     * @param issuer token issuer to mint JWTs
     * @return configured internal OAuth service
     */
    @Bean
    public InternalOAuthService internalOAuthService(
        InternalClientStore store,
        InternalTokenIssuer issuer) {
        return new InternalOAuthService(store, issuer);
    }

    private String readPemFromClasspath(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            return new String(is.readAllBytes(), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read PEM from classpath: " + path, e);
        }
    }

    private InMemoryInternalClientStore.Client toClient(
        String clientId,
        InternalClientProperties.ClientProps props
    ) {
        Set<String> scopes = props.scopes() == null
            ? Set.of()
            : Set.copyOf(props.scopes());

        return new InMemoryInternalClientStore.Client(
            clientId,
            props.secret(),
            scopes
        );
    }
}
