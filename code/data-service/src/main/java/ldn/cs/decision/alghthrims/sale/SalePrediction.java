package ldn.cs.decision.alghthrims.sale;

import ldn.cs.decision.alghthrims.Predictor;
import ldn.cs.decision.alghthrims.convey.ConveyPrediction;
import ldn.cs.decision.pojo.sale.Sale;

import java.math.BigDecimal;
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
        List<Double> quantityValues = new ArrayList<>();
        List<Double> incomeValues = new ArrayList<>();
        List<Double> costValues = new ArrayList<>();
        List<Double> inventoryValues = new ArrayList<>();
        List<Double> scoreValues = new ArrayList<>();

        for (Sale sale : historySale) {
            eventTimes.add(sale.getEventTime());
            quantityValues.add(sale.getQuantity().doubleValue());
            incomeValues.add(sale.getIncome().doubleValue());
            costValues.add(sale.getCost().doubleValue());
            inventoryValues.add(sale.getInventory().doubleValue());
            scoreValues.add((double) sale.getScore());
        }

        eventTimes.add(nowSale.getEventTime());
        quantityValues.add(nowSale.getQuantity().doubleValue());
        incomeValues.add(nowSale.getIncome().doubleValue());
        costValues.add(nowSale.getCost().doubleValue());
        inventoryValues.add(nowSale.getInventory().doubleValue());
        scoreValues.add((double) nowSale.getScore());

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
        predictedSale.setQuantity(BigDecimal.valueOf(quantityPredictor.predict(nextTime) > 0 ? quantityPredictor.predict(nextTime) : 0));
        predictedSale.setCost(BigDecimal.valueOf(costPredictor.predict(nextTime) > 0 ? costPredictor.predict(nextTime) : 0));
        predictedSale.setIncome(BigDecimal.valueOf(incomePredictor.predict(nextTime) > 0 ? incomePredictor.predict(nextTime) : 0));
        predictedSale.setProvince(nowSale.getProvince());
        predictedSale.setCountry(nowSale.getCountry());
        predictedSale.setInventory(BigDecimal.valueOf(inventoryPredictor.predict(nextTime)));
        predictedSale.setScore((int) (scorePredictor.predict(nextTime) > 0 ? scorePredictor.predict(nextTime) : 0));
        predictedSale.setEventTime(nextTime);
        predictedSale.setUpdateTime(System.currentTimeMillis() / 1000);

        return predictedSale;
    }
}

