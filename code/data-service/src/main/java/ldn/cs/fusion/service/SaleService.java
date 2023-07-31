package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.SaleDao;
import ldn.cs.fusion.pojo.production.Trend;
import ldn.cs.fusion.pojo.sale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleDao saleDao;

    public int addSaleBirthInfos(List<SaleBirth> saleBirths){
        return saleDao.addSaleBirthInfos(saleBirths);
    }

    public int addSaleCountInfos(List<SaleCount> saleCounts){
        return saleDao.addSaleCountInfos(saleCounts);
    }

    public int addExportInfos(List<Export> exports){
        return saleDao.addExportInfos(exports);
    }

    public int addProfitInfos(List<Profit> profits){
        return saleDao.addProfitInfos(profits);
    }

    public int addSaleTrendInfos(List<SaleTrend> saleTrends){
        return saleDao.addSaleTrendInfos(saleTrends);
    }

    public int addSaleInfos(List<Sale> sales){
        long updateTime = System.currentTimeMillis();
        sales.forEach(sale -> sale.setUpdateTime(updateTime));
        return saleDao.addSaleInfos(sales);
    }

    public SaleInfo getSaleInfos(String statement, int types, int limit, int offset) {
        SaleInfo saleInfo = new SaleInfo();
        List<Sale> sales = saleDao.getSaleInfos(statement, types, limit, offset);
        int total = saleDao.getTotalSale(statement, types);

        saleInfo.setSales(sales);
        saleInfo.setTotal(total);
        return saleInfo;
    }

    public Map<String, List<SaleTrend>> getSaleTrendInfos(int time) {
        return saleDao.getSaleTrendInfos(time).stream().collect(Collectors.groupingBy(SaleTrend::getCorporation));
    }

    public Map<String, List<SaleBirth>> getSaleBirthInfos(long time, int granularity) {
        return saleDao.getSaleBirthInfos(time, granularity).stream().collect(Collectors.groupingBy(SaleBirth::getCorporation));
    }

    public Map<String, List<SaleCount>> getSaleCountInfos(long time, int granularity) {
        return saleDao.getSaleCountInfos(time, granularity).stream().collect(Collectors.groupingBy(SaleCount::getCorporation));
    }

    public Map<String, List<Export>> getExportInfos(long time, int granularity) {
        return saleDao.getExportInfos(time, granularity).stream().collect(Collectors.groupingBy(Export::getCorporation));
    }
    public Map<String, List<Profit>> getProfitInfos(long time, int granularity) {
        return saleDao.getProfitInfos(time, granularity).stream().collect(Collectors.groupingBy(Profit::getCorporation));
    }
}
