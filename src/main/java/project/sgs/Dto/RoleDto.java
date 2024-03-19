package project.sgs.Dto;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RoleDto {
    private Long id;
    private String Rolenome;
    private List<UtlisateureDto> utlisateureDtos;
}
