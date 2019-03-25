package com.company.hxs.sys.transfer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.sys.transfer.entity.TTransferRecords;
import com.company.hxs.sys.transfer.service.TTransferRecordsService;
import com.company.hxs.sys.transfer.vo.TTransferRecordsVO;

/**
 * ��Ʒ������¼
 * @author luqi
 *
 */
@Controller
@RequestMapping("transfer")
public class TTransferRecordsController extends BaseController{
	
	@Resource private TTransferRecordsService tTransferRecordsService;
	
	/**
	 * ������¼ҳ��
	 * @return
	 */
	@RequestMapping("list")
	public String listRecord(){
		return "transfer/listRecord";
	}
	
	/**
	 * ������¼����
	 * @param request
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(HttpServletRequest request,TTransferRecordsVO vo, Integer page, Integer rows){
		Page<TTransferRecordsVO> list = tTransferRecordsService.getPageResult(vo, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getTotal());
	}
	
	/**
	 * �������޸ĵ�����¼ҳ��
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modTransfer")
	public String modTransfer(HttpServletRequest request, Integer id){
		TTransferRecords record = tTransferRecordsService.getTransRecord(id);
		request.setAttribute("record", record);
		return "transfer/modTransfer";
	}
	
	/**
	 * ���µ�����¼
	 * @return
	 */
	@RequestMapping("updateTransFer")
	@ResponseBody
	public String updateTransfer(TTransferRecords record){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.saveOrUpdate(record);
			js = createResult(true, null);
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�" + e.getMessage());
		}
		return js.toString();
	}
	
}
