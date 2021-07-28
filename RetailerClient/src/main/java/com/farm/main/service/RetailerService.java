package com.farm.main.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.main.dao.IRetailerDao;
import com.farm.main.entity.Retailer;


@Service
public class RetailerService implements IRetailerService {
	
	@Autowired
	private IRetailerDao retailerDao;

	@Override
	public List<Retailer> getRetailers() {
		return retailerDao.findAll();
	}

	@Override
	public Optional<Retailer> getRetailer(int retailerId) {
		return retailerDao.findById(retailerId);
	}

	@Override
	@Transactional
	public Retailer addRetailer(Retailer retailer) {
		return retailerDao.save(retailer);
	}

	@Override
	@Transactional
	public Retailer updateRetailer(Retailer retailer) {
		// TODO Auto-generated method stub
		return retailerDao.save(retailer);
		
	}

	@Override
    public String deleteRetailer(int retailerId) {
        // TODO Auto-generated method stub
        try {
         retailerDao.deleteById(retailerId);
         return "record deleted";
        }catch (Exception e) {
            return "record not present";
        }
    }

}
