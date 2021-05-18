package com.fii.houses.fii.houses.demo.models;


import javax.persistence.*;
import java.util.*;

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "BINARY(16)")
    private UUID houseID;
//    @Column(columnDefinition = "BINARY(16)")
    private long userID;
    @Column(columnDefinition = "text")
    private String description;
    private String title;
    private String city;
    private String country;
    private String address;
    private String area;
    private Double latitude;
    private Double longitude;
    private Integer constructionYear;
    private Integer noOfRooms;
    private Integer floor;
    private Integer surface;
    private Integer landSurface;
    private Integer noOfBathrooms;
    //0-house, 1-apartment
    private Integer houseType;
    //0-selling, 1-renting
    private Integer sellType;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<Date, Double> priceHistory = new TreeMap<>();
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<Date, Integer> favoriteHistory = new TreeMap<>();
    private Integer noOfFave = 0;
    private Double recommendedPrice;
    private Double currentPrice;
    private Date creationDate;
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<Date, Integer> viewsHistory = new TreeMap<>();
    private Integer views;
    @ElementCollection
    private List<byte[]> pictures = new ArrayList<>();

    public UUID getHouseID() {
        return houseID;
    }

    public void setHouseID(UUID houseID) {
        this.houseID = houseID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Integer getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(Integer noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }

    public Integer getSellType() {
        return sellType;
    }

    public void setSellType(int sellType) {
        this.sellType = sellType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Date, Double> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(Map<Date, Double> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public Map<Date, Integer> getFavoriteHistory() {
        return favoriteHistory;
    }

    public void setFavoriteHistory(Map<Date, Integer> favoriteHistory) {
        this.favoriteHistory = favoriteHistory;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getNoOfFave() {
        return noOfFave;
    }

    public void setNoOfFave(Integer noOfFave) {
        this.noOfFave = noOfFave;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        priceHistory.put(new Date(), currentPrice);
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }

    public Map<Date, Integer> getViewsHistory() {
        return viewsHistory;
    }

    public void setViewsHistory(Map<Date, Integer> viewsHistory) {
        this.viewsHistory = viewsHistory;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getRecommendedPrice() {
        return recommendedPrice;
    }

    public void setRecommendedPrice(Double recommendedPrice) {
        this.recommendedPrice = recommendedPrice;
    }

    public Integer getLandSurface() {
        return landSurface;
    }

    public void setLandSurface(Integer landSurface) {
        this.landSurface = landSurface;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public List<byte[]> getPhotos() {
        return pictures;
    }

    public void setPhotos(List<byte[]> photos) {
        this.pictures = photos;
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
}
