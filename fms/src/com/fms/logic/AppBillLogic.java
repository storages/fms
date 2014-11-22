package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Quotation;

public interface AppBillLogic {
	List<AppBillItem> findAppBillItem(Quotation q);
}
