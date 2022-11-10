package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.domain.QualificationDTO;

public class QualificationDTOBuilder {

    private UUID id;
    private double score;
    private String comment;
    private DriverDTO driver;
    
    private QualificationDTOBuilder(){
    	super();
    	}

    public static final QualificationDTOBuilder getQualificationDTOBuilder() {
        return new QualificationDTOBuilder();
    }

    public final QualificationDTOBuilder setId(UUID id) {
        this.id = id;
        return this;

    }

    public final QualificationDTOBuilder setScore(double score) {
        this.score = score;
        return this;

    }

    public final QualificationDTOBuilder setComment(String comment) {
        this.comment = comment;
        return this;

    }

    public final QualificationDTOBuilder setUser(DriverDTO driver) {
        this.driver = driver;
        return this;
    }

    public final QualificationDTO build(){
        return QualificationDTO.create(id,score,comment,driver);
    }
}
