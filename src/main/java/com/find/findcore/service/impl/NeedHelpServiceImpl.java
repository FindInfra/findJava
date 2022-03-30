package com.find.findcore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.findcore.model.entity.NeedHelp;
import com.find.findcore.repository.NeedHelpRepository;
import com.find.findcore.service.NeedHelpService;

@Service
public class NeedHelpServiceImpl implements NeedHelpService {

	@Autowired
	NeedHelpRepository needHelpRepository;
	
	@Override
	public void addHelpRequest(NeedHelp helpReq) {
		needHelpRepository.save(helpReq);
	}

	@Override
	public List<NeedHelp> showNeedHelpRequest() {
		return needHelpRepository.findAll();
	}
	
	@Override
	public void removeNeedHelpRequests() {
		needHelpRepository.deleteAll();
	}

}
