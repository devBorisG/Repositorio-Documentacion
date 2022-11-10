package edu.uco.carpooling.domain;

import java.sql.Date;
import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.DEFAULT_DATE;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;

public class AuthorizedCategoryDTO {

    private UUID id;
    private String category;
    private Date validity;

    public AuthorizedCategoryDTO(){
        setId(getNewUUID());
        setCategory(EMPTY);
        setValidity(DEFAULT_DATE);
    }

    public AuthorizedCategoryDTO(final UUID id,final String category,final Date validity) {
        setId(id);
        setCategory(category);
        setValidity(validity);
    }
    public static final AuthorizedCategoryDTO create(final UUID string,final String category,final Date date) {
        return new AuthorizedCategoryDTO(string,category,date);
    }
    
    public static final AuthorizedCategoryDTO create(final String id,final String category,final Date validity) {
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

    public final void setValidity(final Date validity) {
        this.validity = getDefaultDate(validity);
    }

    public final Date getValidity() {
        return validity;
    }
    
    public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
}