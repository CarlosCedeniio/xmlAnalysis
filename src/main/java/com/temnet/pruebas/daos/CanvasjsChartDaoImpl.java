package com.temnet.pruebas.daos;

import java.util.List;
import java.util.Map;

import com.temnet.pruebas.data.CanvasjsChartData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return CanvasjsChartData.getCanvasjsDataList();
    }

}