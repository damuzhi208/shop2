
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
 * 商品名称
 * @param value
 * @param row
 * @param index
 */
var typeJson = {'1':'桥架','2':'盖板'}, mJson = {'1':'喷塑','2':'镀锌'};
var lJson = {'1':'金属软管','2':'线管'};
function shopNameFormatter(value, row, index){
	if(!row.type && !row.mType){
		return '其他';
	}else{
		if(row.type && row.mType){
			return mJson[row.mType] + typeJson[row.type];
		}else{
			return lJson[row.mType];
		}
	}
	return null;
}