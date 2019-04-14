/**
 * 查询
 */
function doSearch(){
	var params = {};
	$("#toolbar").find("input[name],select[name]").each(function(i){
		params[this.name] = this.value;
	});
	$("#datagrid").datagrid('load',params);
}

/**
 * 规格formatter
 * @param value
 * @param row
 * @param index
 */
var mJson = {'1':'金属软管','2':'线管'};
function lxFormatter(value,row,index){
	return mJson[row.mType];
}
function guigeFormatter(value,row,index){
	return "【"+row.guige+"】单位["+row.danweis+"]单价["+row.danjia+"]";
	/*if(row.type == 1){
		return "【高"+row.heights+"】厚度["+row.houdu+"]"+"系数["+row.xishu+"]"+"吨位价["+row.dwj+"]"+"单价["+row.danjia+"]";
	}else{
		return "【宽*高"+row.widths+"*"+row.heights+"】厚度["+row.houdu+"]"+"系数["+row.xishu+"]"+"吨位价["+row.dwj+"]"+"单价["+row.danjia+"]";
	}*/
}
