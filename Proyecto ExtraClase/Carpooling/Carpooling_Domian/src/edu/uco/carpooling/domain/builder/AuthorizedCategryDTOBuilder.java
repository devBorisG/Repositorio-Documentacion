import java.time.LocalDate;
import java.util.UUID;

import static edu.uco.carpooling.domain.AuthorizedCategoryDTO.create;

import edu.uco.carpooling.domain.AuthorizedCategoryDTO;

public class AuthorizedCategoryDTOBuilder {

    private UUID id;

    private String category;

    private LocalDate validity;

    private AuthorizedCategoryDTOBuilder(){super();}

    public static final AuthorizedCategoryDTOBuilder getAuthorizedCategoryDTOBuilder(){
        return new AuthorizedCategoryDTOBuilder();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public final AuthorizedCategoryDTO build(){
        return create(id,category,validity);
    }

}