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
var typeJson = {'1':'桥架','2':'盖板'};
var mJson = {'1':'喷塑','2':'镀锌'};
function lxFormatter(value,row,index){
	return mJson[row.mType] + typeJson[row.type];
}
function guigeFormatter(value,row,index){
	if(row.type == 1){
		return "【高"+row.heights+"】厚度["+row.houdu+"]"+"系数["+row.xishu+"]"+"吨位价["+row.dwj+"]"+"单价["+row.danjia+"]";
	}else{
		return "【宽*高"+row.widths+"*"+row.heights+"】厚度["+row.houdu+"]"+"系数["+row.xishu+"]"+"吨位价["+row.dwj+"]"+"单价["+row.danjia+"]";
	}
}
