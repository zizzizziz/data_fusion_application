package ldn.cs.decision.alghthrims.sale;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.alghthrims.convey.ConveyPrediction;
import ldn.cs.decision.pojo.sale.Sale;

import java.util.*;

public class SalePrediction {
    private static final SalePrediction instance = new SalePrediction();

    private SalePrediction() {
    }

    public static SalePrediction getInstance() {
        return instance;
    }

    public Sale getNextSale(List<Sale> historySale, Sale nowSale, long nextTime) {
        List<Long> eventTimes = new ArrayList<>();
        List<Long> quantityValues = new ArrayList<>();
        List<Long> incomeValues = new ArrayList<>();
        List<Long> costValues = new ArrayList<>();
        List<Long> inventoryValues = new ArrayList<>();
        List<Long> scoreValues = new ArrayList<>();

        for (Sale sale : historySale) {
            eventTimes.add(sale.getEventTime());
            quantityValues.add(sale.getQuantity());
            incomeValues.add(sale.getIncome());
            costValues.add(sale.getCost());
            inventoryValues.add(sale.getInventory());
            scoreValues.add((long) sale.getScore());
        }

        eventTimes.add(nowSale.getEventTime());
        quantityValues.add(nowSale.getQuantity());
        incomeValues.add(nowSale.getIncome());
        costValues.add(nowSale.getCost());
        inventoryValues.add(nowSale.getInventory());
        scoreValues.add((long) nowSale.getScore());

        Predictor quantityPredictor = new Predictor();
        quantityPredictor.fit(eventTimes, quantityValues);

        Predictor incomePredictor = new Predictor();
        incomePredictor.fit(eventTimes, incomeValues);

        Predictor costPredictor = new Predictor();
        costPredictor.fit(eventTimes, costValues);

        Predictor inventoryPredictor = new Predictor();
        inventoryPredictor.fit(eventTimes, inventoryValues);

        Predictor scorePredictor = new Predictor();
        scorePredictor.fit(eventTimes, scoreValues);

        Sale predictedSale = new Sale();
        predictedSale.setCorporation(nowSale.getCorporation());
        predictedSale.setCategories(nowSale.getCategories());
        predictedSale.setTypes(nowSale.getTypes());
        predictedSale.setQuantity(quantityPredictor.predict(nextTime));
        predictedSale.setIncome(incomePredictor.predict(nextTime));
        predictedSale.setProvince(nowSale.getProvince());
        predictedSale.setCountry(nowSale.getCountry());
        predictedSale.setInventory(inventoryPredictor.predict(nextTime));
        predictedSale.setScore((int) scorePredictor.predict(nextTime));
        predictedSale.setEventTime(nextTime);
        predictedSale.setUpdateTime(System.currentTimeMillis() / 1000);

        return predictedSale;
    }
}

