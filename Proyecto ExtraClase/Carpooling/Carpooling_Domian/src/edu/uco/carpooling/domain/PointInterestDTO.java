package edu.uco.carpooling.domain;

import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class PointInterestDTO {

    private UUID id;
    private String keyPoint;
    private RouteDTO routeCod;
    private String city;

    public PointInterestDTO(){
        setId(getNewUUID());
        setKeyPoint(EMPTY);
        setRouteCod(routeCod);
        setCity(EMPTY);
    }

    public PointInterestDTO(UUID id, String keyPoint, RouteDTO routeCod, String city) {
        setId(id);
        setKeyPoint(keyPoint);
        setRouteCod(routeCod);
        setCity(city);

    }

    public static final PointInterestDTO create(final UUID id, final String keyPoint,final RouteDTO routeCod, final String city){
        return new PointInterestDTO(id, keyPoint,routeCod,city);
    }

    public UUID getId() {return id;}

    public void setId(final UUID id) {this.id = getDefaultUUID(id);}

    public String getKeyPoint() {return keyPoint;}

    public void setKeyPoint(final String keyPoint) {this.keyPoint = applyTrim(keyPoint);}

    public RouteDTO getRouteCod() {
        return routeCod;
    }

    public void setRouteCod(final RouteDTO routeCod) {
        this.routeCod = getDefaultIfNull(routeCod, routeCod);
    }

    public String getCity() {return city;}

    public void setCity(final String city) {
         this.city = applyTrim(city);
    }
}
