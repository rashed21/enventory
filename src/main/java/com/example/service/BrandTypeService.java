package com.example.service;

import java.util.List;

import com.example.model.BrandType;

public interface BrandTypeService {
	public void saveBrandTypeName(BrandType brandType);
	
	public List<BrandType> getAllBrandType();
	
	public BrandType getBrandBybTypeId(Integer id);
	//public void updateBrandType(Integer id);
	public void deleteBybTypeId(Integer id);
	
	
}
