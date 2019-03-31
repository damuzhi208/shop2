package com.company.hxs.sys.transfer.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;
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
		return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
	}
	
	/**
	 * �������޸ĵ�����¼ҳ��
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modTransfer")
	public String modTransfer(HttpServletRequest request, Integer id){
		if(id != null){
			TTransferRecords record = tTransferRecordsService.getTransRecord(id);
			request.setAttribute("record", record);
		}
		return "transfer/modTransfer";
	}
	
	/**
	 * ���µ�����¼
	 * @return
	 */
	@RequestMapping("updateTransFer")
	@ResponseBody
	public void updateTransfer(HttpServletResponse response, @Validated TTransferRecords record,BindingResult result){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.saveOrUpdateRecord(record);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("getTransType")
	@ResponseBody
	public String getTransType(){
		List<SelectVO> list = tTransferRecordsService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * ɾ��
	 * @param id
	 */
	@RequestMapping("delTransRecord")
	@ResponseBody
	public void delTransRecord(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tTransferRecordsService.deleteRecord(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(false, "ɾ��ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
	
}
