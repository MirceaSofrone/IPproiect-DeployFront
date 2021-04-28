package com.fii.houses.fii.houses.demo.service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fii.houses.fii.houses.demo.models.House;
import com.fii.houses.fii.houses.demo.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

class SortByDate implements Comparator<House> {

    @Override
    public int compare(House house1, House house2) {
        return house1.getCreationDate().compareTo(house2.getCreationDate());
    }
}

@Service
public class HouseService {
    @Autowired
    private HouseRepository repository;
    private final static Integer carouselSize = 9;

    public List<House> getAllHouses() {
        List<House> allHouses = repository.findAll();
        if (allHouses.size() > 0) {
            return allHouses;
        } else {
            return new ArrayList<>();
        }
    }

    public House getHouseByHouseID(House house){
        UUID id = house.getHouseID();
        List<House> allHouses = repository.findAll();
        for (House existingHouse : allHouses) {
            if (existingHouse.getHouseID().equals(id)) {
                return existingHouse;
            }
        }
        return null;
    }

    public List<House> getHouseByAddress(House house){
        List<House> housesByAddress = new ArrayList<>();
        List<House> allHouses = repository.findAll();
        String address = house.getAddress();
        for (House existingHouse : allHouses) {
            if (existingHouse.getAddress().equals(address)) {
                housesByAddress.add(existingHouse);
            }
        }
        if(housesByAddress.size()>0){
            return housesByAddress;
        }else {
            return new ArrayList<>();
        }
    }

    public List<House> getHouseByUserID(House house){
        UUID id = house.getUserID();
        List<House> allHouses = repository.findAll();
        List<House> housesByUserId = new ArrayList<>();
        for (House existingHouse : allHouses) {
            if (existingHouse.getUserID().equals(id)) {
                housesByUserId.add(existingHouse);
            }
        }
        if(housesByUserId.size()>0){
            return housesByUserId;
        }else {
            return new ArrayList<>();
        }
    }

    public String getHouseViews(House house){
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            House houseViews = repository.getOne(id);
            return houseViews.getViews().toString();
        }
        return null;
    }

    public boolean updateViews(UUID houseId){
        if(repository.existsById(houseId)){
            House house = repository.getOne(houseId);
            int views = house.getViews();
            house.setViews(views+1);
            repository.save(house);
            return true;
        }
        return false;
    }

    public List<House> lastAddedHouses(){
        List<House> allHouses = repository.findAll();
        allHouses.sort(new SortByDate());
        List<House> lastAddedHouses = new ArrayList<>();
        int noOfHouses = Math.min(allHouses.size(), carouselSize);
        for (int index = 0; index < noOfHouses; index++) {
            lastAddedHouses.add(allHouses.get(index));
        }
        if (lastAddedHouses.size() > 0) {
            return lastAddedHouses;
        } else {
            return new ArrayList<>();
        }
    }

    public String getArea(String address){
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("e02d3849718d47ac86668c2149a7b8f9");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        request.setRestrictToCountryCode("ro"); // restrict results to a specific country
        // request.setBounds(18.367, -34.109, 18.770, -33.704); // restrict results to a geographic bounding box (southWestLng, southWestLat, northEastLng, northEastLat)

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition(); // get the coordinate pair of the first result
        System.out.println(firstResultLatLng.getLat());
        System.out.println(firstResultLatLng.getLng());
        Double lat=firstResultLatLng.getLat();
        Double lon=firstResultLatLng.getLng();
        String location = null;
        if(lat >47.154394 && lat<47.160288 && lon >=27.590935 && lon<=27.612028){
            location="tudor-vladimirescu";
        }
        else if(lat >=47.160339 && lat<=47.170693 && lon >=27.594636 && lon<=27.618331){
            location="tatarasi-nord";
        }else if(lat >=47.170423 && lat<=47.160385 && lon >=27.608396 && lon<=27.619382){
            location="tatarasi-sud";
        }else if(lat >=47.155106 && lat<=47.168791 && lon >=27.579667 && lon<=27.595331){
            location="centru-civic";
        }else if(lat >=47.106165 && lat<=47.136067 && lon >=27.610885&& lon<=27.638179){
            location="bucium";
        }else if(lat >=47.168400 && lat<=47.193057 && lon >=27.552273&& lon<=27.584447){
            location="copou";
        }else if(lat >=47.146940 && lat<=47.161123 && lon >=27.558424&& lon<=27.574603){
            location="galata";
        }else if(lat >=47.148523 && lat<=47.1651123 && lon >=27.681363&& lon<=27.700932){
            location="holboca";
        }else if(lat >=47.164694 && lat<=47.179631 && lon >=27.585385 && lon<=27.605298){
            location="moara-de-vant";
        }else if(lat >=47.169297 && lat<=47.180120 && lon >=27.528106 && lon<=27.571537){
            location="pacurari";
        }else if(lat >=47.155310 && lat<=47.168558 && lon >=27.579752 && lon<=27.595717){
            location="centru";
        }else if(lat >=47.115690 && lat<=47.138232 && lon >=27.677725 && lon<=27.745532){
            location="tomesti";
        }else if(lat >=47.134638 && lat<=47.149641 && lon >=27.570064 && lon<=27.579592){
            location="nicolina 1";
        }else if(lat >=47.133946 && lat<=47.146031 && lon >=27.579798 && lon<=27.596149){
            location="nicolina 2";
        }else if(lat >=47.105328 && lat<=47.135582 && lon >=27.563592 && lon<=27.578870){
            location="cug";
        }else if(lat >=47.16504 && lat<=47.16504 && lon >=27.58035 && lon<=27.58035){
            location="periferie";
        }else if(lat >=47.17463 && lat<=47.17463 && lon >=27.58194  && lon<=27.58194){
            location="sararie";
        }else if(lat >=47.173043 && lat<=47.188615 && lon >=27.492532  && lon<=27.529605){
            location="valea-lupului";
        }else if(lat >=47.17305 && lat<=47.17305 && lon >=27.51921  && lon<=27.51921){
            location="popas-pacurari";
        }else if(lat >=47.129582 && lat<=47.140354 && lon >= 27.579042 && lon<=27.591895){
            location="frumoasa";
        }else if(lat >=47.15262 && lat<=47.15262 && lon >=27.61873 && lon<=27.61873){
            location="centrala";
        }else if(lat >=47.150256 && lat<=47.163476 && lon >=27.624224 && lon<=27.640446){
            location="aviatiei";
        }else if(lat >=47.162448 && lat<=47.170690 && lon >=27.561122 && lon<=27.573696){
            location="gara";
        }else if(lat >=47.112208 && lat<=47.135977 && lon >=27.539742 && lon<=27.560255){
            location="valea-adanca";
        }else if(lat >=47.189951 && lat<=47.202612 && lon >=27.540691 && lon<=27.556785) {
            location = "agronomie";
        }else if(lat >=47.16728 && lat<=47.16728 && lon >=27.59487 && lon<=27.59487) {
            location = "podul-de-fier";
        }else if(lat >=47.193012 && lat<=47.269013 && lon >=27.411705 && lon<=27.506977) {
            location = "rediu";
        }else if(lat >=47.17325 && lat<=47.17325 && lon >=27.51244 && lon<=27.51244) {
            location = "soseaua-pacurari";
        }else if(lat >=47.25483 && lat<=47.25483 && lon >=27.29771 && lon<=27.29771) {
            location = "strada-stefan-cel-mare";
        }else if(lat >=47.16433 && lat<=47.16433 && lon >=27.59021 && lon<=27.59021) {
            location = "targu-cucu";
        }else if(lat >=47.128374 && lat<=47.138957 && lon >=27.591916 && lon<=27.606958) {
            location = "manta-rosie";
        }else if(lat >=47.144348 && lat<=47.153527 && lon >=27.589182 && lon<=27.602764) {
            location = "tesatura";
        }else if(lat >=47.087480 && lat<=47.105331 && lon >=27.544828 && lon<=27.576327) {
            location = "lunca-cetatuii";
        }else if(lat >=47.15753 && lat<=47.15753 && lon >=27.61071 && lon<=27.61071) {
            location = "oancea";
        }else if(lat >=47.069697 && lat<=47.224274 && lon >=27.440860 && lon<=27.553753) {
            location = "miroslava";
        }else if(lat >=47.153784 && lat<=47.167207 && lon >=27.573210 && lon<=27.583682) {
            location = "uzinei";
        }else if(lat >=47.146191 && lat<=47.156245 && lon >=27.578194 && lon<=27.590961) {
            location = "podu-ros";
        }else if(lat >=47.104462 && lat<=47.126797 && lon >=27.59466 && lon<=27.612086) {
            location = "visan";
        }else if(lat >=47.206086 && lat<=47.235991 && lon >=27.490122 && lon<=27.524883) {
            location = "breazu";
        }else if(lat >=47.088167 && lat<=47.108412 && lon >=27.657437 && lon<=27.675204) {
            location = "paun";
        }else if(lat >=47.171737 && lat<=47.174333 && lon >=27.530942 && lon<=27.561787) {
            location = "canta";
        }else if(lat >=47.06696 && lat<=47.06696 && lon >=27.64987 && lon<=27.64987) {
            location = "pietrarie";
        }else if(lat >=47.157985 && lat<=47.170473 && lon >=27.557072 && lon<=27.572972) {
            location = "alexandru-cel-bun";
        }else if(lat >=47.210814 && lat<=47.236492 && lon >=27.588096 && lon<=27.602358) {
            location = "dorobant";
        }else if(lat >=47.101592 && lat<=47.113451 && lon >=27.534721 && lon<=27.560899) {
            location = "horpaz";
        }else if(lat >=47.17279 && lat<=47.17279 && lon >=27.55629 && lon<=27.55629) {
            location = "moara-de-foc";
        }else if(lat >=47.16673 && lat<=47.16673 && lon >=27.57713 && lon<=27.57713) {
            location = "strada-arcu";
        }else if(lat >=47.17002 && lat<=47.17002 && lon >=27.56787 && lon<=27.56787) {
            location = "arcu";
        }else if(lat >=47.172568 && lat<=47.188465 && lon >=27.576123 && lon<=27.589813) {
            location = "ticau";
        }else if(lat >=47.153527 && lat<=47.157964 && lon >=27.564628 && lon<=27.573626) {
            location = "mircea-cel-batran";
        }else if(lat >=47.134988 && lat<=47.150576 && lon >=27.610674 && lon<=27.657795) {
            location = "zona-industriala";
        }else if(lat >=47.162449 && lat<=47.171888 && lon >=27.530963 && lon<=27.557571) {
            location = "dacia";
        }else if(lat >=47.16729 && lat<=47.16729 && lon >=27.55699 && lon<=27.55699) {
            location = "zimbru";
        }else if(lat >=47.150631 && lat<=47.160204 && lon >=27.653174 && lon<=27.676944) {
            location = "dancu";
        }else if(lat >=47.200444 && lat<=47.212412 && lon >=27.577196 && lon<=27.589191) {
            location = "sorogari";
        }else if(lat >=47.135868 && lat<=47.146091 && lon >=27.596091 && lon<=27.610975) {
            location = "bularga";
        }else if(lat >=47.16740 && lat<=47.16740 && lon >=27.58334 && lon<=27.58334) {
            location = "independentei";
        }else if(lat >=47.15078 && lat<=47.15078 && lon >=27.59637 && lon<=27.59637) {
            location = "primaverii";
        }else if(lat >=47.15779 && lat<=47.15779 && lon >=27.57523 && lon<=27.57523) {
            location = "podul-de-piatra";
        }else if(lat >=47.11785 && lat<=47.11785 && lon >=27.54003 && lon<=27.54003) {
            location = "ezareni";
        }else if(lat >=47.197183 && lat<=47.202733 && lon >=27.533519 && lon<=27.541974) {
            location = "carol-1";
        }else if(lat >=47.14842 && lat<=47.14842 && lon >=27.62035 && lon<=27.62035) {
            location = "metalurgie";
        }else if(lat >=47.18190 && lat<=47.18190 && lon >=27.57648 && lon<=27.57648) {
            location = "aroneanu";
        }else if(lat >=47.108441 && lat<=47.113715 && lon >=27.579770 && lon<=27.584633) {
            location = "hlincea";
        }else if(lat >=47.15196 && lat<=47.15196 && lon >=27.58109 && lon<=27.58109) {
            location = "dimitrie-cantemir";
        }else if(lat >=47.16026 && lat<=47.16026 && lon >=27.59981 && lon<=27.59981) {
            location = "bucsinescu";
        }else if(lat >=47.111369 && lat<=47.136552 && lon >=27.539441 && lon<=27.562857) {
            location = "valea-adanca";
        }else if(lat >=47.177681 && lat<=47.193023 && lon >=27.644600 && lon<=27.652711) {
            location = "valea-lunga";
        }else if(lat >=47.13903 && lat<=47.13903 && lon >=27.60632 && lon<=27.60632) {
            location = "socola";
        }else if(lat >=47.143361 && lat<=47.147783 && lon >=27.600124  && lon<=27.614007) {
            location = "baza-3";
        }else if(lat >=47.089434 && lat<=47.100530 && lon >=27.520583  && lon<=27.540232) {
            location = "ciurbesti";
        }else if(lat >=47.16452 && lat<=47.16452 && lon >=27.61249  && lon<=27.61249) {
            location = "ciric";
        }else if(lat >=47.15807 && lat<=47.15807 && lon >=27.59643  && lon<=27.59643) {
            location = "smardan";
        }
        return location;
    }

    public House createHouse(House house) {
        house.setHouseID(UUID.randomUUID());
        house.setCreationDate(new Date());
        house.setViews(0);
        if(house.getAddress() != null){
            house.setArea(getArea(house.getAddress()));
        }
        house=repository.save(house);
        return house;
    }

    public House updateHouse(House house) {
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            House updateHouse = repository.getOne(id);
            house.setCreationDate(new Date());
            if(house.getAddress()!=null){
                updateHouse.setAddress(house.getAddress());
                updateHouse.setArea(getArea(updateHouse.getAddress()));
            }
            if(house.getCity()!=null)
                updateHouse.setCity(house.getCity());
            if(house.getCountry()!=null)
                updateHouse.setCountry(house.getCountry());
            if(house.getFloor()!=null)
                updateHouse.setFloor(house.getFloor());
            if(house.getNoOfBathrooms()!=null)
                updateHouse.setNoOfBathrooms(house.getNoOfBathrooms());
            if(house.getNoOfRooms()!=null)
                updateHouse.setNoOfRooms(house.getNoOfRooms());
            if(house.getSurface()!=null)
                updateHouse.setSurface(house.getSurface());
            if(house.getHouseType()!=null){
                updateHouse.setHouseType(house.getHouseType());
            }
            if(house.getSellType()!=null){
                updateHouse.setSellType(house.getSellType());
            }
            if(house.getDescription()!=null) {
                updateHouse.setDescription(house.getDescription());
            }
            if(house.getCurrentPrice()!=null) {
                updateHouse.setCurrentPrice(house.getCurrentPrice());
            }
            house=repository.save(updateHouse);
            return house;
        }else
            return null;
    }

    public boolean deleteHouse(House house) {
        UUID id = house.getHouseID();
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public List<House> searchByWords(String words){
        List<House> allHouses = this.getAllHouses();
        List<House> housesTemp;
        Map<Integer, List<House>> wordsMatched = new TreeMap<>();
        String[] arrOfWords = words.split("-", 0);
        Integer counterMatches;
        //populate the map wordsMarched: for a number of matches we have a list of houses
        for(House house : allHouses){
            counterMatches = 0;
            for(String word : arrOfWords){
                if(house.getDescription() != null){
                    if(house.getDescription().contains(word)){
                        counterMatches++;
                    }
                }
                if(house.getAddress() != null){
                    if(house.getAddress().contains(word)){
                        counterMatches++;
                    }
                }
            }
            if(counterMatches != 0){
                if(wordsMatched.containsKey(counterMatches)){
                    housesTemp = wordsMatched.get(counterMatches);
                }else{
                    housesTemp = new ArrayList<>();
                }
                housesTemp.add(house);
                wordsMatched.put(counterMatches, housesTemp);
            }
        }
        //sort descending by number of matches
        Map<Integer, List<House>> reverseSortedMap = new TreeMap<>(Collections.reverseOrder());

        reverseSortedMap.putAll(wordsMatched);

        List<House> houses = new ArrayList<>();
        for (Map.Entry<Integer, List<House>> entry :reverseSortedMap.entrySet()) {
            housesTemp = entry.getValue();
            houses.addAll(housesTemp);
        }
        return houses;
    }

    public List<House> searchByFields(Integer houseType,Integer sellType, String city,String country,
                                      Integer noOfRooms,Integer floor, Integer surface, Integer noOfBathrooms){

        List<House> allHouses = this.getAllHouses();
        List<House> filteredHouses = new ArrayList<>();

        for(House house : allHouses){
            if((houseType == null || house.getHouseType().equals(houseType)) &&
                    (sellType == null || house.getSellType().equals(sellType)) &&
                    (city == null || house.getCity().equals(city)) &&
                    (country == null || house.getCountry().equals(country)) &&
                    (noOfRooms == null || house.getNoOfRooms().equals(noOfRooms)) &&
                    (floor == null || house.getFloor().equals(floor)) &&
                    (surface == null || house.getSurface().equals(surface)) &&
                    (noOfBathrooms == null || house.getNoOfBathrooms().equals(noOfBathrooms))) {
                filteredHouses.add(house);
            }
        }
        return filteredHouses;
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}


