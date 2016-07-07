/**
 * Created by yang on 16-7-4.
 */
$(document).ready(function () {
    $("a.delete").click(function () {
        var temp=$("#stock-info").children();
        if(confirm("是否确认删除？")){
            $.get("",{
                service:"delete",
                id:temp.eq(1).text(),
            },function (data,status) {
                if(data=="true"){
                    alert("已成功删除");
                    window.location.href="stock_list.html";
                }else{
                    alert("删除失败");
                }
            });
        }else{
            return false;
        }
    });
    $("#stock-info").click(function () {
        $("#stock-info-form").submit();
    })
    $("#stock-hold").click(function () {
        $("#stock-hold-form").submit();
    })
});