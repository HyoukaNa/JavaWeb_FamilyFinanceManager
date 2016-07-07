function modifyRceord(event){
    $("ul.third-level li[alt='modify']").click();
    $("#form3-type").val($(this).parent().siblings().eq(1).text());
    $("#form3-ID").val($(this).parent().siblings().eq(0).text());
    $("#form3-name").val($(this).parent().siblings().eq(2).text());
    $("#form3-num").val($(this).parent().siblings().eq(3).text());
    $("#form3-date").val($(this).parent().siblings().eq(4).text());
    $("#form3-source").val($(this).parent().siblings().eq(5).text());
    $("#form3-remark").val($(this).parent().siblings().eq(6).text());
}
function doModify() {
    var oldID=$("#form3-ID").val();
    var inputList=$(this).find("input")
    for(var i=0;i<inputList.length;i++){
        if(!inputList.eq(i).val()){
            alert("有必填项为空");
            return false;
        }
    }
    $.post("/FamilyFinanceManager/IncomeManager",{
        service:"modify",
        type:$("#form3-type").val(),
        ID:$("#form3-ID").val(),
        name:$("#form3-name").val(),
        num:$("#form3-num").val(),
        date:$("#form3-date").val(),
        source:$("#form3-source").val(),
        remark:$("#form3-remark").val(),
    },function (data,status) {
        data=data.trim();
    	if(data=="true"){
            alert("修改成功")
            $("#form3 p.wrong-tip").text("");
            $("#form3 p.right-tip").text("修改成功");
            if($("#"+oldID)){
                var aim=$("#"+oldID).children();
                aim.eq(0).text($("#form3-ID").val());
                aim.eq(1).text($("#form3-type").val());
                aim.eq(2).text($("#form3-name").val());
                aim.eq(3).text($("#form3-num").val());
                aim.eq(4).text($("#form3-date").val());
                aim.eq(5).text($("#form3-source").val());
                aim.eq(6).text($("#form3-remark").val());
                $("#"+oldID).attr("id",$("#form3-ID").val());
            }
        }else{
            alert(data);
            $("#form3 p.right-tip").text("");
            $("#form3 p.wrong-tip").text(data);
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
    $.post("/FamilyFinanceManager/IncomeManager",{
        service:"add",
        type:$("#form2-type").val(),
        name:$("#form2-name").val(),
        num:$("#form2-num").val(),
        date:$("#form2-date").val(),
        source:$("#form2-source").val(),
        remark:$("#form2-remark").val(),
    },function (data,status) {
    	data=data.trim();
        if(data=="true"){
            alert("添加成功")
            $("#form2 p.wrong-tip").text("");
            $("#form2 p.right-tip").text("添加成功");
        }else{
            alert(data);
            $("#form2 p.right-tip").text("");
            $("#form2 p.wrong-tip").text(data);
        }
    });
    return false;
}
$(document).ready(function(){
    $("a.modify").click(modifyRceord);
    $("a.delete").click(function () {
        var temp=$(this).parent().siblings();
        if(confirm("是否确认删除？")){
            $.post("",{
                service:"delete",
                id: temp.eq(0).text(),
            },function (data,status) {
                if(data=="true"){
                    alert("已成功删除");
                    $(this).parent().parent().remove();
                }else{
                    alert("删除失败");
                }
            });
        }else{
            return false;
        }
    });
    $("#form1").unbind("submit");
    $("#form1").submit(function (){
        if(!$("#form1-date").val()&&!$("#form1-name").val()){
            alert("至少输入一个搜索条件");
            return false;
        }
    });
    $("#form3").unbind("submit");
    $("#form3").submit(doModify);
    $("#form2").unbind("submit");
    $("#form2").submit(doAdd);
});