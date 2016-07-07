function modifyRceord(event){
    $("ul.third-level li[alt='updateaction']").click();
    $("#modify-form input[name='ID']").val($(this).parent().siblings().eq(0).text());
    $("#modify-form input[name='account']").val($(this).parent().siblings().eq(1).text());
    $("#modify-form input[name='stock']").val($(this).parent().siblings().eq(2).text());
    $("#modify-form input[name='danjia']").val($(this).parent().siblings().eq(4).text());
    $("#modify-form input[name='count']").val($(this).parent().siblings().eq(5).text());
    $("#modify-form input[name='time']").val($(this).parent().siblings().eq(6).text());
    if($(this).parent().siblings().eq(3).text()=="卖出"){
        $("#modify-form input[value='sell']").click();
    }else{
        $("#modify-form input[value='buy']").click();
    }


}
function doModify() {
    var oldID=$("#modify-form input[name='ID']").val();
    var inputList=$(this).find("input")
    for(var i=0;i<inputList.length;i++){
        if(!inputList.eq(i).val()){
            alert("有必填项为空");
            return false;
        }
    }
    $.post("",{
        service:"modify",
        id:$("#modify-form input[name='ID']").val(),
        account:$("#modify-form input[name='account']").val(),
        stock:$("#modify-form input[name='stock']").val(),
        danjia: $("#modify-form input[name='danjia']").val(),
        count:$("#modify-form input[name='count']").val(),
        time:$("#modify-form input[name='time']").val(),
        type:$("#modify-form input[checked='checked']").val(),
    },function (data,status) {
        if(data=="True"){
            alert("修改成功")
            $("#modify-form p.wrong-tip").text("");
            $("#modify-form p.right-tip").text("修改成功");
            if($("#"+oldID)){
                var aim=$("#"+oldID).children();
                aim.eq(0).text($("#modify-form input[name='ID']").val());
                aim.eq(1).text($("#modify-form input[name='account']").val());
                aim.eq(2).text($("#modify-form input[name='stock']").val());
                aim.eq(3).text($("#modify-form input[name='danjia']").val());
                aim.eq(4).text($("#modify-form input[name='count']").val());
                aim.eq(5).text($("#modify-form input[name='time']").val());
                aim.eq(6).text($("#modify-form input[checked='checked']").val());
                $("#"+oldID).attr("id",$("#form3-ID").val());
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
     $.post("",{
        service:"modify",
        id:$("#add-orm input[name='ID']").val(),
        account:$("#add-form input[name='account']").val(),
        stock:$("#add-form input[name='stock']").val(),
        danjia: $("#add-form input[name='danjia']").val(),
        count:$("#add-form input[name='count']").val(),
        time:$("#add-form input[name='time']").val(),
        type:$("#add-form input[checked='checked']").val(),
    },function (data,status) {
        if(data=="True"){
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
            $.get("",{
                service:'delete',
                id:temp.eq(0).text(),
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
    })
    $("#modify-form").unbind("submit");
    $("#modify-form").submit(doModify);
    $("#add-form").unbind("submit");
    $("#add-form").submit(doAdd);
});