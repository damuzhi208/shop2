package com.company.hxs.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.basedata.entity.TBaseCustomer;
import com.company.hxs.basedata.entity.TBaseLineTube;
import com.company.hxs.basedata.service.TBaseCustomerService;
import com.company.hxs.basedata.service.TBaseLineTubeService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.sys.SysConstant;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.order.entity.TSysOrderLine;
import com.company.hxs.order.entity.TSysOrderQiaojia;
import com.company.hxs.order.entity.TSysOrderRecord;
import com.company.hxs.order.service.TSysOrderLineService;
import com.company.hxs.order.service.TSysOrderQiaojiaService;
import com.company.hxs.order.service.TSysOrderRecordService;

@Controller
@RequestMapping("order")
public class TSysOrderRecordController extends BaseController {
	
	@Resource private TSysOrderRecordService tSysOrderRecordService;
	@Resource private TSysOrderQiaojiaService tSysOrderQiaojiaService;
	@Resource private TBaseCustomerService tBaseCustomerService;
	@Resource private TSysOrderLineService tSysOrderLineService;
	@Resource private TBaseLineTubeService tLineTubeService;
	
	/**
	 * 订单管理
	 * @return
	 */
	@RequestMapping("list")
	public String list(){
		return "order/orderList";
	}
	
	/**
	 * 保存订单信息
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request,@Validated TSysOrderRecord record){
		JSONObject js = new JSONObject();
		try{
			TSysOrderRecord order = tSysOrderRecordService.saveOrderRecord(record);
			js = createResult(true, "保存成功");
			js.put(SysConstant.SYS_DATA, order.getOrderId());
		}catch(Exception e){
			js = createResult(false, "保存错误" + e.getMessage());
		}
		return js.toString();
	}
	
	/**
	 * 订单数据
	 * @param request
	 * @param order
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(HttpServletRequest request, TSysOrderRecord order, Integer page, Integer rows){
		Page<TSysOrderRecord> list = tSysOrderRecordService.getPageResult(order, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
	/**
	 * 新增订单管理
	 * @return
	 */
	@RequestMapping("orderTabs")
	public String orderTabs(HttpServletRequest request, Integer orderId){
		if(orderId != null){
			TSysOrderRecord record = tSysOrderRecordService.getRecord(orderId);
			TBaseCustomer cus = tBaseCustomerService.getTBaseCustomer(record.getCustomerId());
			request.setAttribute("record", record);
			request.setAttribute("cus", cus);
		}
		return "order/orderTabs";
	}
	
	/**
	 * 新增桥架订单
	 * @return
	 */
	@RequestMapping("modQiaojia")
	public String modQiaojia(HttpServletRequest request,TSysOrderQiaojia qj){
		if(qj.getId() != null){
			qj = tSysOrderQiaojiaService.getOrderQj(qj.getId());
		}
		request.setAttribute("qj", qj);
		return "order/modQiaojia";
	}
	
	/**
	 * 保存桥架订单
	 */
	@RequestMapping("saveQj")
	@ResponseBody
	public void saveQj(HttpServletResponse response,TSysOrderQiaojia qj){
		JSONObject js = new JSONObject();
		try{
			tSysOrderQiaojiaService.saveOrderQiaojia(qj);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "保存失败"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("orderQiaojia")
	public String qiaojia(HttpServletRequest request, TSysOrderQiaojia orderQj){
		request.setAttribute("qj", orderQj);
		return "order/orderQiaojia";
	}
	
	/**
	 * 桥架订单数据列表
	 * @param request
	 * @param orderQj
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("qiaojiaData")
	@ResponseBody
	public String qiaojiaData(HttpServletRequest request, TSysOrderQiaojia orderQj, Integer page, Integer rows){
		Page<TSysOrderQiaojia> list = tSysOrderRecordService.getPageResult(orderQj, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
	/**
	 * 线管订单
	 * @return
	 */
	@RequestMapping("orderLine")
	public String orderLine(HttpServletRequest request, TSysOrderLine line){
		request.setAttribute("line", line);
		return "order/orderLine";
	}
	
	/**
	 * 新增桥架订单
	 * @return
	 */
	@RequestMapping("modLine")
	public String modLine(HttpServletRequest request, TSysOrderLine line){
		if(line.getId() != null){
			line = tSysOrderLineService.getOrderLine(line.getId());
			TBaseLineTube tube = tLineTubeService.getLineTube(line.getBaseLineId());
			request.setAttribute("tube", tube);
		}
		request.setAttribute("line", line);
		return "order/modLine";
	}
	
	/**
	 * 更新桥架订单
	 * @param response
	 */
	@RequestMapping("updateLine")
	@ResponseBody
	public void updateLine(HttpServletResponse response, TSysOrderLine line){
		JSONObject js = new JSONObject();
		try{
			tSysOrderLineService.saveUpdate(line);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(true, "保存失败："+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * 线管订单数据
	 * @return
	 */
	@RequestMapping("lineData")
	@ResponseBody
	public String lineData(TSysOrderLine line, Integer page, Integer rows){
		Page<TSysOrderLine> list = tSysOrderRecordService.getLinePageResult(line, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
}
