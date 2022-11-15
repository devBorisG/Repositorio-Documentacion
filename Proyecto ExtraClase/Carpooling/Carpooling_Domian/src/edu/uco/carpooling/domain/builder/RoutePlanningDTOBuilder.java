package edu.uco.carpooling.domain.builder;

import java.util.ArrayList;
import java.util.UUID;

import static edu.uco.carpooling.domain.RoutePlanningDTO.create;
import edu.uco.carpooling.domain.RoutePlanningDTO;


public class RoutePlanningDTOBuilder {
	
	private UUID id;
	private ArrayList<String> detailPointOfInteresRoute;
	private String startPoint;
	private String endPoint;
	

	private RoutePlanningDTOBuilder() {
		super();
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public void setDetailPointOfInteresRoute(ArrayList<String> detailPointOfInteresRoute) {
		this.detailPointOfInteresRoute = detailPointOfInteresRoute;
	}



	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}



	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public final RoutePlanningDTO build() {
		return create(id,detailPointOfInteresRoute,startPoint,endPoint);
	}


	
	
	
	


}
