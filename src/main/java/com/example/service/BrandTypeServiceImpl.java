package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.BrandType;
import com.example.repository.BrandTypeRepository;

@Service("brandTypeService")
public class BrandTypeServiceImpl implements BrandTypeService{

	@Autowired
	BrandTypeRepository brandTypeRepository;
	
	@Override
	public void saveBrandTypeName(BrandType brandType) {
		brandTypeRepository.save(brandType);
		
	}

	@Override
	public List<BrandType> getAllBrandType() {
		return brandTypeRepository.findAll();
	}

	@Override
	public BrandType getBrandBybTypeId(Integer id) {
		return brandTypeRepository.findOne(id);
	}

	@Override
	public void deleteBybTypeId(Integer id) {
		brandTypeRepository.delete(id);
	}

}
