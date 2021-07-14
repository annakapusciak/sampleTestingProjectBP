package api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pet {

    Integer id;
    String name;
    IdNamePair category;
    List<IdNamePair> tags;
    List<String> photoUrls;
    String status;

    enum Status {
        AVAILABLE("available"),
        PENDING("pending"),
        SOLD("sold");

        final String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
