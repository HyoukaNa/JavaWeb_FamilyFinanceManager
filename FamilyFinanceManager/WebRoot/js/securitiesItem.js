function modifyRceord(event){
    $("ul.third-level li[alt='updateaction']").click();
    $("#modify-form input[name='modify_ID']").val($(this).parent().siblings().eq(0).text());
    $("#modify-form input[name='modify_account']").val($(this).parent().siblings().eq(1).text());
    $("#modify-form input[name='modify_stock']").val($(this).parent().siblings().eq(2).text());
    $("#modify-form input[name='modify_price']").val($(this).parent().siblings().eq(4).text());
    $("#modify-form input[name='modify_count']").val($(this).parent().siblings().eq(5).text());
    $("#modify-form input[name='modify_date']").val($(this).parent().siblings().eq(6).text());
    if($(this).parent().siblings().eq(3).text()=="卖出"){
        $("#modify-form input[value='sell']").click();
    }else{
        $("#modify-form input[value='buy']").click();
    }


}
function doModify() {
    var oldID=$("#modify-form input[name='modify_ID']").val();
    var inputList=$(this).find("input")
    for(var i=0;i<inputList.length;i++){
        if(!inputList.eq(i).val()){
            alert("有必填项为空");
            return false;
        }
    }
    $.post("/FamilyFinanceManager/ItemManager",{
        service:"modify",
        ID:$("#modify-form input[name='modify_ID']").val(),
        account:$("#modify-form input[name='modify_account']").val(),
        stock:$("#modify-form input[name='modify_stock']").val(),
        price: $("#modify-form input[name='modify_price']").val(),
        count:$("#modify-form input[name='modify_count']").val(),
        date:$("#modify-form input[name='modify_date']").val(),
        type:$("#modify-form input[checked='checked']").val(),
    },function (data,status) {
    	data=data.trim();
        if(data=="true"){
            alert("修改成功")
            $("#modify-form p.wrong-tip").text("");
            $("#modify-form p.right-tip").text("修改成功");
            if($("#"+oldID)){
            	var aim=$("#"+oldID).children();
            	aim.eq(0).text($("#modify-form input[name='modify_ID']").val());
                aim.eq(1).text($("#modify-form input[name='modify_account']").val());
                aim.eq(2).text($("#modify-form input[name='modify_stock']").val());
                aim.eq(4).text($("#modify-form input[name='modify_price']").val());
                aim.eq(5).text($("#modify-form input[name='modify_count']").val());
                aim.eq(6).text($("#modify-form input[name='modify_date']").val());
                aim.eq(3).text($("#modify-form input[checked='checked']").val());
                $("#"+oldID).attr("id",$("#modify-form input[name='modify_ID']").val());
            }
        }else{
            alert(data);
            $("#modify-form p.right-tip").text("");
            $("#modify-form p.wrong-tip").text(data);
        }
    });
    return false;
}
function doAdd() {
    var inputList=$(this).find("input")
    for(var i=0;i<inputList.length;i++){
        if(!inputList.eq(i).val()){
            alert("有必填项为空");
            return false;
        }
    }
     $.post("/FamilyFinanceManager/ItemManager",{
        service:"add",
        ID:$("#add-form input[name='add_ID']").val(),
        account:$("#add-form input[name='add_account']").val(),
        stock:$("#add-form input[name='add_stock']").val(),
        price: $("#add-form input[name='add_price']").val(),
        count:$("#add-form input[name='add_count']").val(),
        date:$("#add-form input[name='add_date']").val(),
        type:$("#add-form input[checked='checked']").val(),
    },function (data,status) {
    	data.trim();
        if(data=="true"){
            alert("修改成功")
            $("#add-form p.wrong-tip").text("");
            $("#add-form p.right-tip").text("修改成功");
        }else{
            alert(data);
            $("#add-form p.right-tip").text("");
            $("#add-form p.wrong-tip").text(data);
        }
    });
    return false;
}
$(document).ready(function(){
    $("a.modify").click(modifyRceord);
    $("#reset-btn").click(function () {
        $("#search-form input[type='text']").val("");
        $("#search-form input[value='sell']").click();
    });
    $("a.delete").click(function () {
        if(confirm("是否确认删除？")){
            var temp=$(this).parent().siblings();
            var that=$(this).parent().parent();
            $.get("",{
                service:'delete',
                id:temp.eq(0).text(),
            },function (data,status) {
            	data=data.trim();
                if(data=="true"){
                    alert("已成功删除");
                    that.remove();
                }else{
                    alert("删除失败");
                }
            });
        }else{
            return false;
        }
    })
    $("#modify-form").unbind("submit");
    $("#modify-form").submit(doModify);
    $("#add-form").unbind("submit");
    $("#add-form").submit(doAdd);
});