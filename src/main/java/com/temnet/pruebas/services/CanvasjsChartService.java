//CanvasjsChartService.java
package com.temnet.pruebas.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartService {

    List<List<Map<Object, Object>>> getCanvasjsChartData();

}
