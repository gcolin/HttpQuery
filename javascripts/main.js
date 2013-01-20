$(document).ready(function(){
	if(location.href.indexOf("/doc.php")!=-1){
		$("#doc").addClass("active");
		var index = location.href.indexOf("doc.php?page=");
		if(index!=-1){
			var url = location.href.substring(index);
			$("a[href='"+url+"']").closest("li").addClass("active");
		}
	}
});
