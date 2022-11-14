package edu.uco.carpooling.domain;

import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;

public class AuthorizedCategoryDTO {

    private UUID id;
    private String category;
    private String validity;

    public AuthorizedCategoryDTO(){
        setId(getNewUUID());
        setCategory(EMPTY);
        setValidity(EMPTY);
    }

    public AuthorizedCategoryDTO(final UUID id,final String category,final String validity) {
        setId(id);
        setCategory(category);
        setValidity(validity);
    }
    
    public static final AuthorizedCategoryDTO create(final UUID string,final String category,final String validity) {
        return new AuthorizedCategoryDTO(string,category,validity);
    }
    
    public static final AuthorizedCategoryDTO create(final String id,final String category,final String validity) {
        return new AuthorizedCategoryDTO(getUUIDFromString(id),category,validity);
    }
    
    public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public static final String getIntAsString(final int value) {
		return Integer.toString(value);
	}

    public final void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    public final UUID getId() {
        return id;
    }

    public final void setCategory(final String category) {
        this.category = applyTrim(category);
    }

    public final String getCategory() {
        return category;
    }

    public final void setValidity(final String validity) {
        this.validity = applyTrim(validity);
    }

    public final String getValidity() {
        return validity;
    }
    
    public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}