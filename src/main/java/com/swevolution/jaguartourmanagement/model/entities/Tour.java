/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.jaguartourmanagement.model.entities;

import com.swevolution.jaguartourmanagement.model.entities.util.PK_Long_Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rxkx
 */
@Entity
@Table(name = "TOUR", catalog = "JTM")
@XmlRootElement
public class Tour extends PK_Long_Entity implements Serializable {

    @NotNull
    private String name;
    @Lob
    private String description;
    private boolean buceo;
    private boolean manejaVehiculo;
    private String grupo;

    private BigDecimal imaAdultoUSD;
    private BigDecimal imaNinoUSD;
    private BigDecimal imaAdultoMXN;
    private BigDecimal imaNinoMXN;

    @Column(name = "ZONE_OPERATION", columnDefinition = "varchar(100) default 'unknown'")
    private String operation;
    @Column(name = "OPERATIONAL_COST", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal operationalCost;

    @Column(name = "OPERATION_COST_CHILD", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal operationalCostChild;

    @Column(name = "OPERATION_COST_INFANT", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal operationalCostInfant;

    @Column(name = "MIN_SALE_PRICE", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal minSalePrice;

    @Column(name = "MIN_SALE_PRICE_CHILD", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal minSalePriceChild;

    @Column(name = "MIN_SALE_PRICE_INFANT", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal minSalePriceInfant;

    @Column(name = "MAX_DISCOUNT_ALLOWED", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal maxDiscountAllowed;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "SEARCH_KEYWORDS")
    private String searchKeywords;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "SEARCH_PHOTO")
    private String searchPhoto;

    @Size(min = 0, max = 200)
    @Column(nullable = true, length = 200, name = "SHORT_DESCRIPTION")
    private String shortdescription;

    @Column(name = "TOUR_SCALE", columnDefinition = "int(10) default 0")
    private int tourScale;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10, scale = 2, name = "ADULT_PRICE", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal adultPrice;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 12, scale = 2, name = "INFANT_PRICE", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal infantPrice;

    @Column(name = "TOP_TOUR", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean topTour;

    @Column(name = "AQUATIC", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean aquatic;

    @Column(name = "CULTURAL", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean cultural;

    @Column(name = "ARCHEOLOGICAL", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean archeological;

    @Column(name = "UNDERGROUND", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean underground;

    @Column(name = "NIGHT", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean night;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "LINK_VIDEO")
    private String linkvideo;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "LINK_PREZI")
    private String linkprezi;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "LINK_TRIPADVISOR")
    private String linktripadvisor;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "LINK_GOOGLEMAPS")
    private String linkgooglemaps;

    @Size(min = 0, max = 255)
    @Column(nullable = true, length = 255, name = "TOUR_TYPE")
    private String type;

    @Size(min = 0, max = 255)
    @Column(nullable = true, length = 255, name = "TOUR_LANG", columnDefinition = "varchar(255) default 'en'")
    private String lang;

    @Size(min = 0, max = 255)
    @Column(nullable = true, length = 255, name = "DESCRIPTION_LANG", columnDefinition = "varchar(255) default 'en'")
    private String descriptionLang;

    @Size(min = 0, max = 255)
    @Column(nullable = true, length = 255, name = "TIME_ZONE")
    private String timezone;

    @Lob
    @Column(nullable = true, name = "OPTIONAL_ACTIVITIES", columnDefinition = "tinyint(1) default 0")
    private String optionalactivities;

    @Lob
    @Column(nullable = true, name = "EXCLUDES")
    private String excludes;

    @Lob
    @Column(nullable = true, name = "INCLUDES")
    private String includes;

    @Lob
    @Column(nullable = true, name = "RECOMMENDATIONS")
    private String recommendations;

    @Lob
    @Column(nullable = true, name = "REGULATIONS")
    private String regulations;

    @Lob
    @Column(nullable = true, name = "RESTRICTIONS")
    private String restrictions;

    @Lob
    @Column(nullable = true, name = "ITINERARY")
    private String itinerary;

    @Column(name = "IS_TRANSPORTATIONS_INCLUDED", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean isTransportationIncluded;

    @Size(min = 0, max = 500)
    @Column(nullable = true, length = 500, name = "TRANSPORTATION_NOTES")
    private String transportationNotes;

    @Column(name = "MIN_CHILD_AGE", columnDefinition = "INTEGER(1) default 5")
    private int minChildAge;

    @Column(name = "MAX_CHILD_AGE", columnDefinition = "INTEGER(1) default 11")
    private int maxChildAge;

    @Column(name = "CHILDREN_ALLOWED", columnDefinition = "tinyint(1) default 0")
    private boolean childrenAllowed;

    @Column(name = "INFANTS_ALLOWED", columnDefinition = "tinyint(1) default 0")
    private boolean infantsAllowed;

    @Column(name = "INFANT_DESCRIPTION", nullable = true, length = 500)
    private String infantsDescription;
    @Column(name = "CHILDREN_DESCRIPTION", nullable = true, length = 500)
    private String childrenDescription;

    @Digits(integer = 10, fraction = 10)
    @Column(nullable = true, precision = 10, scale = 10, name = "LATITUDE")
    private Double latitude;

    @Digits(integer = 10, fraction = 10)
    @Column(nullable = true, precision = 10, scale = 10, name = "LONGITUDE")
    private Double longitude;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 12, scale = 2, name = "CHILD_PRICE")
    private BigDecimal childPrice;

    @Column(name = "MONDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean monday;

    @Column(name = "TUESDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean tuesday;

    @Column(name = "WEDNESDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean wednesday;

    @Column(name = "THURSDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean thursday;

    @Column(name = "FRIDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean friday;

    @Column(name = "SATURDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean saturday;

    @Column(name = "SUNDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean sunday;

    @Column(name = "ADVENTURE", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean adventure;

    @Column(name = "CITY_TOUR", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean cityTour;

    @Column(name = "GASTRONOMY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean gastronomy;

    @Column(name = "GOLF", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean golf;

    @Column(name = "DOLPHINSWIM", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean dolphinSwim;

    @Column(name = "OTHERACTIVITIES", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean otherActivities;

    @Column(name = "PARK", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean park;

    @Column(name = "BOAT", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean boat;

    @Column(name = "FISHING", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean fishing;

    @Column(name = "ANIMALTOUR", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean animalTour;

    @Column(name = "SCUBA", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean scuba;

    @Column(name = "SNORKEL", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean snorkel;

    @Column(name = "ATVS", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean atvs;

    @Column(name = "PACKAGE_TOUR", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean packageTour;

    @Column(name = "CANCUN", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean cancun;

    @Column(name = "COZUMEL", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean cozumel;

    @Column(name = "ISLAMUJERES", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean islaMujeres;

    @Column(name = "RIVIERAMAYA", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean rivieraMaya;

    @Column(name = "YUCATAN", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean yucatan;

    @Column(name = "MORNING", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean morning;

    @Column(name = "AFTERNOON", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean afternoon;

    @Column(name = "FULLDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean fullday;

    @Column(name = "HALFDAY", nullable = true, columnDefinition = "tinyint(1) default 0")
    private boolean halfday;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID", nullable = true)
    private TourServiceProvider provider;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10,
            scale = 2, name = "REP_COMMISSION",
            columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal repCommissionFullPrice;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10,
            scale = 2, name = "REP_COMMISSION_DISCOUNT",
            columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal repCommissionDiscount;

    @Digits(integer = 7, fraction = 2)
    @Column(nullable = true, precision = 10,
            scale = 2, name = "PUBLIC_DISCOUNT",
            columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal publicDiscount;

    //fechas de operacion
    private String inicialesConfirma;

    public Tour() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInicialesConfirma() {
        return inicialesConfirma;
    }

    public void setInicialesConfirma(String inicialesConfirma) {
        this.inicialesConfirma = inicialesConfirma;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public boolean isBuceo() {
        return buceo;
    }

    public void setBuceo(boolean buceo) {
        this.buceo = buceo;
    }

    public BigDecimal getImaAdultoUSD() {
        return imaAdultoUSD;
    }

    public void setImaAdultoUSD(BigDecimal imaAdultoUSD) {
        this.imaAdultoUSD = imaAdultoUSD;
    }

    public BigDecimal getImaNinoUSD() {
        return imaNinoUSD;
    }

    public void setImaNinoUSD(BigDecimal imaNinoUSD) {
        this.imaNinoUSD = imaNinoUSD;
    }

    public BigDecimal getImaAdultoMXN() {
        return imaAdultoMXN;
    }

    public void setImaAdultoMXN(BigDecimal imaAdultoMXN) {
        this.imaAdultoMXN = imaAdultoMXN;
    }

    public BigDecimal getImaNinoMXN() {
        return imaNinoMXN;
    }

    public void setImaNinoMXN(BigDecimal imaNinoMXN) {
        this.imaNinoMXN = imaNinoMXN;
    }

    public boolean isManejaVehiculo() {
        return manejaVehiculo;
    }

    public void setManejaVehiculo(boolean manejaVehiculo) {
        this.manejaVehiculo = manejaVehiculo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(" - ").append(this.getName());
        return sb.toString();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getOperationalCost() {
        return operationalCost;
    }

    public void setOperationalCost(BigDecimal operationalCost) {
        this.operationalCost = operationalCost;
    }

    public BigDecimal getOperationalCostChild() {
        return operationalCostChild;
    }

    public void setOperationalCostChild(BigDecimal operationalCostChild) {
        this.operationalCostChild = operationalCostChild;
    }

    public BigDecimal getOperationalCostInfant() {
        return operationalCostInfant;
    }

    public void setOperationalCostInfant(BigDecimal operationalCostInfant) {
        this.operationalCostInfant = operationalCostInfant;
    }

    public BigDecimal getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(BigDecimal minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public BigDecimal getMinSalePriceChild() {
        return minSalePriceChild;
    }

    public void setMinSalePriceChild(BigDecimal minSalePriceChild) {
        this.minSalePriceChild = minSalePriceChild;
    }

    public BigDecimal getMinSalePriceInfant() {
        return minSalePriceInfant;
    }

    public void setMinSalePriceInfant(BigDecimal minSalePriceInfant) {
        this.minSalePriceInfant = minSalePriceInfant;
    }

    public BigDecimal getMaxDiscountAllowed() {
        return maxDiscountAllowed;
    }

    public void setMaxDiscountAllowed(BigDecimal maxDiscountAllowed) {
        this.maxDiscountAllowed = maxDiscountAllowed;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    public String getSearchPhoto() {
        return searchPhoto;
    }

    public void setSearchPhoto(String searchPhoto) {
        this.searchPhoto = searchPhoto;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public int getTourScale() {
        return tourScale;
    }

    public void setTourScale(int tourScale) {
        this.tourScale = tourScale;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(BigDecimal infantPrice) {
        this.infantPrice = infantPrice;
    }

    public boolean isTopTour() {
        return topTour;
    }

    public void setTopTour(boolean topTour) {
        this.topTour = topTour;
    }

    public boolean isAquatic() {
        return aquatic;
    }

    public void setAquatic(boolean aquatic) {
        this.aquatic = aquatic;
    }

    public boolean isCultural() {
        return cultural;
    }

    public void setCultural(boolean cultural) {
        this.cultural = cultural;
    }

    public boolean isArcheological() {
        return archeological;
    }

    public void setArcheological(boolean archeological) {
        this.archeological = archeological;
    }

    public boolean isUnderground() {
        return underground;
    }

    public void setUnderground(boolean underground) {
        this.underground = underground;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public String getLinkvideo() {
        return linkvideo;
    }

    public void setLinkvideo(String linkvideo) {
        this.linkvideo = linkvideo;
    }

    public String getLinkprezi() {
        return linkprezi;
    }

    public void setLinkprezi(String linkprezi) {
        this.linkprezi = linkprezi;
    }

    public String getLinktripadvisor() {
        return linktripadvisor;
    }

    public void setLinktripadvisor(String linktripadvisor) {
        this.linktripadvisor = linktripadvisor;
    }

    public String getLinkgooglemaps() {
        return linkgooglemaps;
    }

    public void setLinkgooglemaps(String linkgooglemaps) {
        this.linkgooglemaps = linkgooglemaps;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDescriptionLang() {
        return descriptionLang;
    }

    public void setDescriptionLang(String descriptionLang) {
        this.descriptionLang = descriptionLang;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOptionalactivities() {
        return optionalactivities;
    }

    public void setOptionalactivities(String optionalactivities) {
        this.optionalactivities = optionalactivities;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public boolean isIsTransportationIncluded() {
        return isTransportationIncluded;
    }

    public void setIsTransportationIncluded(boolean isTransportationIncluded) {
        this.isTransportationIncluded = isTransportationIncluded;
    }

    public String getTransportationNotes() {
        return transportationNotes;
    }

    public void setTransportationNotes(String transportationNotes) {
        this.transportationNotes = transportationNotes;
    }

    public int getMinChildAge() {
        return minChildAge;
    }

    public void setMinChildAge(int minChildAge) {
        this.minChildAge = minChildAge;
    }

    public int getMaxChildAge() {
        return maxChildAge;
    }

    public void setMaxChildAge(int maxChildAge) {
        this.maxChildAge = maxChildAge;
    }

    public boolean isChildrenAllowed() {
        return childrenAllowed;
    }

    public void setChildrenAllowed(boolean childrenAllowed) {
        this.childrenAllowed = childrenAllowed;
    }

    public boolean isInfantsAllowed() {
        return infantsAllowed;
    }

    public void setInfantsAllowed(boolean infantsAllowed) {
        this.infantsAllowed = infantsAllowed;
    }

    public String getInfantsDescription() {
        return infantsDescription;
    }

    public void setInfantsDescription(String infantsDescription) {
        this.infantsDescription = infantsDescription;
    }

    public String getChildrenDescription() {
        return childrenDescription;
    }

    public void setChildrenDescription(String childrenDescription) {
        this.childrenDescription = childrenDescription;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public boolean isAdventure() {
        return adventure;
    }

    public void setAdventure(boolean adventure) {
        this.adventure = adventure;
    }

    public boolean isCityTour() {
        return cityTour;
    }

    public void setCityTour(boolean cityTour) {
        this.cityTour = cityTour;
    }

    public boolean isGastronomy() {
        return gastronomy;
    }

    public void setGastronomy(boolean gastronomy) {
        this.gastronomy = gastronomy;
    }

    public boolean isGolf() {
        return golf;
    }

    public void setGolf(boolean golf) {
        this.golf = golf;
    }

    public boolean isDolphinSwim() {
        return dolphinSwim;
    }

    public void setDolphinSwim(boolean dolphinSwim) {
        this.dolphinSwim = dolphinSwim;
    }

    public boolean isOtherActivities() {
        return otherActivities;
    }

    public void setOtherActivities(boolean otherActivities) {
        this.otherActivities = otherActivities;
    }

    public boolean isPark() {
        return park;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public boolean isBoat() {
        return boat;
    }

    public void setBoat(boolean boat) {
        this.boat = boat;
    }

    public boolean isFishing() {
        return fishing;
    }

    public void setFishing(boolean fishing) {
        this.fishing = fishing;
    }

    public boolean isAnimalTour() {
        return animalTour;
    }

    public void setAnimalTour(boolean animalTour) {
        this.animalTour = animalTour;
    }

    public boolean isScuba() {
        return scuba;
    }

    public void setScuba(boolean scuba) {
        this.scuba = scuba;
    }

    public boolean isSnorkel() {
        return snorkel;
    }

    public void setSnorkel(boolean snorkel) {
        this.snorkel = snorkel;
    }

    public boolean isAtvs() {
        return atvs;
    }

    public void setAtvs(boolean atvs) {
        this.atvs = atvs;
    }

    public boolean isPackageTour() {
        return packageTour;
    }

    public void setPackageTour(boolean packageTour) {
        this.packageTour = packageTour;
    }

    public boolean isCancun() {
        return cancun;
    }

    public void setCancun(boolean cancun) {
        this.cancun = cancun;
    }

    public boolean isCozumel() {
        return cozumel;
    }

    public void setCozumel(boolean cozumel) {
        this.cozumel = cozumel;
    }

    public boolean isIslaMujeres() {
        return islaMujeres;
    }

    public void setIslaMujeres(boolean islaMujeres) {
        this.islaMujeres = islaMujeres;
    }

    public boolean isRivieraMaya() {
        return rivieraMaya;
    }

    public void setRivieraMaya(boolean rivieraMaya) {
        this.rivieraMaya = rivieraMaya;
    }

    public boolean isYucatan() {
        return yucatan;
    }

    public void setYucatan(boolean yucatan) {
        this.yucatan = yucatan;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isFullday() {
        return fullday;
    }

    public void setFullday(boolean fullday) {
        this.fullday = fullday;
    }

    public boolean isHalfday() {
        return halfday;
    }

    public void setHalfday(boolean halfday) {
        this.halfday = halfday;
    }

    public TourServiceProvider getProvider() {
        return provider;
    }

    public void setProvider(TourServiceProvider provider) {
        this.provider = provider;
    }

    public BigDecimal getRepCommissionFullPrice() {
        return repCommissionFullPrice;
    }

    public void setRepCommissionFullPrice(BigDecimal repCommissionFullPrice) {
        this.repCommissionFullPrice = repCommissionFullPrice;
    }

    public BigDecimal getRepCommissionDiscount() {
        return repCommissionDiscount;
    }

    public void setRepCommissionDiscount(BigDecimal repCommissionDiscount) {
        this.repCommissionDiscount = repCommissionDiscount;
    }

    public BigDecimal getPublicDiscount() {
        return publicDiscount;
    }

    public void setPublicDiscount(BigDecimal publicDiscount) {
        this.publicDiscount = publicDiscount;
    }

}
