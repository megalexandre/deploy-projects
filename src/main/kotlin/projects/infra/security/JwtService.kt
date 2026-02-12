package projects.infra.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey
import kotlin.text.get

@Service
class JwtService {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @Value("\${jwt.expiration}")
    private var jwtExpiration: Long = 0

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        return claimsResolver(extractAllClaims(token))
    }

    fun generateToken(userDetails: UserDetails): String {
        val extraClaims = if(userDetails is CustomUserDetails){
            mapOf("userId" to userDetails.userId)
        } else {
            HashMap()
        }


        return generateToken(extraClaims, userDetails)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extraClaims, userDetails, jwtExpiration)
    }

    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)

            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey())
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun extractUserId(token: String): String? {
        return extractClaim(token) { claims -> claims["userId"] as? String }
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Base64.getDecoder().decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
