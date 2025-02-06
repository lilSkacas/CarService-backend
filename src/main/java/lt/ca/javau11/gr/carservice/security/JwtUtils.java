package lt.ca.javau11.gr.carservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lt.ca.javau11.gr.carservice.dto.UserDto;
import lt.ca.javau11.gr.carservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    private final UserService userService;

    @Autowired
    public JwtUtils(UserService userService) {
        this.userService = userService;
    }

    @Value("${javau11.app.jwtSecret}")
    private String jwtSecret;

    @Value("${javau11.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public void setSecretKey(String key) {
        jwtSecret = key;
    }

    public void setExpirationMs(int ms) {
        jwtExpirationMs = ms;
    }


    public String generateJwtToken(Authentication authentication) {

        UserDto userPrincipal = (UserDto) authentication.getPrincipal();

        return Jwts.builder()
                .subject((userPrincipal.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public Key toKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    private Key key() {
        return toKey(jwtSecret);
    }

    public String getUserNameFromJwtToken(String token) {

        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            logger.debug("trying to validate jwt token");
            Jwts.parser().verifyWith((SecretKey)key()).build().parse(authToken);
            logger.debug("jwt token is valid \n" + authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        logger.trace("Authentication successfull.");

        String username = getUserNameFromJwtToken(token);
        UserDetails userDetails = userService.loadUserByUsername(username);
        logger.debug("UserDetails loaded: " + userDetails.getUsername() + ", Roles: " + userDetails.getAuthorities());

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
