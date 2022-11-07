package edu.uco.carpooling.domain;

import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class PointInterestDTO {

    private UUID id;
    private String keyPoint;
    private String city;

    public PointInterestDTO(){
        setId(getNewUUID());
        setKeyPoint(EMPTY);
        setCity(EMPTY);
    }

    public PointInterestDTO(UUID id, String keyPoint, String city) {
        setId(id);
        setKeyPoint(keyPoint);
        setCity(city);

    }

    public static final PointInterestDTO create(final UUID id, final String keyPoint, final String city){
        return new PointInterestDTO(id, keyPoint,city);
    }
    
    public static final PointInterestDTO create(final String id, final String keyPoint, final String city){
        return new PointInterestDTO(getUUIDFromString(id), keyPoint,city);
    }
    
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}

    public UUID getId() {return id;}

    public void setId(final UUID id) {this.id = getDefaultUUID(id);}
    
    public String getKeyPoint() {
    	return keyPoint;
    }

    public void setKeyPoint(final String keyPoint) {
    	this.keyPoint = applyTrim(keyPoint);
    }

    public String getCity() {return city;}

    public void setCity(final String city) {
         this.city = applyTrim(city);
    }
    
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}
