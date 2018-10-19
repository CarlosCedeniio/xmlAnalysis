//CanvasjsChartController.java
package com.temnet.pruebas.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.temnet.pruebas.PruebasApplicationread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.temnet.pruebas.services.CanvasjsChartService;

@Controller
@RequestMapping("/canvasjschart")
public class CanvasjsChartController {

    @Autowired
    private CanvasjsChartService canvasjsChartService;

    @RequestMapping(method = RequestMethod.GET)
    public String springMVC(ModelMap modelMap) {
         Map<Object,Object> map = null;
         List<List<Map<Object,Object>>> list = new ArrayList<List<Map<Object,Object>>>();
         List<Map<Object,Object>> dataPoints1 = new ArrayList<Map<Object,Object>>();
         int c =0;

        for (String key:PruebasApplicationread.etapa1.keySet())
        {
            if (PruebasApplicationread.etapa1.get(key)!=103) {
                map = new HashMap<Object, Object>();
                map.put("label", PruebasApplicationread.keyset1.get(key));
                map.put("y", PruebasApplicationread.etapa1.get(key));
                dataPoints1.add(map);
                c++;

            }

        }
        //map = new HashMap<Object,Object>(); map.put("label", "Dhausdfsdfsdflsfddfagiri"); map.put("y", 8167);dataPoints1.add(map);
        //map = new HashMap<Object,Object>(); map.put("label", "Manssdfsdfsdfsdfdddga Parbat"); map.put("y", 8726);dataPoints1.add(map);
        //map = new HashMap<Object,Object>(); map.put("label", "Annasdfsdfsdfsdfseddfsdfpsdfurna"); map.put("y", 8051);dataPoints1.add(map);
        System.out.println("El numero de cambios fue: "+c);
        list.add(dataPoints1);
       // List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
        modelMap.addAttribute("dataPointsList", list);
        return "chart";
    }

}