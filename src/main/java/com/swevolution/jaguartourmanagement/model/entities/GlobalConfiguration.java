/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_String_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "GLOBAL_CONFIGURATION", catalog = "JTM")
@XmlRootElement
public class GlobalConfiguration extends PK_String_Entity implements Serializable {

    private BigDecimal tipoDeCambio;
    private String appName;
    private String appDescription;
    private int maxJeep;
    private int maxBuggy;
    private String http;
    private String serverName;
    private String pathToResources;
    private String pathToWebResources;
    private String googleMapsAPIKey;
    private String addThisURL;
    private String adminEmailAccount;
    private String contactEmailAccount;
    private String contactPhone;
    private String description;
    private String enterpriseName;
    private String enterpriseNameShort;
    private String enterpriseNameTrail;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10,
            scale = 2, name = "EXCHANGE_RATE",
            columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal exchangeRate;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10,
            scale = 2, name = "GLOBAL_DISCOUNT",
            columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal globalDiscount;

    @Column(name = "GA_TRACKING_CODE")
    private String googleAnalyticsTrackingCode;

    @Column(name = "LINK_TO_FACEBOOK")
    private String linkToFacebook;

    @Column(name = "LINK_TO_TWITTER")
    private String linkToTwitter;

    @Column(name = "LINK_TO_YOUTUBE")
    private String linkToYoutube;

    @Column(name = "LINK_TO_INSTAGRAM")
    private String linkToInstagram;

    @Column(name = "PAYPAL_LIVE_ACCOUNT")
    private String paypalLiveAccount;

    @Column(name = "PAYPAL_LIVE_ID")
    private String paypalLiveID;

    @Column(name = "PAYPAL_MODE")
    private String paypalMode;

    @Column(name = "PAYPAL_SANDBOX_ACCOUNT")
    private String paypalSandboxAccount;

    @Column(name = "PAYPAL_SANDBOX_ID")
    private String paypalSandboxID;

    @Column(name = "PATH_TO_LOGO")
    private String pathToLogo;

    @Column(name = "SEARCH_LIMIT")
    private int searchLimit;

    @Column(name = "TOP_TOUR_LIMIT")
    private int topTourLimit;

    public GlobalConfiguration() {

    }

    public BigDecimal getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(BigDecimal tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public int getMaxJeep() {
        return maxJeep;
    }

    public void setMaxJeep(int maxJeep) {
        this.maxJeep = maxJeep;
    }

    public int getMaxBuggy() {
        return maxBuggy;
    }

    public void setMaxBuggy(int maxBuggy) {
        this.maxBuggy = maxBuggy;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPathToResources() {
        return pathToResources;
    }

    public void setPathToResources(String pathToResources) {
        this.pathToResources = pathToResources;
    }

    public String getPathToWebResources() {
        return pathToWebResources;
    }

    public void setPathToWebResources(String pathToWebResources) {
        this.pathToWebResources = pathToWebResources;
    }

    public String getGoogleMapsAPIKey() {
        return googleMapsAPIKey;
    }

    public void setGoogleMapsAPIKey(String googleMapsAPIKey) {
        this.googleMapsAPIKey = googleMapsAPIKey;
    }

    public String getAddThisURL() {
        return addThisURL;
    }

    public void setAddThisURL(String addThisURL) {
        this.addThisURL = addThisURL;
    }

    public String getAdminEmailAccount() {
        return adminEmailAccount;
    }

    public void setAdminEmailAccount(String adminEmailAccount) {
        this.adminEmailAccount = adminEmailAccount;
    }

    public String getContactEmailAccount() {
        return contactEmailAccount;
    }

    public void setContactEmailAccount(String contactEmailAccount) {
        this.contactEmailAccount = contactEmailAccount;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseNameShort() {
        return enterpriseNameShort;
    }

    public void setEnterpriseNameShort(String enterpriseNameShort) {
        this.enterpriseNameShort = enterpriseNameShort;
    }

    public String getEnterpriseNameTrail() {
        return enterpriseNameTrail;
    }

    public void setEnterpriseNameTrail(String enterpriseNameTrail) {
        this.enterpriseNameTrail = enterpriseNameTrail;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getGlobalDiscount() {
        return globalDiscount;
    }

    public void setGlobalDiscount(BigDecimal globalDiscount) {
        this.globalDiscount = globalDiscount;
    }

    public String getGoogleAnalyticsTrackingCode() {
        return googleAnalyticsTrackingCode;
    }

    public void setGoogleAnalyticsTrackingCode(String googleAnalyticsTrackingCode) {
        this.googleAnalyticsTrackingCode = googleAnalyticsTrackingCode;
    }

    public String getLinkToFacebook() {
        return linkToFacebook;
    }

    public void setLinkToFacebook(String linkToFacebook) {
        this.linkToFacebook = linkToFacebook;
    }

    public String getLinkToTwitter() {
        return linkToTwitter;
    }

    public void setLinkToTwitter(String linkToTwitter) {
        this.linkToTwitter = linkToTwitter;
    }

    public String getLinkToYoutube() {
        return linkToYoutube;
    }

    public void setLinkToYoutube(String linkToYoutube) {
        this.linkToYoutube = linkToYoutube;
    }

    public String getLinkToInstagram() {
        return linkToInstagram;
    }

    public void setLinkToInstagram(String linkToInstagram) {
        this.linkToInstagram = linkToInstagram;
    }

    public String getPaypalLiveAccount() {
        return paypalLiveAccount;
    }

    public void setPaypalLiveAccount(String paypalLiveAccount) {
        this.paypalLiveAccount = paypalLiveAccount;
    }

    public String getPaypalLiveID() {
        return paypalLiveID;
    }

    public void setPaypalLiveID(String paypalLiveID) {
        this.paypalLiveID = paypalLiveID;
    }

    public String getPaypalMode() {
        return paypalMode;
    }

    public void setPaypalMode(String paypalMode) {
        this.paypalMode = paypalMode;
    }

    public String getPaypalSandboxAccount() {
        return paypalSandboxAccount;
    }

    public void setPaypalSandboxAccount(String paypalSandboxAccount) {
        this.paypalSandboxAccount = paypalSandboxAccount;
    }

    public String getPaypalSandboxID() {
        return paypalSandboxID;
    }

    public void setPaypalSandboxID(String paypalSandboxID) {
        this.paypalSandboxID = paypalSandboxID;
    }

    public String getPathToLogo() {
        return pathToLogo;
    }

    public void setPathToLogo(String pathToLogo) {
        this.pathToLogo = pathToLogo;
    }

    public int getSearchLimit() {
        return searchLimit;
    }

    public void setSearchLimit(int searchLimit) {
        this.searchLimit = searchLimit;
    }

    public int getTopTourLimit() {
        return topTourLimit;
    }

    public void setTopTourLimit(int topTourLimit) {
        this.topTourLimit = topTourLimit;
    }

}
