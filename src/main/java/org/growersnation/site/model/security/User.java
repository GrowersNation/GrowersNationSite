package org.growersnation.site.model.security;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.growersnation.site.utils.ObjectUtils;
import org.openid4java.discovery.DiscoveryInformation;

import java.util.Set;
import java.util.UUID;

/**
 * <p>Simple representation of a User to provide the following to Resources:<br>
 * <ul>
 * <li>Storage of user state</li>
 * </ul>
 * </p>
 */
public class User {

  /**
   * Numerical ID to allow faster indexing (for internal use)
   */
  private Long id = null;

  /**
   * <p>A username (optional for anonymity reasons)</p>
   */
  private String userName = null;

  /**
   * <p>A user password (not plaintext and optional for anonymity reasons)</p>
   */
  private String passwordDigest = null;

  /**
   * <p>The OpenID discovery information used in phase 1 of authenticating against an OpenID server</p>
   * <p>Once the OpenID identifier is in place, this can be safely deleted</p>
   */
  private DiscoveryInformation openIDDiscoveryInformation;

  /**
   * <p>An OpenID identifier used in phase 2 after authenticating against an OpenID server</p>
   */
  private String openIDIdentifier = null;

  /**
   * <p>The user's first name (optional)</p>
   */
  private String firstName = null;


  /**
   * <p>The user's last name (optional)</p>
   */
  private String lastName = null;

  /**
   * <p>UUID to allow public User reference without
   * revealing a sequential ID that could be guessed.
   * Typically used as an API key</p>
   */
  private String apiKey = null;

  /**
   * <p>Used as a shared secret to authenticate this user to the upstream server. Typically
   * part of an HMAC authentication scheme.</p>
   */
  private String secretKey = null;

  /**
   * <p>An email address</p>
   */
  private String emailAddress = null;

  /**
   * A shared secret between the cluster and the user's browser that is revoked
   * when the session ends
   */
  private UUID sessionToken;
  private Set<Authority> authorities=Sets.newLinkedHashSet();

  /**
   * @return The internal unique ID
   */
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return The public API key used when identifying the user during HMAC authentication
   */
  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * @return The private shared secret for upstream communications
   */
  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  /**
   * @return The user name to authenticate with the client
   */
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return The user's email address
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   *
   * @return The OpenID discovery information (phase 1 of authentication)
   */
  public DiscoveryInformation getOpenIDDiscoveryInformation() {
    return openIDDiscoveryInformation;
  }

  public void setOpenIDDiscoveryInformation(DiscoveryInformation openIDDiscoveryInformation) {
    this.openIDDiscoveryInformation = openIDDiscoveryInformation;
  }

  /**
   * @return The OpenID identifier (phase 2 of authentication)
   */
  public String getOpenIDIdentifier() {
    return openIDIdentifier;
  }

  public void setOpenIDIdentifier(String openIDIdentifier) {
    this.openIDIdentifier = openIDIdentifier;
  }

  /**
   * @return The user's first name
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return The user's last name
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return The digested password to provide authentication between the user and the client
   */
  public String getPasswordDigest() {
    return passwordDigest;
  }

  /**
   * <h3>Note that it is expected that Jasypt or similar is used prior to storage</h3>
   *
   * @param passwordDigest The password digest
   */
  public void setPasswordDigest(String passwordDigest) {
    this.passwordDigest = passwordDigest;
  }

  /**
   * @return The session key
   */
  public UUID getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(UUID sessionToken) {
    this.sessionToken = sessionToken;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final User other = (User) obj;

    return ObjectUtils.isEqual(
      apiKey, other.apiKey,
      secretKey, other.secretKey
    );
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(apiKey);
  }

  /**
   * @param requiredAuthorities The required authorities
   *
   * @return True if the user has all the required authorities
   */
  public boolean hasAllAuthorities(Set<Authority> requiredAuthorities) {
    return getAuthorities().containsAll(requiredAuthorities);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
      .add("userName", userName)
      .add("password", "**********")
      .add("emailAddress", emailAddress)
      .add("openIDIdentifier", openIDIdentifier)
      .add("firstName", firstName)
      .add("lastName", lastName)
      .toString();
  }
}
