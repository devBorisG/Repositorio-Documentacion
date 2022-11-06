package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.PointInterestDTO;
import edu.uco.carpooling.domain.RouteDTO;
import static edu.uco.carpooling.domain.PointInterestDTO.create;

public class PointInterestDTOBuilder {


    private UUID id;
    private String keyPoint;
    private RouteDTO routeCod;
    private String City;

    private PointInterestDTOBuilder(){
        super();
    }

    public static final PointInterestDTOBuilder getPointInterestDTOBuilder(){
        return new PointInterestDTOBuilder();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setKeyPoint(String keyPoint) {
        this.keyPoint = keyPoint;
    }

    public void setRouteCod(RouteDTO routeCod) {
        this.routeCod = routeCod;
    }

    public void setCity(String city) {
        City = city;
    }
    
    public final PointInterestDTO build() {
    	return create(id, keyPoint, routeCod, City);
    }
}
