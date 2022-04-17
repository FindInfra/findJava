package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.entity.NeedHelp;

public interface NeedHelpService {

	void addHelpRequest(NeedHelp helpReq);

	List<NeedHelp> showNeedHelpRequest();

	void removeNeedHelpRequests();

}
