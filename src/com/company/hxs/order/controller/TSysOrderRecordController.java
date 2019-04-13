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
import com.company.hxs.basedata.entity.TBaseQiaojia;
import com.company.hxs.basedata.service.TBaseCustomerService;
import com.company.hxs.basedata.service.TBaseLineTubeService;
import com.company.hxs.basedata.service.TBaseQiaoJiaService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.sys.SysConstant;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.order.entity.TSysOrderLine;
import com.company.hxs.order.entity.TSysOrderOther;
import com.company.hxs.order.entity.TSysOrderQiaojia;
import com.company.hxs.order.entity.TSysOrderRecord;
import com.company.hxs.order.service.TSysOrderLineService;
import com.company.hxs.order.service.TSysOrderOtherService;
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
	@Resource private TSysOrderOtherService tSysOrderOtherService;
	@Resource private TBaseQiaoJiaService tBaseQiaoJiaService;
	
	/**
	 * ��������
	 * @return
	 */
	@RequestMapping("list")
	public String list(){
		return "order/orderList";
	}
	
	/**
	 * ���涩����Ϣ
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(HttpServletRequest request,@Validated TSysOrderRecord record){
		JSONObject js = new JSONObject();
		try{
			TSysOrderRecord order = tSysOrderRecordService.saveOrderRecord(record);
			js = createResult(true, "����ɹ�");
			js.put(SysConstant.SYS_DATA, order.getOrderId());
		}catch(Exception e){
			js = createResult(false, "�������" + e.getMessage());
		}
		return js.toString();
	}
	
	/**
	 * ��������
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
	 * ������������
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
	 * �����żܶ���
	 * @return
	 */
	@RequestMapping("modQiaojia")
	public String modQiaojia(HttpServletRequest request,Integer type, Integer mType, TSysOrderQiaojia qj){
		if(qj.getId() != null){
			qj = tSysOrderQiaojiaService.getOrderQj(qj.getId());
			qj.setType(mType);
			qj.setmType(mType);
			TBaseQiaojia qiaojia = tBaseQiaoJiaService.getTBaseQiaoJiaById(qj.getBaseQjId());
			qj.setDanjia(qiaojia.getDanjia());
		}
		request.setAttribute("qj", qj);
		return "order/modQiaojia";
	}
	
	/**
	 * �����żܶ���
	 */
	@RequestMapping("saveQj")
	@ResponseBody
	public void saveQj(HttpServletResponse response,TSysOrderQiaojia qj){
		JSONObject js = new JSONObject();
		try{
			tSysOrderQiaojiaService.saveOrderQiaojia(qj);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ��"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("orderQiaojia")
	public String qiaojia(HttpServletRequest request, TSysOrderQiaojia orderQj){
		request.setAttribute("qj", orderQj);
		return "order/orderQiaojia";
	}
	
	/**
	 * �żܶ��������б�
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
	 * �߹ܶ���
	 * @return
	 */
	@RequestMapping("orderLine")
	public String orderLine(HttpServletRequest request, TSysOrderLine line){
		request.setAttribute("line", line);
		return "order/orderLine";
	}
	
	/**
	 * �����żܶ���
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
	 * �����żܶ���
	 * @param response
	 */
	@RequestMapping("updateLine")
	@ResponseBody
	public void updateLine(HttpServletResponse response, TSysOrderLine line){
		JSONObject js = new JSONObject();
		try{
			tSysOrderLineService.saveUpdate(line);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(true, "����ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * �߹ܶ�������
	 * @return
	 */
	@RequestMapping("lineData")
	@ResponseBody
	public String lineData(TSysOrderLine line, Integer page, Integer rows){
		Page<TSysOrderLine> list = tSysOrderRecordService.getLinePageResult(line, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
	/**
	 * ��������
	 * @return
	 */
	@RequestMapping("orderOther")
	public String orderOther(HttpServletRequest request, Integer orderId){
		request.setAttribute("orderId", orderId);
		return "order/orderOther";
	}
	
	/**
	 * ������������
	 * @return
	 */
	@RequestMapping("otherData")
	@ResponseBody
	public String otherData(HttpServletRequest request, TSysOrderOther other, Integer page, Integer rows){
		Page<TSysOrderOther> result = tSysOrderOtherService.getPageResult(other, page, rows);
		return BaseService.List2Json4FullDate(result.getRows(), result.getFooter(), result.getTotal());
	}
	
	/**
	 * �޸���������
	 * @param request
	 * @param other
	 * @return
	 */
	@RequestMapping("modOther")
	public String modOther(HttpServletRequest request, TSysOrderOther other){
		if(other.getId() != null){
			other = tSysOrderOtherService.getOther(other.getId());
		}
		request.setAttribute("other", other);
		return "order/modOther";
	}
	
	/**
	 * ������������
	 * @param response
	 * @param other
	 */
	@RequestMapping("updateOther")
	public void updateOther(HttpServletResponse response, TSysOrderOther other){
		JSONObject js = new JSONObject();
		try{
			tSysOrderOtherService.updateOther(other);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * ɾ���ż�
	 * @param response
	 * @param id
	 */
	@RequestMapping("deleteQj")
	@ResponseBody
	public void deleteQj(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tSysOrderQiaojiaService.delete(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(true, "ɾ��ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * ɾ���߹�
	 * @param response
	 * @param id
	 */
	@RequestMapping("deleteLine")
	@ResponseBody
	public void deleteLine(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tSysOrderLineService.delete(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(true, "ɾ��ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * ɾ������
	 * @param response
	 * @param id
	 */
	@RequestMapping("deleteOther")
	@ResponseBody
	public void deleteOther(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tSysOrderOtherService.delete(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(true, "ɾ��ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
}
