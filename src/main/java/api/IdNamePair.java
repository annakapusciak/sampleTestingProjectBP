package api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdNamePair {

    Integer id;
    String name;
}
