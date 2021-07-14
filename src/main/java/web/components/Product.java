package web.components;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    String name;
    String price;
    String colour;

}
