package edu.uco.carpooling.domain;

import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.domain.builder.DriverDTOBuilder.getDriverDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class QualificationDTO {
	
    private UUID id;
    private double score;
    private String comment;
    private DriverDTO driver;

    public QualificationDTO(final UUID id,final double score,final String comment,final DriverDTO driver) {
        setId(getNewUUID());
        setScore(ZERO);
        setComment(EMPTY);
        setUser(getDriverDTOBuilder().build());
    }

    public static final QualificationDTO create(final UUID id,final double score,final String comment, final DriverDTO driver){
        return new QualificationDTO(id,score,comment,driver);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = isLessThan(score , ZERO)?ZERO : score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = applyTrim(comment);
    }

    public DriverDTO driver() {
        return driver;
    }

    public void setUser(DriverDTO driver) {
        this.driver = getDefaultIfNull(driver, getDriverDTOBuilder().build());
    }
}
