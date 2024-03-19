package project.sgs.Handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.sgs.Exeption.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private int httpCode;
    private ErrorCode Code;
    private String message;
    private List<String> errors=new ArrayList<>();
}
