package edu.uco.carpooling.domain;

import java.util.ArrayList;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;




public class RoutePlanningDTO {

	
	private UUID id;
	private ArrayList<String> detailPointOfInteresRoute;
	private String startPoint;
	private String endPoint;
	
	
	
	public RoutePlanningDTO() {
		setId(getNewUUID());
		setDetailPointOfInteresRoute(new ArrayList<>());
		setStartPoint(EMPTY);
		setEndPoint(EMPTY);
	}
	
	
	public RoutePlanningDTO(UUID id, ArrayList<String> detailPointOfInteresRoute, String startPoint, String endPoint) {
		setId(id);
		setDetailPointOfInteresRoute(detailPointOfInteresRoute);
		setStartPoint(startPoint);
		setEndPoint(endPoint);
	}
	
	public static final RoutePlanningDTO create (final UUID id,
			final ArrayList<String> detailPointOfIntersRooute,
			final String startPoint, final String endPoint) {
		return new RoutePlanningDTO(id, detailPointOfIntersRooute,startPoint,endPoint);
	}

	public static final RoutePlanningDTO create (final String id,
			final ArrayList<String> detailPointOfIntersRooute,
			final String startPoint, final String endPoint) {
		return new RoutePlanningDTO(getUUIDFromString(id), detailPointOfIntersRooute,startPoint,endPoint);
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public ArrayList<String> getDetailPointOfInteresRoute() {
		return detailPointOfInteresRoute;
	}


	public void setDetailPointOfInteresRoute(ArrayList<String> detailPointOfInteresRoute) {
		this.detailPointOfInteresRoute = detailPointOfInteresRoute;
	}


	public String getStartPoint() {
		return startPoint;
	}


	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}


	public String getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}


	public static RoutePlanningDTO create(String string, String string2, String string3, String string4) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
