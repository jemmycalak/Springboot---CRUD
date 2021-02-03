package com.rest.demo.auth.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap
import io.jsonwebtoken.security.Keys

import javax.crypto.SecretKey
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.io.Decoders
import java.nio.charset.StandardCharsets

@Service
class JwtUtils: Serializable {

    private val serialVersionUID = 234234523523L

    val JWT_TOKEN_VALIDITY = (10 * 60 * 60).toLong()

    //retrieve username from jwt token
    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    //retrieve expiration date from jwt token
    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    //for retrieving any information from token we will need the secret key
    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getKeySecret())
            .build()
            .parseClaimsJws(token).body
    }

    //check if the token has expired
    private fun isTokenExpired(token: String?): Boolean? {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    //generate token for user
    fun generateToken(userDetails: UserDetails): String? {
        val claims: Map<String, Any?> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    fun getKeySecret(): SecretKey? {
        val secretKey =
            "skdjfsgkljdfgjsdfhkgjsdhflksghdfkljsghlkdfhkljdf32452345j2h34lj5kh234lkj5h2l34kjh52lk34jh5j2h34j5hv234jk5v2h345vk2j345vk234h5bkj2h354j2hg4kjh52gk34jhg5k2j3h4g5kj2hg45kh24hj52g3k4jhg5k2j34hg5kj2hg4j5k34jk5234hj52gk34jh2gk3"
        val secretString = Encoders.BASE64.encode(secretKey.encodeToByteArray())
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretString))

        //return Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    private fun doGenerateToken(claims: Map<String, Any?>, subject: String): String? {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(getKeySecret())
//            .signWith(SignatureAlgorithm.RS512, "")
            .compact()
    }

    //validate token
    fun validateToken(token: String?, userDetails: UserDetails): Boolean? {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }
}
