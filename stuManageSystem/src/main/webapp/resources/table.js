function abstractTable() {
	this.id = null;
	this.tableobj = null;
	this.rowNum = 0;
	this.colNum = 0;
	this.header = [];
	this.content = []; //body数据
	//从外部获取表格的信息
	this.currentClickRowNum = 0;
	this.getColNum = function() {
		this.colNum = this.header.length;
		return this.colNum;
	};
	this.clearTable = function() {};
	this.showHeader = function() {};
	this.showContent = function(begin, end) {};
	this.showFoot = function() {};
	//分页功能
	this.allDataNum = 0;  //总数据条数
	this.displayNum = 10; //每页显示条数
	this.maxPageNum = 0;  //最大页数
	this.currentPageNum = 1; //当前页码
	//分页组
	this.groupDataNum = 10; //每组显示10页
	this.groupNum = 1; //当前组
	//分页功能行为
	this.paginationFromBeginToEnd = function(begin, end) {};
	this.first = function() {}; //首页
	this.last = function() {}; //最后一页
	this.prev = function() {}; //上一页
	this.next = function() {}; //下一页
	this.go = function() {}; //跳页
	//表格初始化
	this.init = function(begin, end) {}
}

//表格对象模板
function tableTemplate(table_id) {
	abstractTable.call(this);
	this.id = table_id;
}

/*
 * 表格对象
 * @param options
 */
function table(options) {
	if(!options){return;}
	if(!$.isPlainObject(options)){return;}
	
	tableTemplate.call(this, options.table_id);
	//得到表格对象
	this.tableobj = $("#" + this.id);
	//清空表格
	this.clearTable = function() {
		this.tableobj.html(" ");
	}
	//实现分页
	this.paginationFromBeginToEnd = function(begin, end) {
		this.maxPageNum = Math.ceil(this.allDataNum / this.displayNum);
		var arrPage = [];
		for(var i = begin; i < end; i++) {
			arrPage.push(this.content[i]);
		}
		return arrPage;
	}
	this.showHeader = function() {
		if(this.header != null) {
			var $thead = $("<thead>"),
				$tr = $("<tr>"),
				$th;
			for(var i = 0; i < this.colNum; i ++) {
				$th = $("<th>").html(this.header[i]);
				$th.appendTo($tr);
			}
			$tr.appendTo($thead);
			$thead.appendTo(this.tableobj);
		}
	}
	//初始化tbody
	this.showContent = function(begin, end) {
		if(this.content != null) {
			var $tbody = $("<tbody>"),
				$tr,
				$td;
			var tempData = this.paginationFromBeginToEnd(begin, end),
				len = tempData.length;
			for(var i = 0; i < len; i ++) {
				$tr = $("<tr>").appendTo($tbody);
				if(i % 2 == 1) {
					$tr.addClass("evenrow");
				}
				for(var key in tempData[i]) {
					$td = $("<td>").html(tempData[i][key]).appendTo($tr);
				}
			}
			this.tableobj.append($tbody);
		}
	} 
	//初始化tfoot
    this.showFoot = function(){
       var $tfoot = $("<tfoot>"),
           $tr = $("<tr>"),
           $td = $("<td>").attr("colspan",this.colNum).addClass("paging");
           $tr.append($td);
           $tfoot.append($tr);
           this.tableobj.append($tfoot);
           this.pagination($td);
    }
    //表格分页
    this.pagination = function(tdCell){
        var $td= typeof(tdCell) == "object" ? tdCell : $("#" + tdCell);
        //首页
        var oA = $("<a/>");
        oA.attr("href","#1");
        oA.html("首页");
        $td.append(oA);
        //上一页
        if(this.currentPageNum>=2){
            var oA = $("<a/>");
            oA.attr("href","#"+(this.currentPageNum - 1));
            oA.html("上一页");
            $td.append(oA);
        }
        //普通显示格式
        if(this.maxPageNum <= this.groupDataNum){  // 10页以内 为一组
            for(var i = 1;i <= this.maxPageNum ;i++){
                var oA = $("<a/>");
                oA.attr("href","#"+i);
                if(this.currentPageNum == i){
                    oA.attr("class","current");
                }
                oA.html(i);
                $td.append(oA);
            }
        }else{//超过10页以后（也就是第一组后）
             if(this.groupNum<=1){//第一组显示
                 for(var j = 1;j <= this.groupDataNum ;j++){
                     var oA = $("<a/>");
                     oA.attr("href","#"+j);
                     if(this.currentPageNum == j){
                         oA.attr("class","current");
                     }
                     oA.html(j);
                     $td.append(oA);
 
                 }
             }else{//第二组后面的显示
                 var begin = (this.groupDataNum*(this.groupNum-1))+ 1,
                      end ,
                     maxGroupNum = Math.ceil(this.maxPageNum/this.groupDataNum);
                 if(this.maxPageNum%this.groupDataNum!=0&&this.groupNum==maxGroupNum){
                     end = this.groupDataNum*(this.groupNum-1)+this.maxPageNum%this.groupDataNum
                 }else{
                     end = this.groupDataNum*(this.groupNum);
                 }
 
                 for(var j = begin;j <= end ;j++){
                     var oA = $("<a/>");
                     oA.attr("href","#"+j);
                     if(this.currentPageNum == j){
                         oA.attr("class","current");
                     }
                     oA.html(j);
                     $td.append(oA);
                 }
             }
        }
        //下一页
        if( (this.maxPageNum - this.currentPageNum) >= 1 ){
            var oA = $("<a/>");
            oA.attr("href","#" + (this.currentPageNum + 1));
            oA.html("下一页");
            $td.append(oA);
        }
        //尾页
        var oA = $("<a/>");
        oA.attr("href","#" + this.maxPageNum);
        oA.html("尾页");
        $td.append(oA);
 
        var page_a = $td.find('a');
        var tempThis = this;
 
        page_a.unbind("click").bind("click",function(){
            var nowNum =  parseInt($(this).attr('href').substring(1));
 
            if(nowNum>tempThis.currentPageNum){//下一组
                if(tempThis.currentPageNum%tempThis.groupDataNum==0){
                    tempThis.groupNum += 1;
 
                    var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                    if(tempThis.groupNum>=maxGroupNum){
                        tempThis.groupNum = maxGroupNum;
                    }
                }
            }
            if(nowNum<tempThis.currentPageNum){//上一组
                if((tempThis.currentPageNum-1)%tempThis.groupDataNum==0){
                    tempThis.groupNum -= 1;
                    if(tempThis.groupNum<=1){
                        tempThis.groupNum = 1;
                    }
                }
            }
            if(nowNum==tempThis.maxPageNum){//直接点击尾页
                var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                tempThis.groupNum = maxGroupNum;
            }
            if(nowNum==1){
                var maxGroupNum = Math.ceil(tempThis.maxPageNum/tempThis.groupDataNum);
                tempThis.groupNum = 1;
            }
            tempThis.currentPageNum = nowNum;
 
 
            tempThis.init((tempThis.currentPageNum-1)*tempThis.displayNum,
                tempThis.currentPageNum*tempThis.displayNum);
            return false;
        });
    }
    //初始化
    this.init = function(begin,end){
        this.header = options.headers;
        this.colNum = this.header.length;
        this.content = options.data;
        this.allDataNum = this.content.length;
        if(options.displayNum){
            this.displayNum = options.displayNum;
        }
        if(options.groupDataNum){
            this.groupDataNum = options.groupDataNum;
        }
        this.clearTable();
        this.showHeader();
        this.showContent(begin,end);
        this.showFoot();
    }
 
    this.init(0,options.displayNum);
	
}