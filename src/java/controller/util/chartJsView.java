/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import controller.MachineController;
import entities.Machine;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import javax.inject.Inject;

@Named
@RequestScoped
public class chartJsView implements Serializable {

    private BarChartModel barModel;

    @Inject
    private MachineController mc;
    private List<Machine> machinesList;

    @PostConstruct
    public void init() {

        createBarModels();

    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries machines = new ChartSeries();

        Map<Integer, Integer> machinesCountPerYear = new HashMap<>();

        machines.setLabel("Machines");

        // Count the machines for each year
        for (Machine machine : mc.getItems()) {
            int year = machine.getDateAchat().getYear() + 1900;
            machinesCountPerYear.put(year, machinesCountPerYear.getOrDefault(year, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : machinesCountPerYear.entrySet()) {
            machines.set(entry.getKey(), entry.getValue());
        }
        model.addSeries(machines);

        return model;
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Number of Machines per year");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Number of Machine");
        yAxis.setMin(0);
    }
}
