package com.freight.fox.model;

import lombok.Data;
import java.util.List;

@Data
public class GeocodeResponse {
    private List<GeocodeResult> results;
}
