function modifyRceord(event){
    $("ul.third-level li[alt='modify']").click();
    var tags=["#form3-name","#form3-password","#form3-account","#form3-wage","#form3-call","#form3-age","#form3-ID"]
    for(var i=0;i<7;i++){
    	$(tags[i]).val($(this).parent().siblings().eq(i).text());
    }
}
function doModifyRecord() {
    var oldID=$("#form3-ID").val();
    var inputList=$(this).find("input")
    for(var i=0;i<inputList.length;i++){
        if(!inputList.eq(i).val()){
            alert("有必填项为空");
            return false;
        }
    }
    $.post("/FamilyFinanceManager/UserServlet",{
        service:"modify",
        name:$("#form3-name").val(),
        password:$("#form3-password").val(),
        account:$("#form3-account").val(),
        wage:$("#form3-wage").val(),
        call:$("#form3-call").val(),
        age:$("#form3-age").val(),
        ID:$("#form3-ID").val(),
    },function (data,status) {
    	data=data.trim();
        if(data=="true"){
            alert("修改成功")
            $("#form3 p.wrong-tip").text("");
            $("#form3 p.right-tip").text("修改成功");
            if($("#"+oldID)){
                var aim=$("#"+oldID).children();
                aim.eq(0).text($("#form3-name").val());
                aim.eq(1).text($("#form3-password").val());
                aim.eq(2).text($("#form3-account").val());
                aim.eq(3).text($("#form3-wage").val());
                aim.eq(4).text($("#form3-call").val());
                aim.eq(5).text($("#form3-age").val());
                aim.eq(6).text($("#form3-ID").val());
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
    $.post("/FamilyFinanceManager/UserServlet",{
        service:"add",
        name:$("#form2-name").val(),
        password:$("#form2-password").val(),
        account:$("#form2-account").val(),
        wage:$("#form2-wage").val(),
        call:$("#form2-call").val(),
        age:$("#form2-age").val(),
        ID:$("#form2-ID").val(),
    },function (data,status) {
    	data=data.trim();
        if(data=="true"){
            alert("添加成功");
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
function doDeleteRecord() {
    var temp=$(this).parent().siblings();
    if(confirm("是否确认删除？")){
        $.get("",{service:"delete",
            name:temp.eq(7).text(),
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
}
$(document).ready(function(){
    $("a.modify").click(modifyRceord);
    $("a.delete").click(doDeleteRecord);
    $("#form3").unbind("submit");
    $("#form3").submit(doModifyRecord);
    $("#form2").unbind("submit");
    $("#form2").submit(doAdd);
    $("#form1").unbind("submit");
    $("#form1").submit(function (){
        if(!$("#form1-ID").val()&&!$("#form1-name").val()){
            alert("至少输入一个搜索条件");
            return false;
        }
    });
});
