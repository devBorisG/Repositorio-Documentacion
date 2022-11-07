package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.DEFAULT_DATE;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;

public class AuthorizedCategoryDTO {

    private UUID id;
    private String category;
    private LocalDate validity;

    public AuthorizedCategoryDTO(){
        setId(getNewUUID());
        setCategory(EMPTY);
        setValidity(DEFAULT_DATE);
    }

    public AuthorizedCategoryDTO(final UUID id,final String category,final LocalDate validity) {
        setId(id);
        setCategory(category);
        setValidity(validity);
    }
    public static final AuthorizedCategoryDTO create(final UUID id,final String category,final LocalDate validity) {
        return new AuthorizedCategoryDTO(id,category,validity);
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

    public final void setValidity(final LocalDate validity) {
        this.validity = getDefaultDate(validity);
    }

    public final LocalDate getValidity() {
        return validity;
    }
}